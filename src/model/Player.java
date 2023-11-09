package model;

import java.awt.Color;

public class Player implements Comparable<Player> ,Runnable{

private String name;
private Color color ;
private Pilot pilot;
private Car car;
private Circuit circuit;
private float distance;


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


public Circuit getCircuit() {
	return circuit;
}


public void setCircuit(Circuit circuit) {
	this.circuit = circuit;
}


public float getDistance() {
	return distance;
}


public void setDistance(float distance) {
	this.distance = distance;
}



@Override
public int compareTo(Player other) {
	return this.name.compareTo(other.name);
}


@Override
public String toString() {
	return  name + "  " + color + " " + pilot + " " + car + "\n";
}


@Override
public void run() {
	
	int timeTotal = 0;
	int lap = 0;
	while (lap < circuit.getNumberflaps()) {
		int time = 0;
		while (distance < circuit.getTracklength()) {
			time++;
			distance = calculateMove(time);
			car.carUpdate();
	
			System.out.println("Jugador " + name + " en movimiento. Distancia recorrida: " + distance + ", Tiempo: " + time + " segundos");
			
			
			try {
				Thread.sleep(1000); // Simula un segundo de acción
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
		timeTotal += time;
		distance = 0;
		lap++;
	
		System.out.println("Jugador " + name + " ha terminado la vuelta en " + time + " segundos.");
		
	}

	System.out.println("Jugador " + name + " ha terminado la carrera en " + timeTotal + " segundos.");
	throw new UnsupportedOperationException("Unimplemented method 'run'");
}
public float calculateVelocity(){
	return 40; //Calcular con la velocidad del auto y del piloto
}
public float calculateMove(int time){
	return calculateVelocity() * time;
}

}


