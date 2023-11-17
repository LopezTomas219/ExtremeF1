package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Race {
    
    private Date date;
    private Circuit circuit;
    private List<Laps> ListLapsCompetition = new ArrayList<>();
    private List<Laps> ListLapsQualifying = new ArrayList<>();
    private List<Player> ListPlayers = new ArrayList<>();
    private List<Weathercondition> listWeathercondition = new ArrayList<>();
    public static final boolean CONTINUE = true;

    public Race(Date date,Circuit circuit, List<Player> ListPlayers){

        this.date=date;
        this.circuit=circuit;
        this.ListPlayers = ListPlayers;

    }
    
    private void qualifyingRound(){
        
    }
    public void RunRace(){
        List <Thread> playerThreads = new ArrayList<>();
		for (int player = 0; player < ListPlayers.size(); player++) {
            Thread playerThread = new Thread(ListPlayers.get(player));
            playerThreads.add(playerThread);
			ListPlayers.get(player).setCircuit(circuit);
			ListPlayers.get(player).setDistance(0);
            playerThread.start();
        }

    }
    
}
