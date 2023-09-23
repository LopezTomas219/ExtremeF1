package view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class NeonRoundedButton extends JButton {
    private Color neonColor = Color.CYAN;
    private Shape shape;
    private int arcWidth = 20;  // Controla el redondeo de los bordes
    private int arcHeight = 20;
    private boolean isHovering = false;  // Nuevo: rastrea el estado "hover"

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

    public NeonRoundedButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setOpaque(false);
        setForeground(Color.WHITE);  // Color del texto
        setBorderPainted(false);
        setFocusPainted(false);
        setFont(customFont); 

        // Nuevo: Agrega el ChangeListener para detectar el estado "hover"
        this.addChangeListener(e -> {
            ButtonModel model = getModel();
            if (model.isRollover()) {
                isHovering = true;
            } else {
                isHovering = false;
            }
            repaint();  // Redibuja el botón cuando el estado de hover cambia
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Efecto hover: hacer el botón más brillante cuando el ratón pasa por encima
        Color currentNeonColor = isHovering ? neonColor.brighter() : neonColor;

        // Dibuja el efecto de neón
        g2d.setColor(currentNeonColor);
        g2d.setStroke(new BasicStroke(4.0f));
        g2d.drawRoundRect(2, 2, getWidth() - 4, getHeight() - 4, arcWidth, arcHeight);

        // Dibuja el fondo del botón
        g2d.setColor(currentNeonColor.darker().darker());
        g2d.fillRoundRect(4, 4, getWidth() - 8, getHeight() - 8, arcWidth, arcHeight);
        
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        // No dibujamos un borde específico ya que el efecto de neón lo reemplaza
    }

    @Override
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);
        }
        return shape.contains(x, y);
    }

    // Nuevo: Método para cambiar el color de neón
    public void setNeonColor(Color newNeonColor) {
        this.neonColor = newNeonColor;
        repaint();
    }
}
