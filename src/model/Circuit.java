package model;
import java.awt.Image;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.io.FileInputStream;
import java.time.LocalTime;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import javax.json.JsonValue;



public class Circuit implements Serializable {
	
	private String namecircuit;
	private Country country;
	private int tracklength ; //Longuitud de pista en metros
	private  int numberLaps ; //cantidad de vueltas del circuito
	private  Image infographic;
	
	private LocalTime timerecord; // tiempo record de la vuelta mas rapida
	private int numberovertaking; // cantidad de lugares de sobrepasos
	private int numbercurves; // cantidad de curvas
	private CircuitCell[][] circuitMap;
	private int rowFinish;
	private int colFinish;

	public Circuit(String namecircuit, Country country, int tracklength, int numberflaps,
			Image infographic, LocalTime timerecord, int numberovertaking, int numbercurves , String jsonFilePath) {
		super();
		this.namecircuit = namecircuit;
		this.country = country;
		this.tracklength = tracklength;
		this.numberLaps = numberflaps;
		this.infographic = infographic;
		this.timerecord = timerecord;
		this.numberovertaking = numberovertaking;
		this.numbercurves = numbercurves;
		loadCircuit(jsonFilePath);
	}
	public String getNamecircuit() {
		return namecircuit;
	}
	public void setNamecircuit(String namecircuit) {
		this.namecircuit = namecircuit;
	}
	public Country getCountrycircuit() {
		return country;
	}
	public void setCountrycircuit(Country country) {
		this.country = country;
	}
	
	public int getTracklength() {
		return tracklength;
	}
	public void setTracklength(int tracklength) {
		this.tracklength = tracklength;
	}
	public int getNumberflaps() {
		return numberLaps;
	}
	public void setNumberflaps(int numberflaps) {
		this.numberLaps = numberflaps;
	}
	public Image getInfographic() {
		return infographic;
	}
	public void setInfographic(Image infographic) {
		this.infographic = infographic;
	}
	
	public int getNumberovertaking() {
		return numberovertaking;
	}
	public void setNumberovertaking(int numberovertaking) {
		this.numberovertaking = numberovertaking;
	}
	public int getNumbercurves() {
		return numbercurves;
	}
	public void setNumbercurves(int numbercurves) {
		this.numbercurves = numbercurves;
	}
	public LocalTime getTimerecord() {
		return timerecord;
	}
	public void setTimerecord(LocalTime timerecord) {
		this.timerecord = timerecord;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public int getNumberLaps() {
		return numberLaps;
	}
	public void setNumberLaps(int numberLaps) {
		this.numberLaps = numberLaps;
	}
	public CircuitCell[][] getCircuitMap() {
		return circuitMap;
	}
	public void setCircuitMap(CircuitCell[][] circuitMap) {
		this.circuitMap = circuitMap;
	}
	public int getRowFinish() {
		return rowFinish;
	}
	public void setRowFinish(int rowFinish) {
		this.rowFinish = rowFinish;
	}
	public int getColFinish() {
		return colFinish;
	}
	public void setColFinish(int colFinish) {
		this.colFinish = colFinish;
	}
	public void loadCircuit(String jsonFilePath) {
	    try (JsonReader reader = Json.createReader(new FileInputStream(jsonFilePath))) {
	        JsonArray jsonArray = reader.readArray();

	        int numRows = jsonArray.size();
	        int numCols = jsonArray.getJsonArray(0).size();
	        this.circuitMap = new CircuitCell[numRows][numCols];

	        for (int i = 0; i < numRows; i++) {
	            JsonArray rowArray = jsonArray.getJsonArray(i);
	            for (int j = 0; j < numCols; j++) {
	                JsonObject cellObject = rowArray.getJsonObject(j);
	                int type = cellObject.getInt("type");
	                int direction = cellObject.getInt("direction");
	                if (type == 4) {
	                    rowFinish = i;
	                    colFinish = j;
	                }
	                this.circuitMap[i][j] = new CircuitCell(type, direction);
	            }
	        }

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}




	public void resetPos(int row , int col){
		circuitMap[row][col].setAvailable(true);
		circuitMap[row][col].setPlayer(null);
	}
	public void setPos(Player player ,int row , int col){
		circuitMap[row][col].setAvailable(false);
		circuitMap[row][col].setPlayer(player);
	}
	

	
	
	
}
