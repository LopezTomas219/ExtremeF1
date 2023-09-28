package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.ResourceBundle.Control;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.Championship;
import model.Car;
import model.Pilot;

public class Panel_CreateGame extends JPanel {
    
    private JLabel titleLabel;
    private JTextField nameField;
    private JLabel nameLabel;
    private JButton addPlayerButton;
    private JButton createTournamentButton;
    private JButton btnBack;
    private Color colorSelect;
    private Car carSelect;
    private Pilot pilotSelect;
    private List<Pilot> ListPilots;
    private List<Car> ListCars;
    private static Set<Color> chosenColors = new HashSet<>();
    private static Font customFont;
    
    static {
        try {
            // Carga la fuente desde el archivo .ttf
            customFont = Font.createFont(Font.TRUETYPE_FONT, NeonRoundedButton.class.getResource("/resources/files/RaceFont.ttf").openStream());
            customFont = customFont.deriveFont(46f);  // Ajusta este valor según el tamaño deseado
        } catch (Exception e) {
            e.printStackTrace();
            customFont = new Font("Arial", Font.PLAIN, 14);  // Fuente de respaldo en caso de error
        }
    }

    public Panel_CreateGame(Championship controller) {

        ListCars = new ArrayList<>(controller.getListCars());
        ListPilots = new ArrayList<>(controller.getListPilots());

        chosenColors.add(Color.decode("#7FFFD4"));
        setLayout(new BorderLayout());
        setBackground(Color.decode("#FFFAF0"));
        // Crear título y agregarlo al norte
        titleLabel = new JLabel("Crear Campeonato", SwingConstants.CENTER);
        titleLabel.setFont(customFont);
        int top = 60, left = 20, bottom = 20, right = 20;
        titleLabel.setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
        setBackground(Color.decode("#7FFFD4"));
        add(titleLabel, BorderLayout.NORTH);

        // JPanel con GridBagLayout para distribuir los componentes
        JPanel centerPanel = new JPanel(new GridBagLayout());
        
        centerPanel.setBackground(Color.decode("#7FFFD4"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre los componentes
        gbc.anchor = GridBagConstraints.WEST;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        nameLabel = new JLabel("Nombre del torneo:");
        nameLabel.setFont(customFont.deriveFont(12f));

        centerPanel.add(nameLabel, gbc);

        // Campo de texto
        gbc.gridx = 1;
        nameField = new JTextField(13);
        nameField.setFont(customFont.deriveFont(12f));
        centerPanel.add(nameField, gbc);

        // Etiqueta "Nombre del jugador"
        gbc.gridx = 0;
        gbc.gridy = 1;
        nameLabel = new JLabel("Nombre del jugador:");
        nameLabel.setFont(customFont.deriveFont(12f));

        centerPanel.add(nameLabel, gbc);

        // Campo de texto
        gbc.gridx = 1;
        nameField = new JTextField(13);
        nameField.setFont(customFont.deriveFont(12f));
        centerPanel.add(nameField, gbc);
        
        Dimension commonSize = new Dimension(100, 50);  // puedes cambiar estos valores según lo que necesites


        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel labelAboveSquare = new JLabel("Elegir color");
        centerPanel.add(labelAboveSquare, gbc);
        
        // Cuadrado de color
        gbc.gridy = 3;
        JPanel coloredSquare = new JPanel();
        coloredSquare.setBackground(Color.RED);
        coloredSquare.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        coloredSquare.setPreferredSize(commonSize);
        centerPanel.add(coloredSquare, gbc);
        
        // Botón debajo del cuadrado
        gbc.gridy = 4;
        JButton btnColor = new JButton("Seleccionar");
        btnColor.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
               
                colorSelect = JColorChooser.showDialog(null, "Elige un color", Color.WHITE);
                
                if (colorSelect != null) {
                    if (!chosenColors.contains(colorSelect)) {
                        coloredSquare.setBackground(colorSelect);
                        chosenColors.add(colorSelect);
                    } else {
                        JOptionPane.showMessageDialog(null, "Este color ya ha sido elegido.", "Color duplicado", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        
        });
        centerPanel.add(btnColor, gbc);
        
        // Etiqueta arriba de la imagen 1
        gbc.gridx = 1;
        gbc.gridy = 2;
        JLabel lbPilot = new JLabel("Elegir Piloto");
        centerPanel.add(lbPilot, gbc);
        
        // Imagen 1 
        gbc.gridy = 3;
        ImageIcon image1 = new ImageIcon("src/resources/images/auto.png");
        ImageIcon image1Resized = new ImageIcon(image1.getImage().getScaledInstance(commonSize.width, commonSize.height, Image.SCALE_SMOOTH));
        JLabel imagePilot = new JLabel(image1Resized);
        imagePilot.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));

        centerPanel.add(imagePilot, gbc);
        
        // Botón debajo de la imagen 1
        gbc.gridy = 4;
        JButton btnSelecPilot = new JButton("Seleccionar");
        btnSelecPilot.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
               controller.SelectPilot();
            }
            
        });
        centerPanel.add(btnSelecPilot, gbc);
        
        // Etiqueta arriba de la imagen 2
        gbc.gridx = 2; 
        gbc.gridy = 2;
        JLabel lbCar = new JLabel("Elegir Auto");
        centerPanel.add(lbCar, gbc);
        
        // Imagen 2 a la derecha de la imagen 1
        gbc.gridy = 3;
        ImageIcon image2 = new ImageIcon("src/resources/images/auto.png");
        ImageIcon image2Resized = new ImageIcon(image2.getImage().getScaledInstance(commonSize.width, commonSize.height, Image.SCALE_SMOOTH));
        JLabel imageCar = new JLabel(image2Resized);
        imageCar.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));

        centerPanel.add(imageCar, gbc);
        
        // Botón debajo de la imagen 2
        gbc.gridy = 4;
        JButton btnSelectCar = new JButton("Seleccionar");
        btnSelectCar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
               controller.SelectCar();
            }
        });
        centerPanel.add(btnSelectCar, gbc);
        
        // Botón "Agregar jugador"
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2; // Se extiende por dos columnas
        addPlayerButton = new JButton("Agregar jugador");
        centerPanel.add(addPlayerButton, gbc);

        gbc.gridx = 6; 
        gbc.gridy = 5;

        btnBack = new JButton("Cancelar");
        btnBack.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
              
                int result = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres cancelar?", "Confirmación", 
                JOptionPane.YES_NO_OPTION);
    
            if (result == JOptionPane.YES_OPTION) {
                controller.getFrame_create().dispose();
                controller.StartGame();
            };
            }
          });

        centerPanel.add(btnBack, gbc);
        // Botón "Crear torneo"
        gbc.gridx = 8; 
        gbc.gridy = 5;
        createTournamentButton = new JButton("Crear torneo");
        centerPanel.add(createTournamentButton, gbc);

        
        // Configurar listeners para los botones (aquí solo son stubs, debes agregar el código real según lo necesites)
        addPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Código al presionar "Agregar jugador"
            }
        });

        createTournamentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Código al presionar "Crear torneo"
            }
        });
        //--------------------Panel jugadores---------------------------------------
        JPanel playersPanel = new JPanel();


        // Agregar el panel con alineación a la izquierda en el centro del panel principal
        this.add(centerPanel, BorderLayout.WEST);
    }

    

}
