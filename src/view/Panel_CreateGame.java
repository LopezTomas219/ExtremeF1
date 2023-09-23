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

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Panel_CreateGame extends JPanel {
    
    private JLabel titleLabel;
    private JTextField NameField;
    private JLabel nameLabel;
    private JButton addPlayerButton;
    private JButton createTournamentButton;
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

    public Panel_CreateGame() {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#FFFAF0"));
        // Crear título y agregarlo al norte
        titleLabel = new JLabel("Crear campeonato", SwingConstants.CENTER);
        titleLabel.setFont(customFont);
        int top = 60, left = 20, bottom = 20, right = 20;
        titleLabel.setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));

        add(titleLabel, BorderLayout.NORTH);

        // JPanel con GridBagLayout para distribuir los componentes
        JPanel centerPanel = new JPanel(new GridBagLayout());
        
        centerPanel.setBackground(Color.decode("#FFFAF0"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre los componentes
        gbc.anchor = GridBagConstraints.WEST;
        
        // Etiqueta "Nombre del jugador"
        gbc.gridx = 0;
        gbc.gridy = 0;
        nameLabel = new JLabel("Nombre del jugador:");
        nameLabel.setFont(customFont.deriveFont(12f));

        centerPanel.add(nameLabel, gbc);

        // Campo de texto
        gbc.gridx = 1;
        NameField = new JTextField(13);
        NameField.setFont(customFont.deriveFont(12f));
        centerPanel.add(NameField, gbc);
        
        Dimension commonSize = new Dimension(100, 50);  // puedes cambiar estos valores según lo que necesites


        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel labelAboveSquare = new JLabel("Elegir color");
        centerPanel.add(labelAboveSquare, gbc);
        
        // Cuadrado de color
        gbc.gridy = 2;
        JPanel coloredSquare = new JPanel();
        coloredSquare.setBackground(Color.RED);
        coloredSquare.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        coloredSquare.setPreferredSize(commonSize);
        centerPanel.add(coloredSquare, gbc);
        
        // Botón debajo del cuadrado
        gbc.gridy = 3;
        JButton buttonBelowSquare = new JButton("Seleccionar");
        centerPanel.add(buttonBelowSquare, gbc);
        
        // Etiqueta arriba de la imagen 1
        gbc.gridx = 1;
        gbc.gridy = 1;
        JLabel labelAboveImage1 = new JLabel("Elegir Piloto");
        centerPanel.add(labelAboveImage1, gbc);
        
        // Imagen 1 
        gbc.gridy = 2;
        ImageIcon image1 = new ImageIcon("src/resources/images/auto.png");
        ImageIcon image1Resized = new ImageIcon(image1.getImage().getScaledInstance(commonSize.width, commonSize.height, Image.SCALE_SMOOTH));
        JLabel imageLabel1 = new JLabel(image1Resized);
        imageLabel1.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));

        centerPanel.add(imageLabel1, gbc);
        
        // Botón debajo de la imagen 1
        gbc.gridy = 3;
        JButton button1 = new JButton("Seleccionar");
        centerPanel.add(button1, gbc);
        
        // Etiqueta arriba de la imagen 2
        gbc.gridx = 2; 
        gbc.gridy = 1;
        JLabel labelAboveImage2 = new JLabel("Elegir Auto");
        centerPanel.add(labelAboveImage2, gbc);
        
        // Imagen 2 a la derecha de la imagen 1
        gbc.gridy = 2;
        ImageIcon image2 = new ImageIcon("src/resources/images/auto.png");
        ImageIcon image2Resized = new ImageIcon(image2.getImage().getScaledInstance(commonSize.width, commonSize.height, Image.SCALE_SMOOTH));
        JLabel imageLabel2 = new JLabel(image2Resized);
        imageLabel2.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));

        centerPanel.add(imageLabel2, gbc);
        
        // Botón debajo de la imagen 2
        gbc.gridy = 3;
        JButton button2 = new JButton("Seleccionar");
        centerPanel.add(button2, gbc);
        
        // Botón "Agregar jugador"
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; // Se extiende por dos columnas
        addPlayerButton = new JButton("Agregar jugador");
        centerPanel.add(addPlayerButton, gbc);

        // Botón "Crear torneo"
        gbc.gridx = 5; 
        gbc.gridy = 4;
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

        // Agregar el panel con alineación a la izquierda en el centro del panel principal
        this.add(centerPanel, BorderLayout.WEST);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Crear Juego");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.add(new Panel_CreateGame());
        frame.setVisible(true);
    }
}
/* Nombre de la partida
 * cantidad de personas que van a jugar
 * cada uno: ingresa nombre - avatar/color - piloto - auto 
 */