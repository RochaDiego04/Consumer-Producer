
package models;

import controller.Controller;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Consumer extends Thread{
    private Buffer buffer;
    private int index;
    public Controller controller;
    
    private String stateFlag;
    
    public Consumer(Buffer buffer, int index, Controller controller){
        this.index = index;
        this.buffer = buffer;
        this.stateFlag = "standing";
        this.controller = controller;
    }
    
    public void run(){
        
        this.standing(); // Consumer starts sleeping
        
        while(true){
            
            this.consuming(); // If buffer is empty, sleeps
            this.standing(); // Time between consuming
        }
    }
    
    public void consuming(){
        String burguer = this.buffer.consume(this.index, this);
        this.controller.updateGUIConsumerText(index, "Consumed a " + burguer + "\n");
    }
    
    public void eating(){ // Method called from consuming()
        try {
            this.setStateFlag("consuming");
            this.controller.updateGUIConsumerText(index, "Consumer " + (index+1) + " consuming\n");
            this.controller.updateGUIConsumerStates(index);
            
            sleep((long) (Math.random() * 4000));
        } catch (InterruptedException ex) {
            Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void standing(){
        this.setStateFlag("standing");
        this.controller.updateGUIConsumerText(index, "Consumer " + (index+1) + " standing\n");
        this.controller.updateGUIConsumerStates(index);
        try {
            sleep((long) (Math.random() * 2000 + 1000));
        } catch (InterruptedException ex) {
            Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setStateFlag(String stateFlag) {
        this.stateFlag = stateFlag;
    }
    
    public String getStateFlag() {
        return stateFlag;
    }
}
