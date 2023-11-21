package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.swing.text.Position;


public class Race {
    
    private Date date;
    private Circuit circuit;
    private List<Laps> ListLapsCompetition = new ArrayList<>();
    private List<Player> ListPlayers = new ArrayList<>();
    private List<Weathercondition> listWeathercondition = new ArrayList<>();
    public static final boolean CONTINUE = true;

    public Race(Date date,Circuit circuit, List<Player> ListPlayers){

        this.date=date;
        this.circuit=circuit;
        this.ListPlayers = ListPlayers;

    }
    
    private boolean isCellAvailable(int row ,int col){
        return circuit.getCircuitMap()[row][col].isAvailable();
    }
    private synchronized void movePlayer(Player player , int row , int col){
       
        if (isCellAvailable(row, col)) {
           
            circuit.resetPos(player.getPosition().getRow(),player.getPosition().getCol());
            circuit.setPos(player, row, col);
            player.getPosition().setPosition(row, col);
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
    private boolean tryOvertake(Player overtakingPlayer ,Player targetPlayer){

        //pensar logica para ver si adelanta o no
        return false;
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
