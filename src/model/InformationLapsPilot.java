package model;

public class InformationLapsPilot {
    
    private Pilot pilot;
    private int overtakes; // cantidad de adelantamientos en la última vuelta
    private boolean completed; // indica si la vuelta se completó con éxito
	private double timeLap;
    
    public InformationLapsPilot(Pilot pilot, int overtakes, boolean completed , double timeLap) {
        this.pilot = pilot;
        this.overtakes = overtakes;
        this.completed = completed;
		this.timeLap = timeLap;
    }
    
    public Pilot getPilot() {
		return pilot;
	}

	public void setPilot(Pilot pilot) {
		this.pilot = pilot;
	}

	public int getOvertakes() {
		return overtakes;
	}

	public void setOvertakes(int overtakes) {
		this.overtakes = overtakes;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public double getTimeLap() {
		return timeLap;
	}

	public void setTimeLap(double timeLap) {
		this.timeLap = timeLap;
	}

	@Override
	public String toString() {
		return "InformationLapsPilot [pilot=" + pilot + ", overtakes=" + overtakes + ", completed=" + completed
				+ ", timeLap=" + timeLap + "]";
	}
	

	
}