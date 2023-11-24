package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JPanel;

import controller.Championship;

public class Panel_Charge extends JPanel {
    
    private Image backgroundImage;
    private NeonRoundedButton btnBack;
    private Championship controller;
    public Panel_Charge() {
        backgroundImage = new ImageIcon("src/resources/images/Background.png").getImage();
        setLayout(new BorderLayout());

        JPanel main_Panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };
        main_Panel.setLayout(new BoxLayout(main_Panel, BoxLayout.Y_AXIS));

        JPanel spacer = new JPanel();
        spacer.setPreferredSize(new Dimension(main_Panel.getWidth(), 20));
        spacer.setOpaque(false);
        main_Panel.add(spacer);

       
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        buttonPanel.setOpaque(false); 
        Dimension btnDimension = new Dimension(120, 60);
        NeonRoundedButton btnSave1 = new NeonRoundedButton(controller.getFiles().get(0).getName());
        btnSave1.setPreferredSize(btnDimension);
        btnSave1.setNeonColor(Color.decode("#00EF00"));
        NeonRoundedButton btnSave2 = new NeonRoundedButton(controller.getFiles().get(1).getName());
        btnSave2.setPreferredSize(btnDimension);
        btnSave2.setNeonColor(Color.decode("#00EF00"));
        NeonRoundedButton btnSave3 = new NeonRoundedButton(controller.getFiles().get(2).getName());
        btnSave3.setPreferredSize(btnDimension);
        btnSave3.setNeonColor(Color.decode("#00EF00"));
        buttonPanel.add(btnSave1);
        buttonPanel.add(btnSave2);
        buttonPanel.add(btnSave3);

        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        backPanel.setOpaque(false);
        btnBack = new NeonRoundedButton("Volver");
        btnBack.setPreferredSize(btnDimension);
        btnBack.setNeonColor(Color.decode("#DD0000"));
        backPanel.add(btnBack);
      
        
        main_Panel.add(Box.createVerticalGlue());
        main_Panel.add(buttonPanel);
        main_Panel.add(backPanel);
        main_Panel.add(Box.createVerticalGlue());

        add(main_Panel, BorderLayout.CENTER);
    }
    public JButton getBtnBack(){

        return btnBack;
    }
	public Championship getController() {
		return controller;
	}
	public void setController(Championship controller) {
		this.controller = controller;
	}
    
}
   
 