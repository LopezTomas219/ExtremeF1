package model;

import java.awt.Image;

import javax.swing.Icon;

public class Pilot {
    private String name;
	private String nameabbreviated;
	private Country country;
	private int quantitycarrer;
	private int quantitycarrerwin;
	private int quantitychampions;
	private int quantitychampionswin;
	private int quantitypolepositions;
	private int budget; // PRESUPUESTO ALTO , MEDIO , BAJO 
	private Image infographic;
	
	
	//Caractersiticas del piloto Cada piloto consta de valores entre 1 al 100 a mayor valor mejor deempe�o
	private int overtaking; //Determina cu�ntas oportunidades de sobrepaso ve un conductor
	private int positiondefense; //Determina la eficacia con la que un conductor se defiende de los intentos de adelantamientos de otros pilotos 
	private int ranking; // Mejora el rendimiento del conductor durante las sesiones de clasificaci�n, lo que le permite obtener una mejor posici�n de inicio en la grilla de partida
	private int tirecare; //s 	neum�ticos 	Una nota alta de gesti�n de neum�ticos indica que la forma de manejo del 	piloto reducir� el desgaste de los neum�ticos en las carreras
	private int  start; //Una valoraci�n de inicio de carrera m�s alta aumentar� las estad�sticas de 	sobrepaso y de defensa del corredor, durante la primera vuelta de la carrera

	private boolean selected;

	public Pilot(String name, String nameabbreviated,Country country, int quantitycarrer, int quantitycarrerwin,int quantitychampions, 
    int quantitychampionswin, int quantitypolepositions, int overtaking,int positiondefense, int ranking, int tirecare, int start, Image infographic) {
		this.name = name;
		this.nameabbreviated = nameabbreviated;
		this.country = country;
		this.quantitycarrer = quantitycarrer;
		this.quantitycarrerwin = quantitycarrerwin;
		this.quantitychampions = quantitychampions;
		this.quantitychampionswin = quantitychampionswin;
		this.quantitypolepositions = quantitypolepositions;
		this.overtaking = overtaking;
		this.positiondefense = positiondefense;
		this.ranking = ranking;
		this.tirecare = tirecare;
		this.start = start;
		this.infographic = infographic;
	}
	public Pilot() {
		// TODO Auto-generated constructor stub
	}

	public String getNamepilot() {
		return name;
	}

	public void setNamepilot(String namepilot) {
		this.name = namepilot;
	}

	public String getNameabbreviated() {
		return nameabbreviated;
	}

	public void setNameabbreviated(String nameabbreviated) {
		this.nameabbreviated = nameabbreviated;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public int getQuantitycarrer() {
		return quantitycarrer;
	}

	public void setQuantitycarrer(int quantitycarrer) {
		this.quantitycarrer = quantitycarrer;
	}

	public int getQuantitycarrerwin() {
		return quantitycarrerwin;
	}

	public void setQuantitycarrerwin(int quantitycarrerwin) {
		this.quantitycarrerwin = quantitycarrerwin;
	}

	public int getQuantitychampions() {
		return quantitychampions;
	}

	public void setQuantitychampions(int quantitychampions) {
		this.quantitychampions = quantitychampions;
	}

	public int getQuantitychampionswin() {
		return quantitychampionswin;
	}

	public void setQuantitychampionswin(int quantitychampionswin) {
		this.quantitychampionswin = quantitychampionswin;
	}

	public int getQuantitypolepositions() {
		return quantitypolepositions;
	}

	public void setQuantitypolepositions(int quantitypolepositions) {
		this.quantitypolepositions = quantitypolepositions;
	}

	

	public int getOvertaking() {
		return overtaking;
	}

	public void setOvertaking(int overtaking) {
		this.overtaking = overtaking;
	}

	public int getPositiondefense() {
		return positiondefense;
	}

	public void setPositiondefense(int positiondefense) {
		this.positiondefense = positiondefense;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public int getTirecare() {
		return tirecare;
	}

	public void setTirecare(int tirecare) {
		this.tirecare = tirecare;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}
	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

    @Override
    public String toString() {
        return "pilot [name=" + name + ", nameabbreviated=" + nameabbreviated + ", country=" + country + "]";
    }
    public Icon getImage() {
        return null;
    }
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public Image getInfographic() {
		return infographic;
	}
	public void setInfographic(Image infographic) {
		this.infographic = infographic;
	}
	
    
}
