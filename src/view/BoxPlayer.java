package view;

import javax.swing.*;
import java.awt.*;

public class BoxPlayer extends JPanel {

    private static Font customFont;

    static {
        try {
            // Carga la fuente desde el archivo .ttf
            customFont = Font.createFont(Font.TRUETYPE_FONT, NeonRoundedButton.class.getResource("/resources/files/RaceFont.ttf").openStream());
            customFont = customFont.deriveFont(12f);  // Ajusta este valor según el tamaño deseado
        } catch (Exception e) {
            e.printStackTrace();
            customFont = new Font("Arial", Font.PLAIN, 14);  // Fuente de respaldo en caso de error
        }
    }
    // Constructor
    public BoxPlayer(Color color ,String namePlayer,String namePilot,String mark) {
        // Layout principal
        setLayout(new BorderLayout());

        // Panel de texto a la izquierda
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        JLabel Data1 = new JLabel(namePlayer);
        Data1.setFont(customFont);
        Data1.setForeground(Color.white);
        JLabel Data2 = new JLabel(namePilot);
        Data2.setFont(customFont);
        Data2.setForeground(Color.white);
        JLabel Data3 = new JLabel(mark);
        Data3.setFont(customFont);
        Data3.setForeground(Color.white);

        textPanel.add(Data1);
        textPanel.add(Data2);
        textPanel.add(Data3);

        // Panel de imagen a la derecha
        textPanel.setOpaque(false);
        JPanel imagePanel = new JPanel();

        // Suponiendo que tienes una imagen llamada "imagen.jpg" en el mismo directorio
        ImageIcon icon = new ImageIcon("src/resources/images/auto.png");
        JLabel imageLabel = new JLabel(icon);
        imagePanel.setOpaque(false);
        imagePanel.add(imageLabel);

        // Agregar ambos paneles al panel principal
        add(textPanel, BorderLayout.WEST);
        add(imagePanel, BorderLayout.EAST);

        // Botón debajo de los dos paneles
        NeonRoundedButton button = new NeonRoundedButton("Modificar");
        button.setNeonColor(color);
        add(button, BorderLayout.SOUTH);
        setBackground(color);
    }

}



