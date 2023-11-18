package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    public List<Player> updateRacePositions(List<Laps> lapsList) {
        // Ordenar la lista de vueltas por número de vuelta
        Collections.sort(lapsList, Comparator.comparingInt(Laps::getLapNumber));

        // Actualizar posiciones
        for (int i = 1; i < lapsList.size(); i++) {
            InformationLapsPilot currentInfo = lapsList.get(i).getInformationLapsPilot();
            InformationLapsPilot prevInfo = lapsList.get(i - 1).getInformationLapsPilot();

            if (currentInfo.isCompleted() && prevInfo.isCompleted()) {
                int overtakes = currentInfo.getOvertakes();
                if (overtakes > 0) {
                    // El jugador adelantó a otros pilotos
                    int currentPosition = ListPlayers.indexOf(currentInfo.getPilot());
                    int newPosition = currentPosition - overtakes;
                    if (newPosition >= 0) {
                        Collections.swap(ListPlayers, currentPosition, newPosition);
                    }
                } else if (overtakes < 0) {
                    // Otros jugadores adelantaron al jugador
                    int currentPosition = ListPlayers.indexOf(currentInfo.getPilot());
                    int newPosition = currentPosition - overtakes;
                    if (newPosition < ListPlayers.size()) {
                        Collections.swap(ListPlayers, currentPosition, newPosition);
                    }
                }
                // Si overtakes == 0, no hubo cambios de posición
            }
        }

        // Devolver la lista actualizada de posiciones
        return ListPlayers;
    }
    
}
