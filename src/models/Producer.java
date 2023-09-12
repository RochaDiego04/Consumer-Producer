
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
            System.out.println(burguer + " Inserted into the buffer by Producer: " + (index + 1));
            
            this.standing();
        }
    }
    
    public void producing(String burguer){
        this.buffer.produce(burguer, this.index, this);
    }
    
    public void cooking(){ // Method called from producing()
        try {
            this.setStateFlag("cooking");
            System.out.println("Producer " + (index+1) + " is " + this.getStateFlag());
            this.controller.updateGUIProducerStates(index);
            
            sleep((long) (Math.random() * 4000));
        } catch (InterruptedException ex) {
            Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void standing(){
        this.setStateFlag("standing");
        controller.updateGUIProducerStates(index);
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

