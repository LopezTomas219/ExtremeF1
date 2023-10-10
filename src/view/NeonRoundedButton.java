package view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class NeonRoundedButton extends JButton {
    private Color neonColor = Color.CYAN;
    private Shape shape;
    private int arcWidth = 20; 
    private int arcHeight = 20;
    private boolean isHovering = false;  
    private static Font customFont;

    static {
        try {
           
            customFont = Font.createFont(Font.TRUETYPE_FONT, NeonRoundedButton.class.getResource("/resources/files/RaceFont.ttf").openStream());
            customFont = customFont.deriveFont(10f);  
        } catch (Exception e) {
            e.printStackTrace();
            customFont = new Font("Arial", Font.PLAIN, 14);  
        }
    }

    public NeonRoundedButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setOpaque(false);
        setForeground(Color.WHITE); 
        setBorderPainted(false);
        setFocusPainted(false);
        setFont(customFont); 


        this.addChangeListener(e -> {
            ButtonModel model = getModel();
            if (model.isRollover()) {
                isHovering = true;
            } else {
                isHovering = false;
            }
            repaint();  
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


        Color currentNeonColor = isHovering ? neonColor.brighter() : neonColor;

   
        g2d.setColor(currentNeonColor);
        g2d.setStroke(new BasicStroke(4.0f));
        g2d.drawRoundRect(2, 2, getWidth() - 4, getHeight() - 4, arcWidth, arcHeight);

     
        g2d.setColor(currentNeonColor.darker().darker());
        g2d.fillRoundRect(4, 4, getWidth() - 8, getHeight() - 8, arcWidth, arcHeight);
        
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        
    }

    @Override
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);
        }
        return shape.contains(x, y);
    }

   
    public void setNeonColor(Color newNeonColor) {
        this.neonColor = newNeonColor;
        repaint();
    }
}
