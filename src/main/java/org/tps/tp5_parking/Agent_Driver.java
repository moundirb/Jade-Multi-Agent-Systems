package org.tps.tp5_parking;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class Agent_Driver extends Agent {
    protected void setup() {
        addBehaviour(new RequestParking());
    }

    private class RequestParking extends OneShotBehaviour {
        public void action() {
            // Request parking spaces from ParkingLotAgent
            ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
            request.addReceiver(getAID("agent_park"));
            request.setContent("10"); // Requesting 2 parking spaces
            send(request);

            // Wait for the response
            MessageTemplate confirmTemplate = MessageTemplate.MatchPerformative(ACLMessage.CONFIRM);
            MessageTemplate refuseTemplate = MessageTemplate.MatchPerformative(ACLMessage.REFUSE);

            ACLMessage response = blockingReceive(MessageTemplate.or(confirmTemplate, refuseTemplate));

            if (response != null) {
                // Response received
                if (response.getPerformative() == ACLMessage.CONFIRM) {
                    System.out.println("Reservation confirmed. Spaces reserved: " + request.getContent());

                    // Start waker behavior to leave after a certain time
                    addBehaviour(new LeaveAfterDelay(request.getContent()));
                } else {
                    System.out.println("Reservation refused. Reason: " + response.getContent());
                }
            } else {
                System.out.println("No response received.");
            }
        }
    }

    private class LeaveAfterDelay extends WakerBehaviour {
        private String reservedSpaces;

        public LeaveAfterDelay(String reservedSpaces) {
            super(Agent_Driver.this, 5000); // Leave after 5 seconds (adjust as needed)
            this.reservedSpaces = reservedSpaces;
        }

        protected void onWake() {
            System.out.println("Driver leaving the parking lot. Spaces reserved: " + reservedSpaces);

            // Inform the parking agent that the driver is leaving and specify the reserved spaces
            ACLMessage leaveMsg = new ACLMessage(ACLMessage.INFORM);
            leaveMsg.addReceiver(getAID("agent_park"));
            leaveMsg.setContent("Leaving|" + reservedSpaces);
            send(leaveMsg);
        }
    }
}
