package model;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import observer.EngineObserver;
import observer.FuelObserver;
import observer.TiresObserver;

public class Player implements Comparable<Player> ,Runnable ,FuelObserver , TiresObserver , EngineObserver , Serializable{

private String name;
private Color color ;
private Pilot pilot;
private Car car;
private Race race;
private int points;
private List<Race> ListRaces = new ArrayList<>();
private float distance;
private int overtakes;
private float timeAdd;
private float TimeQualifying = 0;
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


public float getTimeQualifying() {
	return TimeQualifying;
}

public void setTimeQualifying(float timeQualifying) {
	TimeQualifying = timeQualifying;
}

public Car getCar() {
	return car;
}


public void setCar(Car car) {
	this.car = car;
}

public Race getRace() {
	return race;
}

public void setRace(Race race) {
	this.race = race;
}

public int getPoints() {
	return points;
}

public void setPoints(int points) {
	this.points = points;
}

public int getOvertakes() {
	return overtakes;
}

public void setOvertakes(int overtakes) {
	this.overtakes = overtakes;
}

public float getTimeAdd() {
	return timeAdd;
}

public void setTimeAdd(float timeAdd) {
	this.timeAdd = timeAdd;
}

public List<Race> getListRaces() {
	return ListRaces;
}

public void setListRaces(List<Race> listRaces) {
	ListRaces = listRaces;
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
	timeAdd = 0;
	int numberLap = 0;
	
	while (numberLap <race.getCircuit().getNumberflaps()) {
		double timelap = 0;
		overtakes = 0;
		while (numberLap == 0) { //este while es solamente para sumar el tiempo del inicio

			
			nexPosition(position,0.5);
			if (race.getCircuit().getCircuitMap()[position.getRow()][position.getCol()].isStartFinish()) {
				race.movePlayer(this, overtakes, position.getRow(), position.getCol(),timelap , numberLap);
				numberLap++;
			}else{
				race.movePlayer(this, overtakes, position.getRow(), position.getCol(),timelap , numberLap);
			}
				timelap += 0.5;

				try {
					Thread.sleep(500); // Simula 0.5 segundos de acción
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			
		}
		while (!race.getCircuit().getCircuitMap()[position.getRow()][position.getCol()].isStartFinish()) {
			nexPosition(position,0.5);
			race.movePlayer(this, overtakes, position.getRow(), position.getCol(),timelap,numberLap);
			timelap += 0.5;
			race.weatherconditionChange();
			try {
				Thread.sleep(500); // Simula 0.5 segundos de acción
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		numberLap ++;
		timeTotal += timelap;
		
	
		System.out.println("Jugador " + name + " ha terminado la vuelta en " + timelap + " segundos.");
		
	}

	System.out.println("Jugador " + name + " ha terminado la carrera en " + timeTotal + " segundos.");
	throw new UnsupportedOperationException("Unimplemented method 'run'");
}
public double calculateVelocity(){
	float timeRecord =race.getCircuit().getTimerecord().getSecond();
	return (race.getCircuit().getTracklength() * timeRecord) * car.calculateVelocity() * pilot.calculateProperties(); //Calcular con la velocidad del auto y del piloto
}
public double calculateMove(double time){
	return calculateVelocity() * time;
}
public position nexPosition(position position, double time){
	int direction = race.getCircuit().getCircuitMap()[position.getRow()][position.getCol()].getDirection();
	int moveCell = (int)calculateMove(time) / 46;
	switch (direction) {
		case 1: {
			if (race.getCircuit().getCircuitMap()[position.getRow()][position.getCol() + moveCell].getType() != 3) {
				position.setCol(position.getCol() + moveCell); // verificar que no se pase del limite
			}
			position.setCol(position.getCol() + moveCell - 1);
		}
			break;
		case 2: {
			if (race.getCircuit().getCircuitMap()[position.getRow() + moveCell][position.getCol()].getType() != 3) {
				position.setRow(position.getRow() + moveCell); // verificar que no se pase del limite
			}
			position.setRow(position.getRow() + moveCell - 1);
		}
			break;
		case 3: {
			if (race.getCircuit().getCircuitMap()[position.getRow()][position.getCol() - moveCell].getType() != 3) {
				position.setCol(position.getCol() - moveCell); // verificar que no se pase del limite
			}
			position.setCol(position.getCol() - moveCell + 1);
		}
			break;
		case 4: {
			if (race.getCircuit().getCircuitMap()[position.getRow() - moveCell][position.getCol()].getType() != 3) {
				position.setRow(position.getRow() - moveCell); // verificar que no se pase del limite
			}
			position.setRow(position.getRow() - moveCell + 1);
		}
			break;
		
		default:
			break;
	}
	return position;
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

public void addPoints(int i) {
	points += i;
	
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
	public void setRow(int row){
		this.row = row; 
	}
	
	public int getRow(){
		return row;
	}
	
	public void setCol(int col){
		this.col = col;
	}
	public int getCol(){
		return col;
	}
	public void resetPosition(){
		this.row = 0;
		this.col = 0;
	}

}