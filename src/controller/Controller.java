
package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import models.Buffer;
import models.Consumer;
import models.Panel;
import models.Producer;
import view.MainView;


public class Controller implements ActionListener{
    
    private MainView view;
    private Buffer buffer;
    private Producer[] producers;
    private Consumer[] consumers;
    
    private boolean producerThreadsCreated = false;
    private boolean consumerThreadsCreated = false;
   
    
    public Controller(MainView view, Buffer buffer){
        this.view = view;
        this.buffer = buffer;
        
        // Event Listeners
        this.view.btn_start.addActionListener(this);
        this.view.btn_addConsumer.addActionListener(this);
        this.view.btn_addProducer.addActionListener(this);
        
    }
    
    public void start(){
        view.setTitle("Consumer-Producer");
        view.setLocationRelativeTo(null);
        
        if (!producerThreadsCreated) {
            createProducers();
            producerThreadsCreated = true;
        }
        
        if (!consumerThreadsCreated) {
            createConsumers();
            consumerThreadsCreated = true;
        }
        
        printProducerStartingPanels();
        printConsumerStartingPanels();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btn_start){
            startProducerThreads();
            startConsumerThreads();
        }
        else if (e.getSource() == view.btn_addConsumer) {
            System.out.println("Btn añadir consumidor");
        }
        else if (e.getSource() == view.btn_addProducer) {
            System.out.println("Btn añadir productor");
        }
    }
    
    public void createProducers() {
        this.producers = new Producer[5];
    
        for (int i = 0; i < this.producers.length; i++) {
            this.producers[i] = new Producer(buffer, i, this); // add same buffer for each producer
        }
    }
    
    public void createConsumers() {
        this.consumers = new Consumer[5];
    
        for (int i = 0; i < this.consumers.length; i++) {
            this.consumers[i] = new Consumer(buffer, i, this);
        }
    }
    
    
    public void startProducerThreads(){
        for (int i = 0; i < producers.length; i++) { 
          Producer producer = producers[i];
            if (!producer.isAlive()) { // Check if the thread is not already running
                producer.start();
                //this.updateGUIStates(i);
            }
        }
    }
    
    public void startConsumerThreads(){
        for (int i = 0; i < consumers.length; i++) { 
          Consumer consumer = consumers[i];
            if (!consumer.isAlive()) { // Check if the thread is not already running
                consumer.start();
                //this.updateGUIStates(i);
            }
        }
    }
    
    public void printProducerStartingPanels(){
        for (int i = 0; i < producers.length; i++) {
            Panel producerPanel = new Panel(i, "producer");
            view.pnl_producerInfo.add(producerPanel);
        }
    }
    
    public void printConsumerStartingPanels(){
        for (int i = 0; i < consumers.length; i++) {
            Panel consumerPanel = new Panel(i, "consumer");
            view.pnl_consumerInfo.add(consumerPanel);
        }
    }
    
    public void updateGUIConsumerStates(int consumerIndex) {
        String newState = consumers[consumerIndex].getStateFlag();
        String route = "/img/" + newState + ".png";
        Panel consumerPanelToChange = null; // Initialize to null

        // Find the Panel corresponding to the consumerIndex
        for (Component component : view.pnl_consumerInfo.getComponents()) {
            if (component instanceof Panel) {
                Panel panel = (Panel) component;
                if (panel.id == consumerIndex) {
                    consumerPanelToChange = panel;
                    break; // Exit the loop once the correct panel is found
                }
            }
        }

        if (consumerPanelToChange != null) {
            consumerPanelToChange.img_label.setIcon(new ImageIcon(getClass().getResource(route)));
        }
    }
    
    public void updateGUIConsumerText(int consumerIndex, String textToAppend) {
        String newState = consumers[consumerIndex].getStateFlag();
        String route = "/img/" + newState + ".png";
        Panel consumerPanelToChange = null; // Initialize to null

        // Find the Panel corresponding to the consumerIndex
        for (Component component : view.pnl_consumerInfo.getComponents()) {
            if (component instanceof Panel) {
                Panel panel = (Panel) component;
                if (panel.id == consumerIndex) {
                    consumerPanelToChange = panel;
                    break; // Exit the loop once the correct panel is found
                }
            }
        }

        if (consumerPanelToChange != null) {
            JTextArea textAreaToChange = consumerPanelToChange.txtArea; // Assuming you have a public reference to the JTextArea in your Panel class
        
            // Append the text and set the caret position to the end
            textAreaToChange.append(textToAppend);
            textAreaToChange.setCaretPosition(textAreaToChange.getDocument().getLength());
        }
    }
    
    public void updateGUIProducerStates(int producerIndex) {
        String newState = producers[producerIndex].getStateFlag();
        String route = "/img/" + newState + ".png";
        Panel producerPanelToChange = null; // Initialize to null

        // Find the Panel corresponding to the consumerIndex
        for (Component component : view.pnl_producerInfo.getComponents()) {
            if (component instanceof Panel) {
                Panel panel = (Panel) component;
                if (panel.id == producerIndex) {
                    producerPanelToChange = panel;
                    break; // Exit the loop once the correct panel is found
                }
            }
        }

        if (producerPanelToChange != null) {
            producerPanelToChange.img_label.setIcon(new ImageIcon(getClass().getResource(route)));
        }
    }
    
    public void updateGUIProducerText(int producerIndex, String textToAppend) {
        String newState = producers[producerIndex].getStateFlag();
        String route = "/img/" + newState + ".png";
        Panel producerPanelToChange = null; // Initialize to null

        // Find the Panel corresponding to the consumerIndex
        for (Component component : view.pnl_producerInfo.getComponents()) {
            if (component instanceof Panel) {
                Panel panel = (Panel) component;
                if (panel.id == producerIndex) {
                    producerPanelToChange = panel;
                    break; // Exit the loop once the correct panel is found
                }
            }
        }

        if (producerPanelToChange != null) {
            JTextArea textAreaToChange = producerPanelToChange.txtArea; // Assuming you have a public reference to the JTextArea in your Panel class
        
            // Append the text and set the caret position to the end
            textAreaToChange.append(textToAppend);
            textAreaToChange.setCaretPosition(textAreaToChange.getDocument().getLength());
        }
    }
    
    public void updateGUIBuffer(int burguerQuantity){
        String image = "/img/burguer.png";
        switch(burguerQuantity){
            case 0:
                view.img_burguer1.setIcon(new ImageIcon(getClass().getResource("")));
                view.img_burguer2.setIcon(new ImageIcon(getClass().getResource("")));
                view.img_burguer3.setIcon(new ImageIcon(getClass().getResource("")));
                view.img_burguer4.setIcon(new ImageIcon(getClass().getResource("")));
                view.img_burguer5.setIcon(new ImageIcon(getClass().getResource("")));
                view.img_burguer6.setIcon(new ImageIcon(getClass().getResource("")));
                break;
            case 1:
                view.img_burguer1.setIcon(new ImageIcon(getClass().getResource(image)));
                view.img_burguer2.setIcon(new ImageIcon(getClass().getResource("")));
                view.img_burguer3.setIcon(new ImageIcon(getClass().getResource("")));
                view.img_burguer4.setIcon(new ImageIcon(getClass().getResource("")));
                view.img_burguer5.setIcon(new ImageIcon(getClass().getResource("")));
                view.img_burguer6.setIcon(new ImageIcon(getClass().getResource("")));
                break;
            case 2:
                view.img_burguer1.setIcon(new ImageIcon(getClass().getResource(image)));
                view.img_burguer2.setIcon(new ImageIcon(getClass().getResource(image)));
                view.img_burguer3.setIcon(new ImageIcon(getClass().getResource("")));
                view.img_burguer4.setIcon(new ImageIcon(getClass().getResource("")));
                view.img_burguer5.setIcon(new ImageIcon(getClass().getResource("")));
                view.img_burguer6.setIcon(new ImageIcon(getClass().getResource("")));
                break;
            case 3:
                view.img_burguer1.setIcon(new ImageIcon(getClass().getResource(image)));
                view.img_burguer2.setIcon(new ImageIcon(getClass().getResource(image)));
                view.img_burguer3.setIcon(new ImageIcon(getClass().getResource(image)));
                view.img_burguer4.setIcon(new ImageIcon(getClass().getResource("")));
                view.img_burguer5.setIcon(new ImageIcon(getClass().getResource("")));
                view.img_burguer6.setIcon(new ImageIcon(getClass().getResource("")));
                break;
            case 4:
                view.img_burguer1.setIcon(new ImageIcon(getClass().getResource(image)));
                view.img_burguer2.setIcon(new ImageIcon(getClass().getResource(image)));
                view.img_burguer3.setIcon(new ImageIcon(getClass().getResource(image)));
                view.img_burguer4.setIcon(new ImageIcon(getClass().getResource(image)));
                view.img_burguer5.setIcon(new ImageIcon(getClass().getResource("")));
                view.img_burguer6.setIcon(new ImageIcon(getClass().getResource("")));
                break;
            case 5:
                view.img_burguer1.setIcon(new ImageIcon(getClass().getResource(image)));
                view.img_burguer2.setIcon(new ImageIcon(getClass().getResource(image)));
                view.img_burguer3.setIcon(new ImageIcon(getClass().getResource(image)));
                view.img_burguer4.setIcon(new ImageIcon(getClass().getResource(image)));
                view.img_burguer5.setIcon(new ImageIcon(getClass().getResource(image)));
                view.img_burguer6.setIcon(new ImageIcon(getClass().getResource("")));
                break;
            case 6:
                view.img_burguer1.setIcon(new ImageIcon(getClass().getResource(image)));
                view.img_burguer2.setIcon(new ImageIcon(getClass().getResource(image)));
                view.img_burguer3.setIcon(new ImageIcon(getClass().getResource(image)));
                view.img_burguer4.setIcon(new ImageIcon(getClass().getResource(image)));
                view.img_burguer5.setIcon(new ImageIcon(getClass().getResource(image)));
                view.img_burguer6.setIcon(new ImageIcon(getClass().getResource(image)));
                break;
        }
    }
}
