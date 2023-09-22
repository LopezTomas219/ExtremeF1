package model;
import java.time.LocalTime;
import java.awt.Image;



public class Circuit {
	
	private String namecircuit;
	private Country country;
	private int tracklength ; //Longuitud de pista en metros
	private  int numberLaps ; //cantidad de vueltas del circuito
	private  Image infographic;
	
	private LocalTime timerecord; // tiempo record de la vuelta mas rapida
	private int numberovertaking; // cantidad de lugares de sobrepasos
	private int numbercurves; // cantidad de curvas
	public Circuit(String namecircuit, Country country, int tracklength, int numberflaps,
			Image infographic, LocalTime timerecord, int numberovertaking, int numbercurves) {
		super();
		this.namecircuit = namecircuit;
		this.country = country;
		this.tracklength = tracklength;
		this.numberLaps = numberflaps;
		this.infographic = infographic;
		this.timerecord = timerecord;
		this.numberovertaking = numberovertaking;
		this.numbercurves = numbercurves;
	}
	public Circuit() {
		// TODO Auto-generated constructor stub
	}
	public String getNamecircuit() {
		return namecircuit;
	}
	public void setNamecircuit(String namecircuit) {
		this.namecircuit = namecircuit;
	}
	public Country getCountrycircuit() {
		return country;
	}
	public void setCountrycircuit(Country country) {
		this.country = country;
	}
	
	public int getTracklength() {
		return tracklength;
	}
	public void setTracklength(int tracklength) {
		this.tracklength = tracklength;
	}
	public int getNumberflaps() {
		return numberLaps;
	}
	public void setNumberflaps(int numberflaps) {
		this.numberLaps = numberflaps;
	}
	public Image getInfographic() {
		return infographic;
	}
	public void setInfographic(Image infographic) {
		this.infographic = infographic;
	}
	
	public int getNumberovertaking() {
		return numberovertaking;
	}
	public void setNumberovertaking(int numberovertaking) {
		this.numberovertaking = numberovertaking;
	}
	public int getNumbercurves() {
		return numbercurves;
	}
	public void setNumbercurves(int numbercurves) {
		this.numbercurves = numbercurves;
	}
	public LocalTime getTimerecord() {
		return timerecord;
	}
	public void setTimerecord(LocalTime timerecord) {
		this.timerecord = timerecord;
	}
	 

	
	
	
}
