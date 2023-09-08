package model;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

public class Car {
    private int num;
	private String mark;
	private float maximumspeed; //km/hs
	private float aceleration; // 0 a 100 km/h
	private float power; //HP
	private float weight ; 
	private float fuelconsum; // consumo promedio por litros cada 100km
	private Tires tires;
	//private driveMode drivemode;//El drivemode no se construira ya que ira en una funcion la cual seria 
    //asignarModo 
	//caracteristicas del 0 al 100
	
	private int overtakingperformance; //Determina c�mo se desempe�a el auto en aceleraciones r�pidas 	y/o rectas prolongadas para sobrepasar a otros autos
	private int corneringperformance; //Determina c�mo es el comportamiento del auto en las curvas

	private int reliability; //Determina qu� tan confiable es el auto: A menor valor de este atributo, 
    //mayores probabilidades de que vaya a abandonar 	durante la carrera por desperfectos mec�nicos
	private ImageIcon image;
	
	public Car(int num, String mark, float maximumspeed, float aceleration, float power, float weight, float fuelconsum,
			Tires tires, int overtakingperformance, int corneringperformance, int reliability,ImageIcon image) {
		super();
		this.num = num;
		this.mark = mark;
		this.maximumspeed = maximumspeed;
		this.aceleration = aceleration;
		this.power = power;
		this.weight = weight;
		this.fuelconsum = fuelconsum;
		this.tires = tires;
		this.overtakingperformance = overtakingperformance;
		this.corneringperformance = corneringperformance;
		this.reliability = reliability;
		this.image = image;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public float getMaximumspeed() {
		return maximumspeed;
	}

	public void setMaximumspeed(float maximumspeed) {
		this.maximumspeed = maximumspeed;
	}

	public float getAceleration() {
		return aceleration;
	}

	public void setAceleration(float aceleration) {
		this.aceleration = aceleration;
	}

	public float getPower() {
		return power;
	}

	public void setPower(float power) {
		this.power = power;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getFuelconsum() {
		return fuelconsum;
	}

	public void setFuelconsum(float fuelconsum) {
		this.fuelconsum = fuelconsum;
	}

	public Tires getTires() {
		return tires;
	}

	public void setTires(Tires tires) {
		this.tires = tires;
	}

	/*public DrivingMode getDrivingmode() {
		return drivingmode;
	}

	public void setDrivingmode(DrivingMode drivingmode) {
		this.drivingmode = drivingmode;
	}*/

	public int getOvertakingperformance() {
		return overtakingperformance;
	}

	public void setOvertakingperformance(int overtakingperformance) {
		this.overtakingperformance = overtakingperformance;
	}

	public int getCorneringperformance() {
		return corneringperformance;
	}

	public void setCorneringperformance(int corneringperformance) {
		this.corneringperformance = corneringperformance;
	}

	public int getReliability() {
		return reliability;
	}

	public void setReliability(int reliability) {
		this.reliability = reliability;
	}

	public ImageIcon getImage() {
		return image;
	}

	public void setImage(ImageIcon image) {
		this.image = image;
	}
	
}
