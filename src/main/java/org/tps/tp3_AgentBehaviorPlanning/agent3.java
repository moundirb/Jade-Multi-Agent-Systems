package org.tps.tp3_AgentBehaviorPlanning;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.core.behaviours.WakerBehaviour;

public class agent3 extends Agent {

    protected void setup() {
        System.out.println("Agent " + getLocalName() + " is ready.");

        // Add TickerBehaviour with a period of 5 seconds and a total of 6 iterations
        addBehaviour(new TickerBehaviour(this, 5000) {
            @Override
            protected void onTick() {
                // Action to be performed at each tick
                System.out.println("tick: "+getTickCount()+" Je suis l'Agent 001");

                ;


                }

        });

        // Add the WakerBehaviour (wakeup-time 30 secs)
        addBehaviour(new WakerBehaviour(this, 30000) {
            protected void handleElapsedTimeout() {
                System.out.println("Traitement terminé ");
                myAgent.doDelete();
            }
        });


    }
}
