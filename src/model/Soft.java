package model;

public class Soft extends Tires {
    public Soft() {
        setDurability(60); // Ejemplo de durabilidad de neumáticos Soft
        setTireFactor(1f);
    }
    
    

    @Override
	public void tiresUpdate() {
    	durabilityUpdate(4);
    }
        // Implementa la lógica para evaluar el estado de los neumáticos Soft
}