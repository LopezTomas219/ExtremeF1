package controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import model.*;

public class Championship {
    
    private List<Pilot> ListPilots = new ArrayList<>();
    private List<Car> ListCars = new ArrayList<>();
    private List<Player> ListPlayers = new ArrayList<>();
    private List<Circuit> ListCircuits = new ArrayList<>();
    private List<Race> ListRace = new ArrayList<>();
    private List<Date> ListDate = new ArrayList<>();
    public Championship(){

    ChargeXML();
    CreateRaces();
    }
    
    private void CreateRaces(){
        //Se crean las carreras usando la lista de circuitos ,con las fechas del cronograma.
    }
    private void ChargeXML(){
        //Carga los pilotos,autos ,circuitos y fechas.
    }
}
