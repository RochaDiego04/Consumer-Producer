
package models;

import controller.Controller;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Producer extends Thread{
    
    private Buffer buffer;
    private final String[] burgers = {"Cheeseburger", "Bacon Burger", "Veggie Burger"};
    private int index;
    Controller controller;
    
    private String stateFlag;
    
    public Producer(Buffer buffer, int index, Controller controller){
        this.index = index;
        this.buffer = buffer;
        this.controller = controller;
    }
    
    public void run(){
        
        this.standing(); // Producer starts sleeping
        
        while(true){
            int randomIndex = (int) (Math.random() * burgers.length);
            String burguer = burgers[randomIndex]; // Get a random burguer from array
            
            this.producing(burguer);
            this.controller.updateGUIProducerText(index, "Produced a " + burguer + "\n");
            
            this.standing();
        }
    }
    
    public void producing(String burguer){
        this.buffer.produce(burguer, this.index, this);
    }
    
    public void cooking(){ // Method called from producing()
        try {
            this.setStateFlag("cooking");
            this.controller.updateGUIProducerText(index, "Producer " + (index+1) + " cooking\n");
            this.controller.updateGUIProducerStates(index);
            
            sleep((long) (Math.random() * 4000));
        } catch (InterruptedException ex) {
            Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void standing(){
        this.setStateFlag("standing");
        this.controller.updateGUIProducerText(index, "Producer " + (index+1) + " standing\n");
        this.controller.updateGUIProducerStates(index);
        try {
            sleep((long) (Math.random() * 2000));
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

