package org.tps.tp4_InterAgentCommunication;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import java.util.HashSet;
import java.util.Set;

public class Agent_Calcul extends Agent {

    private int maxNumber = Integer.MIN_VALUE;
    private Set<String> uniqueSenders = new HashSet<>();
    private int agentsCount = 3;

    protected void setup() {
        addBehaviour(new Behaviour() {
            public void action() {
                ACLMessage msg = myAgent.receive();
                if (msg != null) {
                    int receivedNumber = Integer.parseInt(msg.getContent());

                    if(receivedNumber > maxNumber){
                        maxNumber =receivedNumber;
                    }

                    String senderName = msg.getSender().getLocalName();
                    if (!uniqueSenders.contains(senderName)) {
                        uniqueSenders.add(senderName);
                        System.out.println(getLocalName() + ": Received number " + receivedNumber + " from " + senderName);
                    }

                    // Check if all unique agents have sent their numbers
                    if (uniqueSenders.size() == agentsCount) {
                        addBehaviour(new SendMaxNumberBehavior());
                    }
                } else {
                    block();
                    System.out.println("Waiting for agents to send their numbers...");
                }
            }

            public boolean done() {
                return uniqueSenders.size() == agentsCount;
            }
        });
    }

    private class SendMaxNumberBehavior extends Behaviour {
        private boolean done = false;

        public void action() {
            ACLMessage replyMsg = new ACLMessage(ACLMessage.INFORM);
            replyMsg.setContent(String.valueOf(maxNumber));

            // Add all three agents as receivers
            replyMsg.addReceiver(getAID("moundir001"));
            replyMsg.addReceiver(getAID("moundir002"));
            replyMsg.addReceiver(getAID("moundir003"));

            send(replyMsg);
            System.out.println(getLocalName() + ": Sent maximum number " + maxNumber + " to all agents.");

            // Set the behavior as done after sending the maximum number
            done = true;
        }

        public boolean done() {
            return done;
        }
    }
}
