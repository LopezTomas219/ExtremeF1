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
        //Ronda de clasificacion,luego ordenar por tiempo
    }
    public void RunRace(){

        List <Thread> playerThreads = new ArrayList<>();
    	
    	for (Player player : ListPlayers) {
    		Thread playerThread = new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					while (CONTINUE) {
						//player.getCar().move();
						//player.getCar().updateFuelAndTires();
						player.getVelocity();
						try {
							Thread.sleep((int) (Math.random() * 1000));
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
    			
    		});
    		playerThreads.add(playerThread);
    		playerThread.start();
    }
    	
    for (Thread thread : playerThreads) {
    	try {
    		thread.join();
    	} catch (InterruptedException e) {
    		e.printStackTrace();
    	}
    }	
}

}
