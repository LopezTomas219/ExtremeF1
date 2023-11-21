package model;

import java.awt.Color;

import observer.EngineObserver;
import observer.FuelObserver;
import observer.TiresObserver;

public class Player implements Comparable<Player> ,Runnable ,FuelObserver , TiresObserver , EngineObserver{

private String name;
private Color color ;
private Pilot pilot;
private Car car;
private Circuit circuit;
private float distance;
private position position = new position();


public Player(String name, Color color, Pilot pilot, Car car) {
	super();
	this.name = name;
	this.color = color;
	this.pilot = pilot;
	this.car = car;
	
	car.addFuelObserver(this);
	car.addTiresObserver(this);
	car.addEngineObserver(this);
}

//--------------------------------Getter/Setters-------------------------------------------------//

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

public position getPosition() {
	return position;
}

public void setPosition(position position) {
	this.position = position;
}

//---------------------------------------------------------------------------------//

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
	
	double timeTotal = 0;
	int lap = 0;
	boolean startLap = false;
	while (lap < circuit.getNumberflaps()) {
		double timelap = 0;
		if (!startLap) {
			timelap = timelap + 0.5;

		}
		while (startLap) {
			timelap = timelap + 0.5;
			
			car.carUpdate();
	
			System.out.println("Jugador " + name + " en movimiento. Distancia recorrida: " + distance + ", Tiempo: " + time + " segundos");
			
			
			try {
				Thread.sleep(500); // Simula 0.5 segundos de acción
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
		timeTotal += time;
		lap++;
	
		System.out.println("Jugador " + name + " ha terminado la vuelta en " + time + " segundos.");
		
	}

	System.out.println("Jugador " + name + " ha terminado la carrera en " + timeTotal + " segundos.");
	throw new UnsupportedOperationException("Unimplemented method 'run'");
}
public float calculateVelocity(){
	float timeRecord = circuit.getTimerecord().getSecond();
	return (circuit.getTracklength() * timeRecord) * car.calculateVelocity() * pilot.calculateProperties(); //Calcular con la velocidad del auto y del piloto
}
public float calculateMove(int time){
	return calculateVelocity() * time;
}

//--------------------------------Observers-------------------------------------------------//
@Override
public void lowFuel() {
	// TODO Auto-generated method stub
	
}


@Override
public void tiresWear() {
	// TODO Auto-generated method stub
	
}


@Override
public void engineWear() {
	// TODO Auto-generated method stub
	
}




}


/**
 * position
 */
 class position {

	private int row ,col ; 
	public position(){}
	
	public void setPosition(int row , int col){
		this.row = row;
		this.col = col;
	}
	public int getRow(){
		return row;
	}
	public int getCol(){
		return col;
	}
	public void resetPosition(){
		this.row = 0;
		this.col = 0;
	}

}