package view;

import javax.swing.*;
import java.awt.*;


public class Panel_Select extends JPanel {
    private JLabel imageLabel = new JLabel();

    public Panel_Select() {
        setLayout(new BorderLayout());

        // Crea un ImageIcon desde la imagen
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/resources/images/auto.png"));

        // Asigna el ImageIcon al JLabel y ajusta la imagen al tamaño del JLabel
        imageLabel.setIcon(imageIcon);
        imageLabel.setHorizontalAlignment(JLabel.CENTER); // Centra la imagen en el JLabel

        // Agrega el JLabel al panel
        add(imageLabel, BorderLayout.CENTER);

        JPanel labelPanel = new JPanel(new GridLayout(3, 1));

        JLabel modelLabel = new JLabel("car.getMark()", SwingConstants.CENTER);
        JLabel speedLabel = new JLabel("car.getMaximumspeed()", SwingConstants.CENTER);
        JLabel acelerationLabel = new JLabel("car.getAceleration()", SwingConstants.CENTER);

        labelPanel.add(modelLabel);
        labelPanel.add(speedLabel);
        labelPanel.add(acelerationLabel);

        add(labelPanel, BorderLayout.SOUTH);
    }
}

