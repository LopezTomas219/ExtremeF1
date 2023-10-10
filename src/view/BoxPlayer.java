package view;

import javax.swing.*;

import controller.Championship;
import model.Player;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoxPlayer extends JPanel {

    private static Font customFont;
    private Player player;
    private Championship controller;
    
    static {
        try {
        
            customFont = Font.createFont(Font.TRUETYPE_FONT, NeonRoundedButton.class.getResource("/resources/files/RaceFont.ttf").openStream());
            customFont = customFont.deriveFont(10f); 
        } catch (Exception e) {
            e.printStackTrace();
            customFont = new Font("Arial", Font.PLAIN, 10); 
        }
    }
    
    public BoxPlayer(Player player,Championship controller) {
        
        this.player=player;
        this.controller = controller;
        setLayout(new BorderLayout());
        Dimension boxSize = new Dimension(325, 75);
        this.setPreferredSize(boxSize);
        this.setMinimumSize(boxSize);
        this.setMaximumSize(boxSize);
        

        // Panel de texto a la izquierda
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        JLabel Data1 = new JLabel("Jugador: " + player.getName());
        Data1.setFont(customFont);
        Data1.setForeground(Color.white);
        JLabel Data2 = new JLabel("Piloto: " + player.getPilot().getNamepilot());
        Data2.setFont(customFont);
        Data2.setForeground(Color.white);
        JLabel Data3 = new JLabel("Marca: " + player.getCar().getMark());
        Data3.setFont(customFont);
        Data3.setForeground(Color.white);
        JLabel Data4 = new JLabel("Modelo: " + player.getCar().getModel());
        Data4.setFont(customFont);
        Data4.setForeground(Color.white);

        textPanel.add(Data1);
        textPanel.add(Data2);
        textPanel.add(Data3);
        textPanel.add(Data4);

        // Panel de imagen a la derecha
        textPanel.setOpaque(false);
        JPanel imagePanel = new JPanel();

        ImageIcon icon = new ImageIcon("src/resources/images/auto.png");
        JLabel imageLabel = new JLabel(icon);
        imagePanel.setOpaque(false);
        imagePanel.add(imageLabel);

       
        add(textPanel, BorderLayout.WEST);
        add(imagePanel, BorderLayout.EAST);

        
        NeonRoundedButton button = new NeonRoundedButton("Eliminar");
        button.setNeonColor(player.getColor());
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                controller.removePlayer(BoxPlayer.this);
            }
        });
        add(button, BorderLayout.SOUTH);
        setBackground(player.getColor());
    }
    public Player getPlayer(){
        return player;
    }
    public Championship getController() {
        return controller;
    }
    public void setController(Championship controller) {
        this.controller = controller;
    }
}