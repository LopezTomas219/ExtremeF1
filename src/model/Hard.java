package model;

public class Hard extends Tires {
    public Hard() {
        setDurability(90); // Ejemplo de durabilidad de neumáticos Hard
        setTireFactor(0.8f);
    }

    @Override
	public void tiresUpdate() {
    	durabilityUpdate(6);
    }
}