package org.tps.tp1_OneShotBehaviour;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class agent extends Agent {
    @Override
    public void setup(){
        // adding BneShotBehaviour
        addBehaviour(new MyOneShotBehaviour());
    }
    // custom BneShotBehaviour class
    private class MyOneShotBehaviour extends OneShotBehaviour {
        @Override
        public void action(){
            // display the initial message
            System.out.println("Je suis l'Agent 001");
        }
    }
}
