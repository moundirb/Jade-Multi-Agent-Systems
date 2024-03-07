package org.tps.tp2_CyclicBehaviour;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;

public class agent2 extends Agent {

    @Override
    protected void setup() {
        // Adding the CyclicBehaviour
        addBehaviour(new CyclicBehaviour(){
            @Override
            public void action() {
                // Display the initial message
                System.out.println("Je suis l'Agent 001");
            }
        });
    }
}
