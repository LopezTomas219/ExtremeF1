package view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;

import model.Car;
import model.Pilot;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class BoxSelect extends JPanel {

    private Car car;
    private Pilot pilot;
    private JLabel Line1,Line2,Line3;
    private NeonRoundedButton selectButton ;
    private SelectionListener selectionListener;
 

    private JPanel contentPanel,imagePanel,descriptionPanel;

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

    public BoxSelect(BufferedImage img,SelectionListener selectionListener) {
        this.selectionListener = selectionListener;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        // Panel para la imagen centrada
        imagePanel = new JPanel(new BorderLayout());
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
        
        Border top = new MatteBorder(2, 0, 0, 0, Color.BLACK); 
        Border left = new MatteBorder(0, 2, 0, 0, Color.BLACK); 
        Border right = new MatteBorder(0, 0, 0, 2, Color.BLACK); 
        Border south= new MatteBorder(0, 0, 0, 2, Color.BLACK); 

       
        CompoundBorder compound_img = new CompoundBorder(left, top);
        compound_img = new CompoundBorder(compound_img, right);

        imagePanel.setBorder(compound_img);
        imagePanel.setBackground(Color.decode("#7FFFD4"));
        add(imagePanel);
    
        // Panel para las descripciones y el botón
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
    
        // Panel para las descripciones
        descriptionPanel = new JPanel(new GridLayout(3, 1));
    
        Line1 = new JLabel();
        Line1.setHorizontalAlignment(JLabel.CENTER);
        Line1.setFont(customFont);
        descriptionPanel.add(Line1);
    
        Line2 = new JLabel();
        Line2.setHorizontalAlignment(JLabel.CENTER);
        Line2.setFont(customFont);
        descriptionPanel.add(Line2);
    
        Line3 = new JLabel();
        Line3.setHorizontalAlignment(JLabel.CENTER);
        Line3.setFont(customFont);
        descriptionPanel.add(Line3);
        descriptionPanel.setBackground(Color.decode("#7FFFD4"));
        contentPanel.add(descriptionPanel);
    
       
        selectButton = new NeonRoundedButton("Seleccionar");
        selectButton.setNeonColor(Color.decode("#00FFFF"));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        selectButton.setPreferredSize(new Dimension(150, 40));
        selectButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (car != null && selectionListener != null) {
                    selectionListener.onCarSelected(car);
                } else if (pilot != null && selectionListener != null) {
                    selectionListener.onPilotSelected(pilot);
                }
            }
        });
        buttonPanel.setOpaque(false);
        buttonPanel.add(selectButton);
    
        contentPanel.add(buttonPanel);
        CompoundBorder compound_content = new CompoundBorder(left, south);
        compound_content = new CompoundBorder(compound_content, right);

        contentPanel.setBorder(compound_content);
        contentPanel.setBackground(Color.decode("#7FFFD4"));
        add(contentPanel);
    }
      // Constructor para autos
      public BoxSelect(Car car, BufferedImage img,SelectionListener selectionListener) {
        this(img,selectionListener);
        this.car = car;
        updateContent("Marca: " + car.getMark(), "Modelo: " + car.getModel(), "Velocidad max: " + car.getMaximumspeed());
    }

    // Constructor para pilotos
    public BoxSelect(Pilot pilot, BufferedImage img,SelectionListener selectionListener) {
        this(img,selectionListener);
        this.pilot = pilot;
        updateContent("Nombre: " + pilot.getNamepilot(), "Pais: " + pilot.getCountry().getName(), "Carreras ganadas: " + pilot.getQuantitycarrerwin());
      
    }

    private void updateContent(String data1, String data2, String data3) {
        Line1.setText(data1);
        Line2.setText(data2);
        Line3.setText(data3);
    }
    public void updateBoxState() {
        if (isCar()) {
            if (car.isSelected()) {

                imagePanel.setBackground(Color.decode("#1abc9c"));  
                descriptionPanel.setBackground(Color.decode("#1abc9c"));  
                contentPanel.setBackground(Color.decode("#1abc9c"));  
            } else {
                imagePanel.setBackground(Color.decode("#7FFFD4"));  
                descriptionPanel.setBackground(Color.decode("#7FFFD4"));  
                contentPanel.setBackground(Color.decode("#7FFFD4"));  
            }
        }else 
            if (pilot.isSelected()) {

                imagePanel.setBackground(Color.decode("#1abc9c"));  
                descriptionPanel.setBackground(Color.decode("#1abc9c"));  
                contentPanel.setBackground(Color.decode("#1abc9c"));  
            } else {
                imagePanel.setBackground(Color.decode("#7FFFD4"));  
                descriptionPanel.setBackground(Color.decode("#7FFFD4"));  
                contentPanel.setBackground(Color.decode("#7FFFD4"));  
            }
        
    }
  


    public Car getCar() {
        return car;
    }

    public Pilot getPilot() {
        return pilot;
    }

    public boolean isCar() {
        return car != null;
    }

    public boolean isPilot() {
        return pilot != null;
    }

    public NeonRoundedButton getBtn(){
        return selectButton;
    }   
    public SelectionListener getSelectionListener() {
        return selectionListener;
    }
    public void setSelectionListener(SelectionListener selectionListener) {
        this.selectionListener = selectionListener;
    }
}

