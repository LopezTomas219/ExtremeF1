package view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BoxSelect extends JPanel {


    private static Font customFont;

    static {
        try {
            // Carga la fuente desde el archivo .ttf
            customFont = Font.createFont(Font.TRUETYPE_FONT, NeonRoundedButton.class.getResource("/resources/files/RaceFont.ttf").openStream());
            customFont = customFont.deriveFont(10f);  // Ajusta este valor según el tamaño deseado
        } catch (Exception e) {
            e.printStackTrace();
            customFont = new Font("Arial", Font.PLAIN, 14);  // Fuente de respaldo en caso de error
        }
    }

    public BoxSelect(BufferedImage img,String Data1 , String Data2 , String Data3) {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    
        
    
        // Panel para la imagen centrada
        JPanel imagePanel = new JPanel(new BorderLayout());
        JComponent imageComponent = new JComponent() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (img != null) {
                    g.drawImage(img, (getWidth() - img.getWidth()) / 2, (getHeight() - img.getHeight()) / 2, null);
                }
            }
        };
        imagePanel.add(imageComponent, BorderLayout.CENTER);
        // Crear bordes para la parte superior, izquierda y derecha
        Border top = new MatteBorder(2, 0, 0, 0, Color.BLACK);  // 2 píxeles de ancho en la parte superior
        Border left = new MatteBorder(0, 2, 0, 0, Color.BLACK); // 2 píxeles de ancho en la izquierda
        Border right = new MatteBorder(0, 0, 0, 2, Color.BLACK); // 2 píxeles de ancho en la derecha
        Border south= new MatteBorder(0, 0, 0, 2, Color.BLACK); // 2 píxeles de ancho en la derecha

        // Combinar los bordes
        CompoundBorder compound_img = new CompoundBorder(left, top);
        compound_img = new CompoundBorder(compound_img, right);

        imagePanel.setBorder(compound_img);
        imagePanel.setBackground(Color.decode("#7FFFD4"));
        add(imagePanel);
    
        // Panel para las descripciones y el botón
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
    
        // Panel para las descripciones
        JPanel descriptionPanel = new JPanel(new GridLayout(3, 1));
    
        JLabel desc1 = new JLabel(Data1);
        desc1.setHorizontalAlignment(JLabel.CENTER);
        desc1.setFont(customFont);
        descriptionPanel.add(desc1);
    
        JLabel desc2 = new JLabel(Data2);
        desc2.setHorizontalAlignment(JLabel.CENTER);
        desc2.setFont(customFont);
        descriptionPanel.add(desc2);
    
        JLabel desc3 = new JLabel(Data3);
        desc3.setHorizontalAlignment(JLabel.CENTER);
        desc3.setFont(customFont);
        descriptionPanel.add(desc3);
        descriptionPanel.setBackground(Color.decode("#7FFFD4"));
        contentPanel.add(descriptionPanel);
    
        // Botón de seleccionar
        NeonRoundedButton selectButton = new NeonRoundedButton("Seleccionar");
        selectButton.setNeonColor(Color.decode("#00FFFF"));
        // JPanel con FlowLayout centrado para el botón
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);  // Para que no tenga un fondo diferente al del BoxSelect
        buttonPanel.add(selectButton);
    
        contentPanel.add(buttonPanel);
        CompoundBorder compound_content = new CompoundBorder(left, south);
        compound_content = new CompoundBorder(compound_content, right);

        contentPanel.setBorder(compound_content);
        contentPanel.setBackground(Color.decode("#7FFFD4"));
        add(contentPanel);
    }
}
