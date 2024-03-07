package org.tps.tp4_InterAgentCommunication;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;


public class Agent001 extends Agent {

    protected void setup() {
        int randomNumber = (int) (Math.random() * 100);

        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(getAID("belbachir"));
        msg.setContent(String.valueOf(randomNumber));
        this.send(msg);
        System.out.println(getLocalName() + ": Sending number " + randomNumber + " to " + getAID("belbachir").getLocalName());

        // Receiving max number from Agent_Calcul
        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                ACLMessage maxNumberMsg = receive();
                if (maxNumberMsg != null) {
                    int maxNumber = Integer.parseInt(maxNumberMsg.getContent());
                    System.out.println(getLocalName() + ": Received max number " + maxNumber +" from "+ maxNumberMsg.getSender().getLocalName());
                } else {
                    block();
                }
            }
        });
    }
}
