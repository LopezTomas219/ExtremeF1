package model;

import java.util.Random;

public class Weathercondition {
	
	private Condition condition; 
	private int Temp;
	
	
	public Weathercondition(Condition condition) {
		super();
		this.condition = condition;
		randomTemp();
	}
	private void randomTemp() {
	    int maxTemp = condition.getTemperatureRange();
	    int minTemp;

	    if (condition.ordinal() < Condition.values().length - 1) {
	        minTemp = Condition.values()[condition.ordinal() + 1].getTemperatureRange();
	    } else {
	        minTemp = 0;
	    }

	    // Verifica si maxTemp es menor o igual a minTemp
	    if (maxTemp <= minTemp) {
	        // Maneja el caso en el que los límites no son válidos (por ejemplo, intercambia los valores o establece un valor predeterminado)
	        int temp = maxTemp;
	        maxTemp = minTemp + 1;  // Establece una diferencia mínima de 1
	        minTemp = temp;
	    }

	    Random random = new Random();
	    Temp = random.nextInt(maxTemp - minTemp) + minTemp;
	}
	public Condition getCondition() {
		return condition;
	}
	public void setCondition(Condition condition) {
		this.condition = condition;
	}
	public int getTemp() {
		return Temp;
	}
	public void setTemp(int temp) {
		Temp = temp;
	}
	
	
	

	

}