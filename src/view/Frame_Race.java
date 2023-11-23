package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;

import controller.Championship;
import model.Player;
import javax.swing.JSlider;


public class Frame_Race extends Frame_Basic {
	
	private JPanel panelPrincipal;
	private JPanel panelSimulacionCarrera;
	private JPanel panelJugadores;
	private JTextArea textAreaPosiciones;

	public Frame_Race(Championship controller) {
		super(controller);
		
		panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BorderLayout());
		panelSimulacionCarrera = new JPanel();
		
		panelJugadores = new JPanel();
		panelJugadores.setLayout(new GridLayout(0,1));
	
		List <Player> realPlayers = controller.getListPlayers();
		for (Player player : realPlayers) {
			PlayerPanelBox playerPanelBox = new PlayerPanelBox(player);
			panelJugadores.add(playerPanelBox);
		}
		
		textAreaPosiciones = new JTextArea();
		textAreaPosiciones.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textAreaPosiciones);
		
		panelPrincipal.add(panelSimulacionCarrera, BorderLayout.NORTH);
		panelPrincipal.add(panelJugadores,BorderLayout.SOUTH);
		panelPrincipal.add(scrollPane, BorderLayout.EAST);
		
		add(panelPrincipal);
	
		setVisible(true);
	}
}

class PlayerPanelBox extends JPanel{
	
	public PlayerPanelBox (Player player) {
		
		setLayout(new GridLayout(0,2));
		JLabel nameLabel = new JLabel ("Nombre: " + player.getName());
		JLabel velocityPlayer = new JLabel ("Velocidad: " + player.calculateVelocity());
		JSlider sliderFuel = new JSlider((int) player.getCar().getFuel());
		JSlider sliderTires = new JSlider(player.getCar().getTires().getDurability());
		JSlider sliderEngine = new JSlider(player.getCar().getEngineStatus());
		JButton refuel = new JButton("Recargar");
		JButton changeTires = new JButton("Cambiar ruedas");
		JButton fixEngine = new JButton("Reparar motor");
		
		add(nameLabel);
		add(velocityPlayer);
		add(new JLabel("Nivel de combustible:"));
		add(sliderFuel);
		add(new JLabel("Durabilidad de neumáticos:"));
		add(sliderTires);	
		add(new JLabel("Estado del motor:"));
		add(sliderEngine);
		add(refuel);
		add(changeTires);
		add(fixEngine);
	}
}
