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
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import controller.Championship;
import model.Hard;
import model.Medium;
import model.Player;
import model.Soft;
import model.Tires;
import model.Weathercondition;
import model.Wet;
import model.DriveMode;

public class Panel_CustomizeOptions extends JPanel {

    public Championship getController() {
		return controller;
	}

	public void setController(Championship controller) {
		this.controller = controller;
	}

	private Championship controller;
    private JLabel titleLabel;
    private JComboBox<String> fuelComboBox;
    private JComboBox<String> wheelsComboBox;
    private JComboBox<String> driveModeComboBox;
    private NeonRoundedButton confirmButton;
    private int numberPlayers = 0;

    private static Font customFont;

    static {
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT,
                    NeonRoundedButton.class.getResource("/resources/files/RaceFont.ttf").openStream());
            customFont = customFont.deriveFont(46f);
        } catch (Exception e) {
            e.printStackTrace();
            customFont = new Font("Arial", Font.PLAIN, 14);
        }
    }

    public Panel_CustomizeOptions(Championship controller) {
    	this.controller = controller;
        setLayout(new BorderLayout());
        setBackground(Color.decode("#7FFFD4"));

        titleLabel = new JLabel("Seleccionar opciones", SwingConstants.CENTER);
        titleLabel.setFont(customFont);
        int top = 60, left = 20, bottom = 20, right = 20;
        titleLabel.setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
        setBackground(Color.decode("#7FFFD4"));
        add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Color.decode("#7FFFD4"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel playerLabel = new JLabel ("Jugador: " + controller.getListPlayers().get(numberPlayers).getName());
        playerLabel.setFont(customFont);
        centerPanel.add(playerLabel, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel fuelLabel = new JLabel("Cantidad de combustible:");
        centerPanel.add(fuelLabel, gbc);

        gbc.gridx = 1;
        String[] fuelOptions = {"1/4 tanque", "1/2 tanque", "3/4 tanque", "Tanque lleno"};
        fuelComboBox = new JComboBox<>(fuelOptions);
        centerPanel.add(fuelComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel wheelsLabel = new JLabel("Tipo de neumáticos:");
        centerPanel.add(wheelsLabel, gbc);

        gbc.gridx = 1;
        String[] wheelsOptions = {"Soft", "Hard", "Medium", "Wet"};
        wheelsComboBox = new JComboBox<>(wheelsOptions);
        centerPanel.add(wheelsComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel driveModeLabel = new JLabel("Modo de manejo:");
        centerPanel.add(driveModeLabel, gbc);

        gbc.gridx = 1;
        String[] driveModeOptions = {"Rápido", "Conservador", "Moderado"};
        driveModeComboBox = new JComboBox<>(driveModeOptions);
        centerPanel.add(driveModeComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        confirmButton = new NeonRoundedButton("Confirmar");
        confirmButton.setPreferredSize(new Dimension(200, 50));
        confirmButton.setFont(customFont.deriveFont(10f));
        centerPanel.add(confirmButton, gbc);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedFuel = (String) fuelComboBox.getSelectedItem();
                int fuelPercentage;
                switch (selectedFuel) {
                case "1/4 tanque":
                	fuelPercentage = 25;
                	break;
                case "1/2 tanque":
                	fuelPercentage = 50;
                	break;
                case "3/4 tanque":
                	fuelPercentage = 75;
                	break;
                case "Tanque lleno":
                	fuelPercentage = 100;
                	break;
                default:
                	fuelPercentage = 100;
                }
                String selectedWheels = (String) wheelsComboBox.getSelectedItem();
                Tires selectedTires;
                switch (selectedWheels) {
                case "Soft":
                	selectedTires = new Soft();
                	break;
                case "Hard":
                	selectedTires = new Hard();
                	break;
                case "Medium":
                	selectedTires = new Medium();
                	break;
                case "Wet":
                	Weathercondition weathercondition = new Weathercondition(); // este habría que traerlo del circuito o de la carrera y pasarle la condicion climatica y la temperatura
                	selectedTires = new Wet(weathercondition);
                	break;
                default:
                	selectedTires = new Medium();
                }
                String selectedDriveMode = (String) driveModeComboBox.getSelectedItem();
                DriveMode driveMode;
                switch(selectedDriveMode) {
                case "Rápido":
                	driveMode = DriveMode.fast;
                	break;
                case "Conservador":
                	driveMode = DriveMode.conservative;
                	break;
                case "Moderado":
                	driveMode = DriveMode.moderate;
                	break;
                default:
                	driveMode = DriveMode.moderate;
                }
                
                if (numberPlayers < controller.getNumberPlayers()) {
                	controller.getListPlayers().get(numberPlayers).getCar().setFuel(fuelPercentage);
                	controller.getListPlayers().get(numberPlayers).getCar().setTires(selectedTires);
                	controller.getListPlayers().get(numberPlayers).getCar().setDriveMode(driveMode);
                	if (++numberPlayers < controller.getNumberPlayers()) {
                		playerLabel.setText("Jugador: " + controller.getListPlayers().get(numberPlayers).getName());
                	}
                } else {
                	// pasa al sig panel Frame_Race;
                }

            }
        });

        this.add(centerPanel, BorderLayout.CENTER);
    }
}