/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego
 */
public class Buffer {
    private String[] buffer;
    private int next;
    private boolean isEmpty;
    private boolean isFull;
    
    public Buffer(int size){
        this.buffer = new String[size];
        this.next = 0;
        this.isEmpty = true;
        this.isFull = false;
    }
    
    public synchronized String consume(int consumerIndex, Consumer consumer){
        while(this.isEmpty){
            try {
                consumer.setStateFlag("sleeping");
                consumer.controller.updateGUIConsumerText(consumerIndex, "Consumer " + (consumerIndex+1) + " sleeping\n");
                consumer.controller.updateGUIConsumerStates(consumerIndex); //Update GUI
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        consumer.eating();
        
        next--;
        this.isFull = false;
        if(next == 0){
            this.isEmpty = true;
        }
        
        notifyAll();
        return this.buffer[this.next];
    }
    
    public synchronized void produce(String burguer, int producerIndex, Producer producer){
        while(this.isFull){
            try {
                producer.setStateFlag("sleeping");
                producer.controller.updateGUIProducerText(producerIndex, "Producer " + (producerIndex+1) + " sleeping\n");
                producer.controller.updateGUIProducerStates(producerIndex); //Update GUI
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        producer.cooking();
        
        buffer[next] = burguer;
        next++;
        this.isEmpty = false;
        if(next == this.buffer.length){
            this.isFull = true;
        }
        
        notifyAll();
    }
}
