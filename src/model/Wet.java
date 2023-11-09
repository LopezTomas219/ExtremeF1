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
	        setTireFactor(1.1f);
	 }

	@Override
	public void statusTires() {
		// TODO Auto-generated method stub
		
	}
	
	private void adjustDurability() {
		if (weathercondition.getCondition() == Condition.RAINY) {
			setDurability(80);
		} else {
			setDurability(70);
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