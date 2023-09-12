
package models;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Consumer extends Thread{
    private Buffer buffer;
    private String name;
    
    public Consumer(Buffer buffer, String name){
        this.name = name;
        this.buffer = buffer;
    }
    
    public void run(){
        while(true){
            String burguer = this.buffer.consume();
            System.out.println(burguer + " Taken from buffer by: " + name);
            
            try {
                sleep((int) (Math.random() * 4000));
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
