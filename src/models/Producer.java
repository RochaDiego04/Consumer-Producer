
package models;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Producer extends Thread{
    private Buffer buffer;
    private final String[] burgers = {"Cheeseburger", "Bacon Burger", "Veggie Burger"};
    private int index;
    
    public Producer(Buffer buffer, int index){
        this.index = index;
        this.buffer = buffer;
    }
    
    public void run(){
        while(true){
            int randomIndex = (int) (Math.random() * burgers.length);
            String burguer = burgers[randomIndex]; // Get a random burguer from array
            buffer.produce(burguer);
            System.out.println(burguer + " Inserted into the buffer by Producer: " + index);
            
            try {
                sleep((int) (Math.random() * 4000));
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

