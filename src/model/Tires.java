package model;


public abstract class Tires {
    private int durability;
	private int grip;

	public Tires() {
		
	}

	public int getDurability() {
		return durability;
	}

	public void setDurability(int durability) {
		this.durability = durability;
	}

	public int getGrip() {
		return grip;
	}

	public void setGrip(int grip) {
		this.grip = grip;
	}

	public abstract void statusTires(
		
	);
  
}
