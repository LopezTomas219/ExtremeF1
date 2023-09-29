package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.Championship;



public class Frame_Create extends Frame_Basic {
    

    private CardLayout cardLayout = new CardLayout();

    public Frame_Create(Championship controller){
        
		super(controller);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(cardLayout);
        Panel_CreateGame panelCreate = new Panel_CreateGame(controller);
        add(panelCreate,"panelCreate");
        JPanel contentCar = new JPanel(new BorderLayout());
        JPanel panelBackCar = new JPanel();
        NeonRoundedButton btnBackCar = new NeonRoundedButton("Volver");
        btnBackCar.setPreferredSize(new Dimension(120, 50));
        btnBackCar.addActionListener(new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent arg0) {
            switchToPanel("panelCreate");
          }
          
        });
        panelBackCar.add(btnBackCar);

        JPanel panelCar = new JPanel(new GridLayout(2, 5));
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("src/resources/images/auto.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < controller.getListCars().size(); i++) {  
          BoxSelect box = new BoxSelect(img,"Marca: "+controller.getListCars().get(i).getMark(),"Modelo: "+controller.getListCars().get(i).getModel(),
          "Velocidad max: "+controller.getListCars().get(i).getMaximumspeed()+"");
          panelCar.add(box);
        }
        panelBackCar.setBackground(Color.decode("#7FFFD4"));
        contentCar.add(panelCar, BorderLayout.CENTER);
        contentCar.add(panelBackCar, BorderLayout.SOUTH);
        add(contentCar,"panelCar");
        JPanel contentPilot = new JPanel(new BorderLayout());
        
        JPanel panelPilot = new JPanel(new GridLayout(2, 5));
        for (int i = 0; i < controller.getListPilots().size(); i++) {  
          BoxSelect box = new BoxSelect(img,"Nombre: "+controller.getListPilots().get(i).getNamepilot(),"Pais: "+controller.getListPilots().get(i).getCountry().getName(),
          "Carreras ganadas: "+controller.getListPilots().get(i).getQuantitycarrerwin()+"");
          panelPilot.add(box);
        }
        JPanel panelBackPilot = new JPanel();
        NeonRoundedButton btnBackPilot = new NeonRoundedButton("Volver");
        btnBackPilot.setPreferredSize(new Dimension(120, 50));
        btnBackPilot.addActionListener(new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent arg0) {
            switchToPanel("panelCreate");
          }
          
        });
        panelBackPilot.add(btnBackPilot);
        panelBackPilot.setBackground(Color.decode("#7FFFD4"));
        contentPilot.add(panelPilot, BorderLayout.CENTER);
        contentPilot.add(panelBackPilot, BorderLayout.SOUTH);
        add(contentPilot,"panelPilot");
    }

    public void switchToPanel(String panel){
      cardLayout.show(getContentPane(), panel);
    }
    
}
