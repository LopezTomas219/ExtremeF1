package model;

public class Wet extends Tires{
	
	

	 public Wet(Weathercondition weathercondition) {

	        adjustDurability(weathercondition); // Llamamos a un método para ajustar la durabilidad basándonos en las condiciones climáticas
	        setTireFactor(0.9f);
	 }

	@Override
	public void tiresUpdate() {
    	durabilityUpdate(3);
    }
	
	private void adjustDurability(Weathercondition weathercondition) {
		if (weathercondition.getCondition() == Condition.RAINY) {
			setDurability(80);
		} else {
			setDurability(50);
		}
	}
	

//	public int getDryDurability() {
//		return dryDurability;
//	}
//
//	public void setDryDurability(int dryDurability) {
//		this.dryDurability = dryDurability;
//	}

}