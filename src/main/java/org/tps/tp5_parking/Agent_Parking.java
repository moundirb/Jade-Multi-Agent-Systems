package org.tps.tp5_parking;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class Agent_Parking extends Agent {
    private int availableSpaces;

    protected void setup() {
        System.out.println("Hello welcome to the parking lot I am " + getLocalName() );
        availableSpaces = 10;

        addBehaviour(new HandleRequests());
    }

    private class HandleRequests extends CyclicBehaviour {
        public void action() {
            MessageTemplate template = MessageTemplate.or(
                    MessageTemplate.MatchPerformative(ACLMessage.REQUEST),
                    MessageTemplate.MatchPerformative(ACLMessage.INFORM)
            );
            ACLMessage msg = myAgent.receive(template);

            if (msg != null) {
                if (msg.getPerformative() == ACLMessage.REQUEST) {
                    int requestedSpaces = Integer.parseInt(msg.getContent());
                    if (requestedSpaces <= availableSpaces) {
                        // Respond with confirmation and update available spaces
                        ACLMessage reply = msg.createReply();
                        reply.setPerformative(ACLMessage.CONFIRM);
                        availableSpaces -= requestedSpaces;
                        reply.setContent("Reservation confirmed. Spaces reserved: " + requestedSpaces);
                        send(reply);
                    } else {
                        // Respond with denial if not enough spaces available
                        ACLMessage reply = msg.createReply();
                        reply.setPerformative(ACLMessage.REFUSE);
                        reply.setContent("Not enough spaces available");
                        send(reply);
                    }
                } else if (msg.getPerformative() == ACLMessage.INFORM && msg.getContent().startsWith("Leaving")) {
                    // Extract reserved spaces information from the content
                    String[] parts = msg.getContent().split("\\|");
                    if (parts.length == 2) {
                        int leavingSpaces = Integer.parseInt(parts[1]);

                        // Update available spaces when a driver leaves
                        availableSpaces += leavingSpaces;
                        System.out.println("Driver has left. Available spaces: " + availableSpaces);
                    }
                }
            } else {
                block();
            }
        }
    }
}
