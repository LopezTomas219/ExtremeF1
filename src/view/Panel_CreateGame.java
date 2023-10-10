package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import controller.Championship;
import model.Car;
import model.Pilot;
import model.Player;


public class Panel_CreateGame extends JPanel implements SelectionListener{

    private Championship controller;
    private JLabel titleLabel;
    private JTextField nameField;
    private JLabel nameLabel;
    private JTextField namePlayerField;
    private JLabel namePlayerLabel;
    private NeonRoundedButton addPlayerButton;
    private NeonRoundedButton createTournamentButton;
    private NeonRoundedButton btnBack;
    private Color colorSelect;
    private JLabel imageCar;
    private JLabel imagePilot;
    private int contPlayers;
    private Car carSelect = null;
    private Pilot pilotSelect = null;

    private static Set<Color> selectedColors = new HashSet<>();
    private Set<String> playerNames = new HashSet<>();
    private Set<Car> selectedCars = new HashSet<>();
    private Set<Pilot> selectedPilots = new HashSet<>();

    private static Font customFont;

    static {
        try {
            // Carga la fuente desde el archivo .ttf
            customFont = Font.createFont(Font.TRUETYPE_FONT,
                    NeonRoundedButton.class.getResource("/resources/files/RaceFont.ttf").openStream());
            customFont = customFont.deriveFont(46f); // Ajusta este valor según el tamaño deseado
        } catch (Exception e) {
            e.printStackTrace();
            customFont = new Font("Arial", Font.PLAIN, 14); // Fuente de respaldo en caso de error
        }
    }

    public Panel_CreateGame(Championship controller) {

        this.controller = controller;
        selectedColors.add(Color.decode("#7FFFD4"));
        selectedColors.add(Color.WHITE);
        colorSelect = Color.WHITE;
        contPlayers = 0;
        setLayout(new BorderLayout());
        setBackground(Color.decode("#FFFAF0"));

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
        namePlayerLabel = new JLabel("Nombre del jugador:");
        namePlayerLabel.setFont(customFont.deriveFont(12f));

        centerPanel.add(namePlayerLabel, gbc);

        // Campo de texto
        gbc.gridx = 1;
        namePlayerField = new JTextField(13);
        namePlayerField.setFont(customFont.deriveFont(12f));
        centerPanel.add(namePlayerField, gbc);

        Dimension commonSize = new Dimension(100, 50); // puedes cambiar estos valores según lo que necesites
        Dimension btnSize = new Dimension(105, 40);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel labelAboveSquare = new JLabel("Elegir color");
        centerPanel.add(labelAboveSquare, gbc);

        // Cuadrado de color
        gbc.gridy = 3;
        JPanel coloredSquare = new JPanel();
        coloredSquare.setBackground(Color.RED);
        coloredSquare.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        coloredSquare.setPreferredSize(commonSize);
        coloredSquare.setBackground(Color.WHITE);
        centerPanel.add(coloredSquare, gbc);

        // Botón debajo del cuadrado
        gbc.gridy = 4;
        NeonRoundedButton btnColor = new NeonRoundedButton("Seleccionar");
        btnColor.setPreferredSize(btnSize);
        btnColor.setFont(customFont.deriveFont(7f));
        btnColor.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
        
                JDialog colorDialog = new JDialog();
                colorDialog.setModal(true);  
        
 
                ColorPanel colorPanel = new ColorPanel(selectedColors);
                colorDialog.add(colorPanel);
                
 
                colorPanel.setExternalListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        colorSelect = colorPanel.getSelectedColor();
        
                        if (!selectedColors.contains(colorSelect)) {
                            coloredSquare.setBackground(colorSelect);
                        } else {
                            JOptionPane.showMessageDialog(null, "Este color ya ha sido elegido.", "Color duplicado",
                                    JOptionPane.WARNING_MESSAGE);
                        }
        
                        colorDialog.dispose(); 
                    }
                });
        
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                colorDialog.setPreferredSize(new Dimension(screenSize.width / 2, screenSize.height /2 ));
                colorDialog.pack(); 
                colorDialog.setLocationRelativeTo(null);  
                colorDialog.setVisible(true);  
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
        ImageIcon image1Resized = new ImageIcon(
                image1.getImage().getScaledInstance(commonSize.width, commonSize.height, Image.SCALE_SMOOTH));
        imagePilot = new JLabel(image1Resized);
        imagePilot.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        centerPanel.add(imagePilot, gbc);

        // Botón debajo de la imagen 1
        gbc.gridy = 4;
        NeonRoundedButton btnSelecPilot = new NeonRoundedButton("Seleccionar");
        btnSelecPilot.setPreferredSize(btnSize);
        btnSelecPilot.setFont(customFont.deriveFont(7f));
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
        ImageIcon image2Resized = new ImageIcon(
                image2.getImage().getScaledInstance(commonSize.width, commonSize.height, Image.SCALE_SMOOTH));
        imageCar = new JLabel(image2Resized);
        imageCar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        centerPanel.add(imageCar, gbc);

        // Botón debajo de la imagen 2
        gbc.gridy = 4;
        NeonRoundedButton btnSelectCar = new NeonRoundedButton("Seleccionar");
        btnSelectCar.setPreferredSize(btnSize);
        btnSelectCar.setFont(customFont.deriveFont(7f));
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
        addPlayerButton = new NeonRoundedButton("Agregar jugador");
        
      
        addPlayerButton.setPreferredSize(new Dimension(200, 50));
        addPlayerButton.setFont(customFont.deriveFont(10f));


        centerPanel.add(addPlayerButton, gbc);

        gbc.gridx = 6;
        gbc.gridy = 5;

        btnBack = new NeonRoundedButton("Cancelar");
        btnBack.setPreferredSize(new Dimension(150, 50));
        btnBack.setFont(customFont.deriveFont(10f));
        btnBack.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                int result = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres cancelar? Perderas los jugadores creados",
                        "Confirmación",
                        JOptionPane.YES_NO_OPTION);

                if (result == JOptionPane.YES_OPTION) {
                    controller.getListPlayers().clear();
                    selectedColors.clear();
                    controller.getFrame_create().dispose();
                    controller.StartGame();
                };
            }
        });

        centerPanel.add(btnBack, gbc);
        // Botón "Crear torneo"
        gbc.gridx = 8;
        gbc.gridy = 5;
        createTournamentButton = new NeonRoundedButton("Crear torneo");
        createTournamentButton.setPreferredSize(new Dimension(150, 50));
        createTournamentButton.setFont(customFont.deriveFont(10f));
        centerPanel.add(createTournamentButton, gbc);

        createTournamentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(!nameField.getText().trim().isEmpty() && !controller.getListPlayers().isEmpty()){
                    controller.setNameGame(nameField.getText().trim());
                    System.out.println("Se cre el torneo "+ controller.getNameGame() +"con " + controller.getListPlayers().toString());
                    
                }else {
                    if (nameField.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Complete el nombre del torneo");
                    }else JOptionPane.showMessageDialog(null, "Debe crear minimo un jugador");
                }



            }
        });
        // --------------------Panel jugadores---------------------------------------
        JPanel playersPanel = new JPanel();
        playersPanel.setBackground(Color.decode("#7FFFD4"));
        playersPanel.setLayout(new FlowLayout(FlowLayout.LEFT));



        //Crea los jugadores 
        addPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validatePlayerInfo()) {
                    
                    controller.CreatePlayer(namePlayerField.getText().trim(), colorSelect, pilotSelect, carSelect);
                    playerNames.add(namePlayerField.getText().trim());
                    namePlayerField.setText("");
                    selectedColors.add(colorSelect);
                    coloredSquare.setBackground(Color.WHITE);
                    selectedCars.add(carSelect);
                    carSelect.setSelected(true);
                    selectedPilots.add(pilotSelect);
                    pilotSelect.setSelected(true);
                   
                    BoxPlayer box = new BoxPlayer(controller.getListPlayers().get(contPlayers),controller);
                    contPlayers ++;
                    playersPanel.add(box);
                    playersPanel.revalidate();
                    playersPanel.repaint(); 
                    updateBoxes();
                    
                } else
                    JOptionPane.showMessageDialog(null, "Complete los campos");
            }
        });

        
        this.add(centerPanel, BorderLayout.WEST);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        playersPanel.setPreferredSize(new Dimension((int)screenSize.getWidth(),(int) screenSize.getHeight() / 4));

     
        this.add(playersPanel, BorderLayout.SOUTH);
    }

    public boolean validatePlayerInfo() {
    
        return !(namePlayerField.getText().trim().isEmpty() || 
                 playerNames.contains(namePlayerField.getText().trim()) || 
                 selectedColors.contains(colorSelect) || 
                 selectedCars.contains(carSelect) || 
                 selectedPilots.contains(pilotSelect));
    }
    
    public void updateBoxes(){
        for (BoxSelect boxSelect : controller.getFrame_create().getBoxesCars()) {
            boxSelect.updateBoxState();
        }
        for (BoxSelect boxSelect : controller.getFrame_create().getBoxesPilots()) {
            boxSelect.updateBoxState();
        }
                 
    }
    @Override
    public void onCarSelected(Car car) {
        carSelect = car;
        if (!selectedCars.contains(carSelect)) {
         //   imageCar.setIcon(car.getImage());
            controller.getFrame_create().switchToPanel("panelCreate");
            // Haz lo que necesites hacer con el auto seleccionado.
        } else {
            JOptionPane.showMessageDialog(this, "Este auto ya ha sido seleccionado.", "Auto duplicado", JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    public void onPilotSelected(Pilot pilot) {
        pilotSelect = pilot;
        if (!selectedPilots.contains(pilotSelect)) {
           // imagePilot.setIcon(pilot.getImage());
            controller.getFrame_create().switchToPanel("panelCreate");
            // Haz lo que necesites hacer con el piloto seleccionado.
        } else {
            JOptionPane.showMessageDialog(this, "Este piloto ya ha sido seleccionado.", "Piloto duplicado", JOptionPane.WARNING_MESSAGE);
        }
    }
    public void removePlayer(Player player){
        contPlayers--;
        selectedColors.remove(player.getColor());
        playerNames.remove(player.getName());
        selectedCars.remove(player.getCar());
        selectedPilots.remove(player.getPilot());
        player.getCar().setSelected(false);
        player.getPilot().setSelected(false);
    }

}
