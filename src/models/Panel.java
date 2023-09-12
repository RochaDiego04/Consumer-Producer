package models;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    int id;
    private String kindOfPanel;
    
    public Panel(int id, String kindOfPanel) {
        this.id = id;
        this.kindOfPanel = kindOfPanel;
        setLayout(new BorderLayout());

        // Create a panel to hold the squared label with FlowLayout
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        labelPanel.setPreferredSize(new Dimension(100, 150)); // Increased height for label
        labelPanel.setMaximumSize(new Dimension(100, 150)); // Ensure a fixed size

        // Create a squared label
        JLabel squaredLabel = new JLabel("");
        squaredLabel.setPreferredSize(new Dimension(100, 100));
        squaredLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Create a label below the img_label
        JLabel consumerLabel = new JLabel(kindOfPanel + (id+1));

        // Add the squared label and consumer label to the label panel
        labelPanel.add(squaredLabel);
        labelPanel.add(consumerLabel);

        // Create a textbox with a scroll pane on the right
        JTextArea textArea = new JTextArea(10, 20);
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Add components to the panel
        add(labelPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
    }
}
