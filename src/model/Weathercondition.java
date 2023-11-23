package model;

public class Weathercondition {
	
	private Condition Condition; 
	private int Temp;
	
	
	public Weathercondition(Condition condition, int temp) {
		super();
		this.Condition = condition;
		this.Temp = temp;
	}
	public Weathercondition() {
		// TODO Auto-generated constructor stub
	}
	public Condition getCondition() {
		return Condition;
	}
	public void setCondition(Condition condition) {
		Condition = condition;
	}
	public int getTemp() {
		return Temp;
	}
	public void setTemp(int temp) {
		Temp = temp;
	}
	
	
	

	

}
