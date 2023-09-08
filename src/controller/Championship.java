package controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import model.*;
import view.Frame_Init;

public class Championship {
    
    private List<Pilot> ListPilots = new ArrayList<>();
    private List<Car> ListCars = new ArrayList<>();
    private List<Player> ListPlayers = new ArrayList<>();
    private List<Circuit> ListCircuits = new ArrayList<>();
    private List<Race> ListRace = new ArrayList<>();
    private List<Date> ListDate = new ArrayList<>();
    private Frame_Init frame_Init;

    public Championship(){

    Country country1 = new Country("Italiano"); 
	Country country2 = new Country("Finlandes"); 
	Country country3 = new Country("Finlandes"); 
	     
	     
	Pilot pilot1 =new Pilot("Antonio Giovinazzi", "El rapido",country1 ,2,1,4,3,2,60,48,68,58,90);
    Pilot pilot2 =new Pilot("Kimi R�ikk�nen", "El picante", country2, 2,4 , 3, 2, 0, 85, 65, 75, 89, 50);
	Pilot pilot3 =new Pilot("Valtteri Bottas","El pollo", country3, 0, 2, 0, 2, 1, 25, 90, 25, 89, 50);
    ListPilots.add(pilot1);
    ListPilots.add(pilot2);
    ListPilots.add(pilot3);
	     
	     //Se crean las ruedas de los autos con su atributo durabilidad !! 
	Tires soft = new Soft(70);
	Tires wet = new Wet();
	Tires hard= new Hard();
	Tires medioum = new Medium();
	     
	Car car1 = new Car(44,"Mercedes-AMG F1 W12", 370, 70, 950, 752, 50, soft, 89, 75, 90,null);
	Car car2 = new Car(33,"Red Bull Racing RB16B", 360, 78, 980, 754, 40, wet, 89, 75, 90,null);
	Car car3 = new Car(5,"Scuderia Ferrari SF21", 360, 80, 920, 755, 35, hard, 89, 75, 90,null);
    ListCars.add(car1);
    ListCars.add(car2);
    ListCars.add(car3);
    ChargeXML();
    CreateRaces();
    }
    
    public List<Pilot> getListPilots() {
        return ListPilots;
    }

    public void setListPilots(List<Pilot> listPilots) {
        ListPilots = listPilots;
    }

    public List<Car> getListCars() {
        return ListCars;
    }

    public void setListCars(List<Car> listCars) {
        ListCars = listCars;
    }

    public List<Player> getListPlayers() {
        return ListPlayers;
    }

    public void setListPlayers(List<Player> listPlayers) {
        ListPlayers = listPlayers;
    }

    public List<Circuit> getListCircuits() {
        return ListCircuits;
    }

    public void setListCircuits(List<Circuit> listCircuits) {
        ListCircuits = listCircuits;
    }

    private void CreateRaces(){
        //Se crean las carreras usando la lista de circuitos ,con las fechas del cronograma.
    }
    private void ChargeXML(){
        //Carga los pilotos,autos ,circuitos y fechas.
    }

    //---------------------------------------------------------------Vista

    public void StartGame(){
        
        frame_Init = new Frame_Init();
        frame_Init.setVisible(true);
        frame_Init.setController(this);
        
    }
}
