package models;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    public int id;
    public String kindOfPanel;
    public JPanel img_panel;
    public JLabel img_label;
    public JTextArea txtArea;
    
    public Panel(int id, String kindOfPanel) {
        this.id = id;
        this.kindOfPanel = kindOfPanel;
        
        setLayout(new BorderLayout());

        // Create a panel to hold the squared label with FlowLayout
        this.img_panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.img_panel.setPreferredSize(new Dimension(100, 150)); // Increased height for label
        this.img_panel.setMaximumSize(new Dimension(100, 150)); // Ensure a fixed size

        // Create a squared label
        this.img_label = new JLabel("");
        this.img_label.setPreferredSize(new Dimension(100, 100));
        this.img_label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.img_label.setIcon(new ImageIcon(getClass().getResource("/img/standing.png")));

        // Create a label below the img_label
        JLabel consumerLabel = new JLabel(kindOfPanel + (id+1));

        // Add the squared label and consumer label to the label panel
        this.img_panel.add(img_label);
        this.img_panel.add(consumerLabel);

        // Create a textbox with a scroll pane on the right
        this.txtArea = new JTextArea(10, 20);
        JScrollPane scrollPane = new JScrollPane(this.txtArea);

        // Add components to the panel
        add(this.img_panel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
    }
}
