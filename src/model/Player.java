package model;

import java.awt.Color;

public class Player implements Comparable<Player>{

private String name;
private Color color ;
private Pilot pilot;
private Car car;
private Circuit circuit;
private int currentLap = 0;
private float currentPosition = 0.0f;
//Tiene un tiempo por vuelta 

public Player(String name, Color color, Pilot pilot, Car car) {
	super();
	this.name = name;
	this.color = color;
	this.pilot = pilot;
	this.car = car;
}


public String getName() {
	return name;
}


public void setName(String name) {
	this.name = name;
}


public Color getColor() {
	return color;
}


public void setColor(Color color) {
	this.color = color;
}


public Pilot getPilot() {
	return pilot;
}


public void setPilot(Pilot pilot) {
	this.pilot = pilot;
}


public Car getCar() {
	return car;
}


public void setCar(Car car) {
	this.car = car;
}
@Override
public int compareTo(Player other) {
	return this.name.compareTo(other.name);
}

public Circuit getCircuit() {
	return circuit;
}


public void setCircuit(Circuit circuit) {
	this.circuit = circuit;
}

public float getVelocity() {
	long totalSeconds = circuit.getTimerecord().toSecondOfDay();
	return ((circuit.getTracklength() / (float) totalSeconds)) * getCar().calculateVelocity() * getPilot().calculateProperties();
}

public float getTotalRace() {
	return circuit.getNumberflaps()*circuit.getTracklength();
}

public void move(float distanceMoved) {
    // Simula el movimiento del jugador en la pista
    currentPosition += distanceMoved;

    // Verifica si el jugador ha completado todas las vueltas
    if (currentLap < circuit.getNumberflaps()) {
        if (currentPosition >= circuit.getTracklength()) {
            currentLap++;
            currentPosition -= circuit.getTracklength();
            System.out.println(name + " ha completado la vuelta " + currentLap);
        }
    } else {
        // El jugador ha completado todas las vueltas, puedes detener el movimiento
        System.out.println(name + " ha terminado la carrera");
        // Detener el hilo o marcar al jugador como terminado, dependiendo de tu implementación.
    }
}


@Override
public String toString() {
	return  name + "  " + color + " " + pilot + " " + car + "\n";
}


}
