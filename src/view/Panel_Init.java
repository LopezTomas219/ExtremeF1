package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

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
        spacer.setPreferredSize(new Dimension(init_panel.getWidth(), 50));
        spacer.setOpaque(false);
        init_panel.add(spacer);
        Dimension btnSize = new Dimension(150, 50);

        //-------------------------------------------------------------------
        ImageIcon iconImagen = new ImageIcon("src/resources/images/btnIniciar.png");
        JButton btnStart = new JButton() {
            private Shape shape;
        
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
                // Dibuja el fondo redondeado
                if (getModel().isPressed()) {
                    g2.setColor(Color.LIGHT_GRAY);
                } else {
                    g2.setColor(getBackground());
                }
                g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                
                // Dibuja la imagen
                g2.drawImage(iconImagen.getImage(), (getWidth() - iconImagen.getIconWidth()) / 2, (getHeight() - iconImagen.getIconHeight()) / 2, this);
        
                super.paintComponent(g);
            }
        
            @Override
            protected void paintBorder(Graphics g) {
                g.setColor(getForeground());
                g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
            }
        
            @Override
            public boolean contains(int x, int y) {
                if (shape == null || !shape.getBounds().equals(getBounds())) {
                    shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                }
                return shape.contains(x, y);
            }
        };
        
        btnStart.setContentAreaFilled(false);
        btnStart.setBorderPainted(false);
        btnStart.setFocusPainted(false);
        btnStart.setBackground(Color.decode("#00EF00")); // Color de fondo
        btnStart.setForeground(Color.WHITE); // Color del texto y del borde
        btnStart.setFocusable(false);
        btnStart.setPreferredSize(new Dimension(iconImagen.getIconWidth(), iconImagen.getIconHeight()));
        
       
        
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
        JButton btnClose = new JButton("Salir");
        btnClose.setPreferredSize(btnSize);
        JPanel buttonWrapper_Options = new JPanel();
        buttonWrapper_Options.setLayout(new FlowLayout());
        buttonWrapper_Options.setOpaque(false);
        buttonWrapper_Options.add(btnClose);
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
