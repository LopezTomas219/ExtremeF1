package view;

import javax.swing.JPanel;

import model.Player;

import java.awt.Graphics;
import java.util.List;

public class RacePanel extends JPanel {

    private List<Player> players;
    private int currentLap;
    private int totalLaps;

    public RacePanel(List<Player> players, int totalLaps) {
        this.players = players;
        this.currentLap = 0;
        this.totalLaps = totalLaps;
    }

    public void updateRaceData(List<Player> players, int currentLap) {
        this.players = players;
        this.currentLap = currentLap;
        repaint(); // Vuelve a dibujar el panel con los nuevos datos
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int y = 20; // Posición inicial en el eje Y

        // Dibuja la información de cada jugador
        for (Player player : players) {
            g.drawString(player.getName() + " - Lap: " + currentLap + " / " + totalLaps, 20, y);
            y += 20; // Incrementa la posición para el siguiente jugador
        }
    }
}
