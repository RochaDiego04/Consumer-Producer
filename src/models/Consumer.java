
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
            
            this.consuming();
            
            try {
                sleep((int) (Math.random() * 4000));
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void consuming(){
        String burguer = this.buffer.consume(this.index, this);
        System.out.println(burguer + " Taken from buffer by Consumer: " + index);
    }
    
    public void standing(){
        controller.updateGUIConsumerStates(index);
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
