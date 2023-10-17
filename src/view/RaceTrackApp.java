package view;

import javax.swing.*;
import java.awt.*;

class RaceTrackPanel extends JPanel {
    private TrackElement[][] pista = new TrackElement[10][10];

    public RaceTrackPanel() {
       
               
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        pista[i][j] = new Decoration();
                    }
                }
        
                for (int j = 2; j < 8; j++) {
                    pista[9][j] = new Pit();  // Línea de pits
                }
        
                // Dibujar los dos carriles internos
                for (int i = 1; i < 9; i++) {
                    for (int j = 1; j < 9; j++) {
                        // Excluyendo la región central para que quede un espacio entre los carriles
                        if (i > 2 && i < 7 && j > 2 && j < 7) {
                            continue;
                        }
                        boolean top = i == 1;
                        boolean bottom = i == 8;
                        boolean left = j == 1;
                        boolean right = j == 8;
        
                        pista[i][j] = new Road(new boolean[]{top, right, bottom, left});  // Carriles
                    }
                }
        
                if (pista[1][1] instanceof Road) {
                    ((Road) pista[1][1]).placeCar();
                } else {
                    System.out.println("pista[1][1] no es un objeto de tipo Road!");
                }
            } 

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < pista.length; i++) {
            for (int j = 0; j < pista[i].length; j++) {
                pista[i][j].draw(g, j * 50, i * 50);
            }
        }
    }
}

abstract class TrackElement {
    public abstract void draw(Graphics g, int x, int y);
}

class Road extends TrackElement {
    private boolean hasCar;
    private boolean[] drawBorders; // top, right, bottom, left
    private Color carColor = Color.RED; // Default car color
    
    public Road(boolean[] drawBorders) {
        this.drawBorders = drawBorders;
    }

    @Override
    public void draw(Graphics g, int x, int y) {
        g.setColor(Color.GRAY); // Representing road color
        g.fillRect(x, y, 50, 50);

        g.setColor(Color.WHITE);
        
        if (drawBorders[0]) { // Borde superior
            for (int i = 0; i < 3; i++) {
                g.fillRect(x + i * 15, y, 10, 4);
            }
        }
        
        if (drawBorders[2]) { // Borde inferior
            for (int i = 0; i < 3; i++) {
                g.fillRect(x + i * 15, y + 46, 10, 4);
            }
        }

        if (drawBorders[3]) { // Borde izquierdo
            for (int i = 0; i < 3; i++) {
                g.fillRect(x, y + i * 15, 4, 10);
            }
        }

        if (drawBorders[1]) { // Borde derecho
            for (int i = 0; i < 3; i++) {
                g.fillRect(x + 46, y + i * 15, 4, 10);
            }
        }

        if (hasCar) {
            g.setColor(carColor);
            g.fillOval(x + 10, y + 10, 30, 30);
        }
    }

    public void placeCar() {
        this.hasCar = true;
    }

    public void removeCar() {
        this.hasCar = false;
    }
}


class Pit extends TrackElement {
    @Override
    public void draw(Graphics g, int x, int y) {
        g.setColor(Color.YELLOW); // Representing pit color
        g.fillRect(x, y, 50, 50);
    }
}

class Decoration extends TrackElement {
    @Override
    public void draw(Graphics g, int x, int y) {
        g.setColor(Color.GREEN); // Representing grass or other decoration
        g.fillRect(x, y, 50, 50);
    }
}

public class RaceTrackApp {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Race Track Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800); // Ajusta el tamaño de acuerdo a las dimensiones de la pista.
        frame.add(new RaceTrackPanel());
        frame.setVisible(true);
    }
}
