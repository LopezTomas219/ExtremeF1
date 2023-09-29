package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Panel_Init extends JPanel {
    
    private Image backgroundImage;
    private NeonRoundedButton btnStart;
    private NeonRoundedButton btnCharge;
    private NeonRoundedButton btnClose;
    public Panel_Init(){


        backgroundImage = new ImageIcon("src/resources/images/Background.png").getImage();
        setLayout(new BorderLayout());


        JPanel main_Panel = new JPanel(){

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };
        main_Panel.setLayout(new BoxLayout(main_Panel, BoxLayout.Y_AXIS));
        JPanel init_panel = new JPanel();
        init_panel.setLayout(new GridLayout(5, 1, 5, 5));
        init_panel.setBackground(Color.BLACK);

        //init_panel.setMaximumSize(new Dimension(this.getWidth() / 2, this.getHeight() / 2)); 
        init_panel.setOpaque(false); 
        
        //-------------------------------------------------------------------
        JPanel spacer = new JPanel();
        spacer.setPreferredSize(new Dimension(init_panel.getWidth(), 75));
        spacer.setOpaque(false);
        init_panel.add(spacer);
        JPanel spacer2= new JPanel();
        spacer2.setPreferredSize(new Dimension(init_panel.getWidth(), 75));
        spacer2.setOpaque(false);
        init_panel.add(spacer2);

        //-------------------------------------------------------------------
        Dimension btnSize = new Dimension(170, 50);
        
        btnStart = new NeonRoundedButton("Iniciar");
        btnStart.setNeonColor(Color.decode("#00EF00"));
        btnStart.setPreferredSize(btnSize);
          
        JPanel buttonWrapper_Start = new JPanel();
        buttonWrapper_Start.setLayout(new FlowLayout());
        buttonWrapper_Start.setOpaque(false);
        buttonWrapper_Start.add(btnStart);
        
        //-------------------------------------------------------------------
        btnCharge = new NeonRoundedButton("Cargar partida");
        btnCharge.setNeonColor(Color.decode("#F8E600"));
        btnCharge.setPreferredSize(btnSize);
        
        JPanel buttonWrapper_Charge = new JPanel();
        buttonWrapper_Charge.setLayout(new FlowLayout());
        buttonWrapper_Charge.setOpaque(false);
        buttonWrapper_Charge.add(btnCharge);

        //-------------------------------------------------------------------       
        btnClose = new NeonRoundedButton("Salir");
        btnClose.setNeonColor(Color.decode("#DD0000"));
        btnClose.setPreferredSize(btnSize);
        
        JPanel buttonWrapper_Close = new JPanel();
        buttonWrapper_Close.setLayout(new FlowLayout());
        buttonWrapper_Close.setOpaque(false);
        buttonWrapper_Close.add(btnClose);
        init_panel.add(buttonWrapper_Start);
        init_panel.add(buttonWrapper_Charge);
        init_panel.add(buttonWrapper_Close);


        //-------------------------------------------------------------------
        main_Panel.add(Box.createVerticalGlue()); // Espacio flexible antes del panel de botones
        main_Panel.add(init_panel);
        main_Panel.add(Box.createVerticalGlue()); // Espacio flexible después del panel de botones

        
        add(main_Panel, BorderLayout.CENTER);
    }

    public JButton getBtnCharge(){
        return btnCharge;
    }
    public JButton getBtnStart(){
        return btnStart;
    }
    public JButton getBtnClose() {
    	return btnClose;
    }
}
