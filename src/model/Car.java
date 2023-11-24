package model;

import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

import observer.CarObservable;
import observer.EngineObserver;
import observer.FuelObserver;
import observer.TiresObserver;


public class Car implements CarObservable{
    private String model;
	private String mark;
	private float maximumspeed; //km/hs
	private float aceleration; // 0 a 100 km/h
	private float power; //HP
	private float weight; 
	private float fuelconsum; // consumo promedio por litros cada 100km
	private Tires tires;
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
	private int engineStatus;
	//--------------------------------Observadores--------------------------------------------------//
	private FuelObserver fuelObserver;
	private TiresObserver tiresObserver;
	private EngineObserver engineObserver;
	//--------------------------------Observadores--------------------------------------------------//


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
		engineStatus = 100;
	}
	public Car() {
		// TODO Auto-generated constructor stub
	}
	//--------------------------------Getter/Setters-------------------------------------------------//

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
	public float getFuel() {
		return fuel;
	}
	public void setFuel(float fuel) {
		this.fuel = fuel;
	}
	public int getEngineStatus() {
		return engineStatus;
	}
	public void setEngineStatus(int engineStatus) {
		this.engineStatus = engineStatus;
	}
	public Timer getTimer() {
		return timer;
	}
	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	public float getVelocity() {
		return velocity;
	}
	public void setVelocity(float velocity) {
		this.velocity = velocity;
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
	//----------------------------------------------------------------------------------//

    
     public float velocityProm() {
    	 return (power / 1000) * (maximumspeed / ((aceleration * 1000) / 3600)); 
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
         float finalSpeed = velocityProm() * tires.getTireFactor() * driveModeFactor * fuelFactor;
         finalSpeed = Math.max(0.0f, Math.min(1.0f, finalSpeed)); // Normaliza el valor dentro del rango [0, 1]
    	 return finalSpeed;
     }

	 public void carUpdate(){

		fuel -= 3;
		tires.tiresUpdate();
		engineStatus--;

		if (fuel < 20) {
			notifyLowFuel();
		}
		if (tires.getDurability()<10) {
			notifyWearTires();
		}
		if (engineStatus < 10) {
			notifyWearEngine();
		}
		
	 }
	
	//--------------------------------Observers-------------------------------------------------//
	@Override
	public void addEngineObserver(EngineObserver engineObserver) {
		this.engineObserver = engineObserver;
		
	}
	@Override
	public void addFuelObserver(FuelObserver fuelObserver) {
		this.fuelObserver = fuelObserver;
		
	}
	@Override
	public void addTiresObserver(TiresObserver tiresObserver) {
		this.tiresObserver = tiresObserver;
		
	}
	@Override
	public void notifyLowFuel() {
		// TODO Auto-generated method stub
		fuelObserver.lowFuel();
	}
	@Override
	public void notifyWearEngine() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void notifyWearTires() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void removeEngineObserver(EngineObserver observer) {
		this.engineObserver = null;
		
	}
	@Override
	public void removeFuelObserver(FuelObserver fuelObserver) {
		this.fuelObserver = null;
		
	}
	@Override
	public void removeTiresObserver(TiresObserver observer) {
		this.tiresObserver = null;
		
	} 
	
}