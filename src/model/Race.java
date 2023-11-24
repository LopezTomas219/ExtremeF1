package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.swing.text.Position;

import observer.WeatherChangeListener;
import observer.WeatherObserver;


public class Race {
    
    private Date date;
    private Circuit circuit;
    private List<Laps> LapsCompetition = new ArrayList<>();
    private List<Player> ListPlayers = new ArrayList<>();
    private List<Weathercondition> listWeathercondition = new ArrayList<>();
    private List<WeatherObserver> weatherObservers = new ArrayList<>();


    public static final boolean CONTINUE = true;

    public Race(Date date,Circuit circuit, List<Player> ListPlayers){

        this.date=date;
        this.circuit=circuit;
        this.ListPlayers = ListPlayers;
        createWeatherConditions();

    }
    
    
    public Date getDate() {
        return date;
    }


    public void setDate(Date date) {
        this.date = date;
    }


    public Circuit getCircuit() {
        return circuit;
    }


    public void setCircuit(Circuit circuit) {
        this.circuit = circuit;
    }
    public void createWeatherConditions() {
        

        // Obtener todas las condiciones climáticas disponibles
        Condition[] allConditions = Condition.values();

        // Crear un generador de números aleatorios
        Random random = new Random();

        // Seleccionar aleatoriamente 3 condiciones climáticas
        for (int i = 0; i < 3; i++) {
            int randomIndex = random.nextInt(allConditions.length);
            Condition randomCondition = allConditions[randomIndex];
            Weathercondition weathercondition = new Weathercondition(randomCondition);
            listWeathercondition.add(weathercondition);
        }
    }

    private synchronized boolean isCellAvailable(int row ,int col){
        return circuit.getCircuitMap()[row][col].isAvailable();
    }
    public synchronized void movePlayer(Player player , int overtakes ,int row , int col , double time , int numberLap){
       
        if (isCellAvailable(row, col)) {
           
            changePosition(player, overtakes, row, col);
            if (circuit.getCircuitMap()[row][col].isStartFinish()) {
                if (numberLap > 0) {
                    addLap(player.getPilot(), overtakes, true, time, numberLap);
                }
            }
        }else {
            if (circuit.getCircuitMap()[row][col].isOvertakeZone()) { //pregunta si a la posicion que se quiere mover es zona de adelantamientos
                Player targetPlayer = circuit.getCircuitMap()[row][col].getPlayer();
                if (tryOvertake(player, targetPlayer)) { 
                    
                    int row2 = player.getPosition().getRow();
                    int col2 = player.getPosition().getCol();
                    targetPlayer.getPosition().setPosition(row2, col2); //al jugador que lo adelantaron le asigno la position del que lo adelanto
                    player.getPosition().setPosition(row, col); // posicion que adelanto
                    circuit.setPos(player, row, col);
                    circuit.setPos(targetPlayer, row2, col2);

                }
            }//else player.reduceSpeed();
        }
    }
    
    private synchronized void changePosition(Player player,int overtakes, int row ,int col){
        circuit.resetPos(player.getPosition().getRow(),player.getPosition().getCol());
        circuit.setPos(player, row, col);
        player.getPosition().setPosition(row, col);
    }

    private boolean tryOvertake(Player overtakingPlayer ,Player targetPlayer){
    	return overtakingPlayer.calculateVelocity() * (overtakingPlayer.getCar().getOvertakingperformance() + overtakingPlayer.getPilot().getOvertaking() / 2) > targetPlayer.calculateVelocity() * targetPlayer.getPilot().getPositiondefense(); 
    }
    public void weatherconditionChange(){
        if (LapsCompetition.size() > 138 && LapsCompetition.size() < 276) {
            for (int i = 0; i < ListPlayers.size(); i++) {
                notifyWeatherChangeListeners(listWeathercondition.get(1));
            }
        }else if(LapsCompetition.size() > 276){
            notifyWeatherChangeListeners(listWeathercondition.get(1));
        }
    }

    private void qualifyingRound(){
        
        
    }
    
    
    public void goBox(Player player) {
    	
    }
    
    
    public void RunRace(){
        List <Thread> playerThreads = new ArrayList<>();
		for (int numberPlayer = 0; numberPlayer < ListPlayers.size(); numberPlayer++) {
            Thread playerThread = new Thread(ListPlayers.get(numberPlayer));
            playerThreads.add(playerThread);
			ListPlayers.get(numberPlayer).getListRaces().add(this);
            ListPlayers.get(numberPlayer).setRace(this);
            int StartDirection = circuit.getCircuitMap()[circuit.getRowFinish()][circuit.getColFinish()].getDirection() ;
            switch (StartDirection) {
                case 1:
                    ListPlayers.get(numberPlayer).getPosition().setPosition(circuit.getRowFinish(),circuit.getColFinish() - numberPlayer - 1 );
                    break;
                case 2:
                    ListPlayers.get(numberPlayer).getPosition().setPosition(circuit.getRowFinish() + numberPlayer,circuit.getColFinish()); 
                    break;
                case 3:
                    ListPlayers.get(numberPlayer).getPosition().setPosition(circuit.getRowFinish(),circuit.getColFinish() + numberPlayer );
                    break;
                case 4:
                    ListPlayers.get(numberPlayer).getPosition().setPosition(circuit.getRowFinish() - numberPlayer,circuit.getColFinish()); 
                    break;

                default:
                    break;
            }
            playerThread.start();
        }

    }
    
    public List<Laps>  getLaps(Player player){
        List<Laps> Laps = new ArrayList<>();
        for (int i = 0 ; i < LapsCompetition.size() ; i++){
            if (LapsCompetition.get(i).getInformationLapsPilot().getPilot().equals(player.getPilot())) {
                Laps.add(LapsCompetition.get(i));
            }
        }
        return Laps;
    }
    
    public void addLap(Pilot pilot , int overtake , boolean completed , double time, int numberLap){
        LapsCompetition.add(new Laps(new InformationLapsPilot(pilot, overtake, completed, time), numberLap));
    }
    public void addWeatherObserver(WeatherObserver observer) {
        weatherObservers.add(observer);
    }

    private void notifyWeatherChange(Weathercondition newCondition) {
        for (WeatherObserver observer : weatherObservers) {
            observer.updateWeather(newCondition);
        }
    }

}
