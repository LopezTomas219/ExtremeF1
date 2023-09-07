package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Panel_Init extends JPanel {
    
    private Image backgroundImage;
    private JButton btnCharge;
    public Panel_Init(){


        backgroundImage = new ImageIcon("src/resources/images/F1.jpeg").getImage();
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
        JLabel titleLabel = new JLabel("Extreme F1");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        Font titleFont = new Font("Arial", Font.ITALIC, 45);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setOpaque(false);
        init_panel.add(titleLabel);

        //-------------------------------------------------------------------
        JPanel spacer = new JPanel();
        spacer.setPreferredSize(new Dimension(init_panel.getWidth(), 20));
        spacer.setOpaque(false);
        init_panel.add(spacer);
        Dimension btnSize = new Dimension(120, 40);

        //-------------------------------------------------------------------
        JButton btnStart = new JButton("Iniciar");
        btnStart.setPreferredSize(btnSize);
        JPanel buttonWrapper_Start = new JPanel();
        buttonWrapper_Start.setLayout(new FlowLayout());
        buttonWrapper_Start.setOpaque(false);
        buttonWrapper_Start.add(btnStart);
        
        //-------------------------------------------------------------------
        btnCharge = new JButton("Cargar partida");
        btnCharge.setPreferredSize(btnSize);
        JPanel buttonWrapper_Charge = new JPanel();
        buttonWrapper_Charge.setLayout(new FlowLayout());
        buttonWrapper_Charge.setOpaque(false);
        buttonWrapper_Charge.add(btnCharge);

        //-------------------------------------------------------------------       
        JButton btnOptions = new JButton("Opciones");
        btnOptions.setPreferredSize(btnSize);
        JPanel buttonWrapper_Options = new JPanel();
        buttonWrapper_Options.setLayout(new FlowLayout());
        buttonWrapper_Options.setOpaque(false);
        buttonWrapper_Options.add(btnOptions);
        init_panel.add(buttonWrapper_Start);
        init_panel.add(buttonWrapper_Charge);
        init_panel.add(buttonWrapper_Options);


        //-------------------------------------------------------------------
        main_Panel.add(Box.createVerticalGlue()); // Espacio flexible antes del panel de botones
        main_Panel.add(init_panel);
        main_Panel.add(Box.createVerticalGlue()); // Espacio flexible después del panel de botones

        
        add(main_Panel, BorderLayout.CENTER);
    }

    public JButton getBtnCharge(){
        return btnCharge;
    }
}
