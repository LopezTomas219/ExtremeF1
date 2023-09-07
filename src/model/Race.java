package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Race {
    
    private Date date;
    private Circuit circuit;
    private List<Laps> ListLaps = new ArrayList<>();
    private List<Player> ListPlayers = new ArrayList<>();

    public Race(Date date,Circuit circuit){

        this.date=date;
        this.circuit=circuit;

    }
    
    private void qualifyingRound(){
        //Ronda de clasificacion,luego ordenar por tiempo
    }
    private void RunRace(){

        //Ver hilos
    }

}
