
package models;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Consumer extends Thread{
    private Buffer buffer;
    private int index;
    
    public Consumer(Buffer buffer, int index){
        this.index = index;
        this.buffer = buffer;
    }
    
    public void run(){
        while(true){
            String burguer = this.buffer.consume();
            System.out.println(burguer + " Taken from buffer by Consumer: " + index);
            
            try {
                sleep((int) (Math.random() * 4000));
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
