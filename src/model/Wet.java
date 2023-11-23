package model;

public class Wet extends Tires{
	
	//private int dryDurability;
	 private Weathercondition weathercondition;
/*
//	public Wet(int durability, int grip, int dryDurability) {
//        super(durability, grip);
//        //this.dryDurability = dryDurability;
//		
//	}
*/
	 public Wet(Weathercondition weathercondition) {
	        this.weathercondition = weathercondition;
	        adjustDurability(); // Llamamos a un método para ajustar la durabilidad basándonos en las condiciones climáticas
	        setTireFactor(0.9f);
	 }

	@Override
	public void tiresUpdate() {
    	durabilityUpdate(3);
    }
	
	private void adjustDurability() {
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