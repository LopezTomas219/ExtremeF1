package model;


public abstract class Tires {
    private int durability;
	private int grip;
	private float tireFactor;

	public Tires(int durability, int grip) {
		this.durability = durability;
		this.grip = grip;
		
	}
	
	public Tires() {
		
	}

	public int getDurability() {
		return durability;
	}

	public void setDurability(int durability) {
		this.durability = durability;
	}
	public void durabilityUpdate(int wear){
		durability -= wear;
	}
	public int getGrip() {
		return grip;
	}

	public void setGrip(int grip) {
		this.grip = grip;
	}

	public abstract void tiresUpdate();
	
	public float getTireFactor() {
		return tireFactor;
	}

	public void setTireFactor(float tireFactor) {
		this.tireFactor = tireFactor;
	}

  
}
