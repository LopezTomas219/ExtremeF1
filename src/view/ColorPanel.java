package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;


public class ColorPanel extends JPanel {
    
    private ActionListener externalListener; 
    private Set<Color> selectedColors; 
    

    private Color selectedColor;
    private Color[] colors = {
        Color.decode("#FF5733"), // Naranja brillante
        Color.decode("#FFA633"), // Naranja
        Color.decode("#FF5A33"), // Naranja oscuro
        Color.decode("#FFDE33"), // Amarillo
        Color.decode("#A6FF33"), // Verde limón
        Color.decode("#33FF57"), // Verde claro
        Color.decode("#D16BE7"), // Rosa pastel
        Color.decode("#D28BE7"), // Lavanda claro
        Color.decode("#8F6BE7"), // Violeta medio
        Color.decode("#696BE7"), // Azul mediano
        Color.decode("#33A6FF"), // Azul cielo
        Color.decode("#5733FF"), // Azul violáceo
        Color.decode("#7A33FF"), // Violeta claro
        Color.decode("#A633FF"), // Morado
        Color.decode("#8533FF"), // Lila
        Color.decode("#DC33FF"), // Lavanda
        Color.decode("#FF33F5"), // Rosa claro
        Color.decode("#FF33A6"), // Rosa brillante
        Color.decode("#FF3360"), // Rosa medio
        Color.decode("#FF3388")  // Rosa fuerte
    };

    public ColorPanel(Set<Color> selectedColors) {

        this.selectedColors = selectedColors;
        setLayout(new GridLayout(4, 6, 5, 5));  // 4 filas, 6 columnas con un espacio de 5 pixels entre botones
        for (Color color : colors) {
            JButton colorButton = new JButton();
            colorButton.setBackground(color);
            if (selectedColors.contains(color)) {
                colorButton.setBackground(darkenColor(color));
                colorButton.setEnabled(false); 
            }
        
            colorButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectedColor = colorButton.getBackground();
            
                    if (!selectedColors.contains(selectedColor)) {
                        if (externalListener != null) {
                            externalListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Color Selected"));
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Este color ya ha sido elegido.", "Color duplicado",
                                JOptionPane.WARNING_MESSAGE);
                    }
            
                   
                }
            });
            
            add(colorButton);
        }
    }

    public Color getSelectedColor() {
        return selectedColor;
    }
    
    public void setExternalListener(ActionListener listener) {
        this.externalListener = listener;
    }
    
    public Color darkenColor(Color color) {
        float factor = 0.2f; 
        int red = (int) (color.getRed() * factor);
        int green = (int) (color.getGreen() * factor);
        int blue = (int) (color.getBlue() * factor);
        return new Color(red, green, blue);
    }
    public Set<Color> getSelectedColors() {
        return selectedColors;
    }

    public void setSelectedColors(Set<Color> selectedColors) {
        this.selectedColors = selectedColors;
    }
    public ArrayList<Color> getColors() {
        return new ArrayList<>(Arrays.asList(colors));
    }
}
