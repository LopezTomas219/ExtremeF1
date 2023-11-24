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
        int maxTemp = condition.getTemperatureRange(); // Obtén el rango máximo de temperatura
        int minTemp;

        // Si no es la última condición, obtén el rango mínimo de la siguiente condición
        if (condition.ordinal() < Condition.values().length - 1) {
            minTemp = Condition.values()[condition.ordinal() + 1].getTemperatureRange();
        } else {
            // Para la última condición, establece el rango mínimo a un valor arbitrario (por ejemplo, 0)
            minTemp = 0;
        }

        // Genera una temperatura aleatoria dentro del rango
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
