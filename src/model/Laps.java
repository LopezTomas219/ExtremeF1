package model;

public class Laps {
    
    private InformationLapsPilot informationLapsPilot;
    private int lapNumber; // número de la vuelta
    
    public Laps(InformationLapsPilot informationLapsPilot, int lapNumber) {
        this.informationLapsPilot = informationLapsPilot;
        this.lapNumber = lapNumber;
    }

    public InformationLapsPilot getInformationLapsPilot() {
		return informationLapsPilot;
	}

	public void setInformationLapsPilot(InformationLapsPilot informationLapsPilot) {
		this.informationLapsPilot = informationLapsPilot;
	}

	public int getLapNumber() {
		return lapNumber;
	}

	public void setLapNumber(int lapNumber) {
		this.lapNumber = lapNumber;
	}

}
