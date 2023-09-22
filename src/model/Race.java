package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Race {
    
    private Date date;
    private Circuit circuit;
    private List<Laps> ListLaps = new ArrayList<>();
    private List<Player> ListPlayers = new ArrayList<>();
    private List<Car> ListCars;
    public static final boolean CONTINUE = true;

    public Race(Date date,Circuit circuit, List<Car> ListCars){

        this.date=date;
        this.circuit=circuit;
        this.ListCars = ListCars;

    }
    
    private void qualifyingRound(){
        //Ronda de clasificacion,luego ordenar por tiempo
    }
    private void RunRace(){

        List <Thread> carThreads = new ArrayList<>();
    	
    	for (Car car : ListCars) {
    		Thread carThread = new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					while (CONTINUE) {
						car.move();
						try {
							Thread.sleep((int) (Math.random() * 1000));
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
    			
    		});
    		carThreads.add(carThread);
    		carThread.start();
    }
    	
    for (Thread thread : carThreads) {
    	try {
    		thread.join();
    	} catch (InterruptedException e) {
    		e.printStackTrace();
    	}
    }	
}

}
