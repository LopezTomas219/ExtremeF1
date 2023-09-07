package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;

import java.awt.Image;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Panel_Charge extends JPanel {
    
    private Image backgroundImage;
    private JButton btnBack;
    public Panel_Charge() {
        backgroundImage = new ImageIcon("src/resources/images/F1.jpeg").getImage();
        setLayout(new BorderLayout());

        JPanel main_Panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };
        main_Panel.setLayout(new BoxLayout(main_Panel, BoxLayout.Y_AXIS));

        // Panel del Título
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        JLabel titleLabel = new JLabel("Extreme F1");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        Font titleFont = new Font("Arial", Font.ITALIC, 45);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(Color.BLACK);
        titlePanel.add(titleLabel);
        
        

        // Panel de Botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        buttonPanel.setOpaque(false); 
        Dimension btnDimension = new Dimension(120, 60);
        JButton btnSave1 = new JButton("Partida 1");
        btnSave1.setPreferredSize(btnDimension);
        JButton btnSave2 = new JButton("Partida 2");
        btnSave2.setPreferredSize(btnDimension);
        JButton btnSave3 = new JButton("Vacio");
        btnSave3.setPreferredSize(btnDimension);
        buttonPanel.add(btnSave1);
        buttonPanel.add(btnSave2);
        buttonPanel.add(btnSave3);

        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        backPanel.setOpaque(false);
        btnBack = new JButton("Volver");
        btnBack.setPreferredSize(new Dimension(120, 40));
        backPanel.add(btnBack);
        // Agregar todos los paneles a main_Panel
        main_Panel.add(Box.createVerticalGlue());
        main_Panel.add(titlePanel);
        main_Panel.add(buttonPanel);
        main_Panel.add(backPanel);
        main_Panel.add(Box.createVerticalGlue());

        add(main_Panel, BorderLayout.CENTER);
    }
    public JButton getBtnBack(){

        return btnBack;
    }
}
   
 