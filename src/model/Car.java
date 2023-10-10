package model;

import java.awt.Image;
import java.awt.Point;
import java.net.URL;

import javax.swing.ImageIcon;

public class Car {
    private String model;
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
	private boolean moverDerecha;
    private boolean moverAbajo;
    private boolean moverIzquierda;
    private boolean moverArriba;
	
	private boolean selected;
	

	public Car(String model, String mark, float maximumspeed, float aceleration, float power, float weight, float fuelconsum,
			Tires tires, int overtakingperformance, int corneringperformance, int reliability,ImageIcon image) {
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

	public ImageIcon getImage() {
		return image;
	}

	public void setImage(ImageIcon image) {
		this.image = image;
	}
	public void setMoverDerecha(boolean moverDerecha) {
        this.moverDerecha = moverDerecha;
    }

    public boolean isMoverDerecha() {
        return moverDerecha;
    }

    public void setMoverAbajo(boolean moverAbajo) {
        this.moverAbajo = moverAbajo;
    }

    public boolean isMoverAbajo() {
        return moverAbajo;
    }

    public void setMoverIzquierda(boolean moverIzquierda) {
        this.moverIzquierda = moverIzquierda;
    }

    public boolean isMoverIzquierda() {
        return moverIzquierda;
    }

    public void setMoverArriba(boolean moverArriba) {
        this.moverArriba = moverArriba;
    }

    public boolean isMoverArriba() {
        return moverArriba;
    }
    
    // public void move () {
    // 	Point carLocation = getLocation(); // esto se va a poder usar cuando tengamos los JLabel 
    // 	if (isMoverDerecha()) {
    // 		if (carLocation.x <= WindowsRace.getFinish().getLocation().x-55) { // windowsRace es el frame de la carrera
    // 			carLocation.x += 10;
    // 		} else {
    // 			setMoverDerecha(false);
    // 			setMoverAbajo(true);
    // 		}
    // 	} else if (isMoverAbajo()) {
    // 		if (carLocation.y <= WindowsRace.getHeight()-55) {
    // 			carLocation.y += 10;
    // 		} else {
    // 			setMoverAbajo(false);
    // 			setMoverIzquierda(true);
    // 		}
    // 	} else if (isMoverIzquierda()) {
    // 		if (carLocation.x >= 0) {
    // 			carLocation.x -= 10;
    // 		} else {
    // 			setMoverIzquierda(false);
    // 			setMoverArriba(true);
    // 		}
    // 	} else if (isMoverArriba()) {
    // 		if (carLocation.y >= 0) {
    // 			carLocation.y -= 10;
    // 		} else {
    // 			setMoverArriba(false);
    // 			setMoverDerecha(true);
    // 		}
    // 	}
    // }


	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
}
