package model;

import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;


public class Car {
    private String model;
	private String mark;
	private float maximumspeed; //km/hs
	private float aceleration; // 0 a 100 km/h
	private float power; //HP
	private float weight; 
	private float fuelconsum; // consumo promedio por litros cada 100km
	private Tires tires;
	//private driveMode drivemode;//El drivemode no se construira ya que ira en una funcion la cual seria 
    //asignarModo 
	//caracteristicas del 0 al 100
	
	private int overtakingperformance; //Determina c mo se desempe a el auto en aceleraciones r pidas 	y/o rectas prolongadas para sobrepasar a otros autos
	private int corneringperformance; //Determina c mo es el comportamiento del auto en las curvas

	private int reliability; //Determina qu  tan confiable es el auto: A menor valor de este atributo, 
    //mayores probabilidades de que vaya a abandonar 	durante la carrera por desperfectos mec nicos
	private Image image;
	private boolean selected;
	private DriveMode driveMode;
	private float fuel;
	private Timer timer;
    private float velocity;
	

	public Car(String model, String mark, float maximumspeed, float aceleration, float power, float weight, float fuelconsum,
			Tires tires, int overtakingperformance, int corneringperformance, int reliability,Image image, DriveMode driveMode) {
		super();
		this.model = model;
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
		this.driveMode = driveMode;
	}
	public Car() {
		// TODO Auto-generated constructor stub
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
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

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
    
     public float velocityProm() {
    	 return (power / 100) * (maximumspeed / ((aceleration * 1000) / 3600)); 
     }
     public float calculateVelocity() {
    	// Factor de velocidad inicial (basado en la potencia del auto)
         // tiene en cuenta la aceleracion pero nunca llega a ser 1 el resultado final
         
      // Ajuste de velocidad según el modo de manejo
         float driveModeFactor = (float) driveMode.getFactor();

         // Ajuste de velocidad según el nivel de combustible (más combustible puede ralentizar el auto)
         float fuelFactor;
         
         if (fuel == 100) {
        	 fuelFactor = 0.6f;
         } else if (fuel >= 75) {
        	 fuelFactor = 0.7f;
         } else if (fuel >= 50) {
        	 fuelFactor = 0.8f;
         } else if (fuel >= 25) {
        	 fuelFactor = 0.9f;
         } else if (fuel > 0) {
        	 fuelFactor = 1.0f;
         } else {
        	 fuelFactor = 0f;
         }
         float finalSpeed = velocityProm() * tires.getTireFactor() * driveModeFactor * fuelFactor * maximumspeed;
         finalSpeed = Math.max(0.0f, Math.min(1.0f, finalSpeed/maximumspeed)); // Normaliza el valor dentro del rango [0, 1]
    	 return finalSpeed;
     }

	 public void carUpdate(){
		fuel =- 3;
		tires.tiresUpdate();
		
	 }
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public DriveMode getDriveMode() {
		return driveMode;
	}
	public void setDriveMode(DriveMode driveMode) {
		this.driveMode = driveMode;
	}
	
}