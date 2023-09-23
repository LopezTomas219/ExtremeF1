package model;

public class Player implements Comparable<Player>{

private String name;
private String color ;
private Pilot pilot;
private Car car;
//Tiene un tiempo por vuelta 

public Player(String name, String color, Pilot pilot, Car car) {
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


public String getColor() {
	return color;
}


public void setColor(String color) {
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

@Override
public String toString() {
	return  name + "  " + color + " " + pilot + " " + car ;
}
}
