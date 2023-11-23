package model;

public  class Medium  extends Tires {

	public Medium() {
		setDurability(70); // Ejemplo de durabilidad de neumáticos Soft
        setTireFactor(0.9f);
	}

	@Override
	public void tiresUpdate() {
    	durabilityUpdate(5);
    }

	
	
	//Son una combinaci n entre los neum ticos Soft y Hard


}