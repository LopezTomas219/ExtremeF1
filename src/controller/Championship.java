package controller;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import java.awt.Color;
import java.awt.Container;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import model.*;
import observer.WeatherObserver;
import view.BoxPlayer;
import view.ColorPanel;
import view.Frame_Create;
import view.Frame_Init;

public class Championship implements WeatherObserver , Serializable{
    
    
    private List<Pilot> ListPilots = new ArrayList<>();
    private List<Car> ListCars = new ArrayList<>();
    private List<Color> ListColors = new ArrayList<>();
    private List<Player> ListPlayers = new ArrayList<>();
    private List<Circuit> ListCircuits = new ArrayList<>();
    private List<Race> ListRace = new ArrayList<>();
    private List <Country> ListCountries = new ArrayList<>();
    private List<File> files = new ArrayList<>();
    private String nameGame;
    private int numberPlayers;
    private int racesPlayed = 0;
   
    private Frame_Init frame_init;
    private Frame_Create frame_create;
    

    public Championship(){


    chargeXML();
    createRaces();
    }
    
    public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}
	
	/*--------------------------------Pilotos-----------------------------------------------------------------------------*/
    public List<Pilot> getListPilots() {
        return ListPilots;
    }

    public void setListPilots(List<Pilot> listPilots) {
        ListPilots = listPilots;
    }
    /*-------------------------------------Autos------------------------------------------------------------------------*/
    public List<Car> getListCars() {
        return ListCars;
    }

    public void setListCars(List<Car> listCars) {
        ListCars = listCars;
    }   
    /*----------------------------------------Jugadores---------------------------------------------------------------------*/
    public List<Player> getListPlayers() {
        return ListPlayers;
    }

    public void setListPlayers(List<Player> listPlayers) {
        ListPlayers = listPlayers;
    }

    public void createPlayer(String name, Color color, Pilot pilot, Car car){
        PlayerReal player = new PlayerReal(name, color, pilot, car);
        ListPlayers.add(player);
        numberPlayers++;
        
    } 
    public int getNumberPlayers() {
        return numberPlayers;
    }
    public void setNumberPlayers(int numberPlayers) {
        this.numberPlayers = numberPlayers;
    }
    public void setRacesPlayed(int races) {
    	this.racesPlayed = races;
    }
    public int getRacesPlayed() {
    	return racesPlayed;
    }
    /*------------------------------------------------Circuitos-------------------------------------------------------------*/
    public List<Circuit> getListCircuits() {
        return ListCircuits;
    }

    public void setListCircuits(List<Circuit> listCircuits) {
        ListCircuits = listCircuits;
    }
    /*-------------------------------------------------------------------------------------------------------------*/
     public String getNameGame() {
        return nameGame;
    }
    public void setNameGame(String nameGame) {
        this.nameGame = nameGame;
    }
    
    private void createRaces() {
        if (ListCircuits == null || ListCircuits.isEmpty()) {
            throw new IllegalArgumentException("La lista de circuitos no puede ser nula o vacía.");
        }

        for (Circuit circuit : ListCircuits) {
            Date currentDate = new Date();
            Race race = new Race(currentDate, circuit,ListPlayers);
            race.addWeatherObserver(this);
            ListRace.add(race);
        }
    }
    public void startRace() {
        sortPlayersByPoints(); 
        Race currentRace = ListRace.get(racesPlayed); 
        currentRace.RunRace();
    
        List<Player> racePositions = currentRace.getPositions();
        for (int i = 0; i < racePositions.size(); i++) {
            
            racePositions.get(i).addPoints(racePositions.size() - i);
        }
        racesPlayed++;
    }
    public void sortPlayersByPoints() {
      
        Comparator<Player> comparator = Comparator.comparingInt(Player::getPoints).reversed();
        Collections.sort(ListPlayers, comparator);
    }
    @Override
    public void updateWeather(Weathercondition newCondition) {
        
        if (newCondition.getCondition() == Condition.RAINY) {
            for (Player player : ListPlayers) {
                if (player instanceof PlayerSimulator) {
                    // Ejecutar el método cambiarRuedas en jugadores bots
                    player.getCar().setTires(new Wet(newCondition));
                }
            }
        }
        //avisar modificacion en la vista 
        
    }
    private void chargeXML(){
        //Carga los pilotos,autos ,circuitos y fechas.
    	// LEE AUTOS
        try {
            File inputFile = new File("src/resources/files/autos.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList autoNodes = doc.getElementsByTagName("auto");

            for (int i = 0; i < autoNodes.getLength(); i++) {
                Node autoNode = autoNodes.item(i);
                if (autoNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element carElement = (Element) autoNode;
                    Car car = new Car();
                    car.setOvertakingperformance(Integer.parseInt(carElement.getElementsByTagName("performanceSobrepaso").item(0).getTextContent()));
                    car.setCorneringperformance(Integer.parseInt(carElement.getElementsByTagName("performanceCurvas").item(0).getTextContent()));
                    car.setWeight(Float.parseFloat(carElement.getElementsByTagName("peso").item(0).getTextContent()));
                    car.setReliability(Integer.parseInt(carElement.getElementsByTagName("fiabilidad").item(0).getTextContent()));
                    car.setMaximumspeed(Float.parseFloat(carElement.getElementsByTagName("velocidadMax").item(0).getTextContent()));
                    car.setAceleration(Float.parseFloat(carElement.getElementsByTagName("aceleracion").item(0).getTextContent()));
                    car.setPower(Float.parseFloat(carElement.getElementsByTagName("potencia").item(0).getTextContent()));
                    car.setFuelconsum(Float.parseFloat(carElement.getElementsByTagName("consumo").item(0).getTextContent()));
                    car.setMark(carElement.getElementsByTagName("marca").item(0).getTextContent());
                    car.setModel(carElement.getElementsByTagName("modelo").item(0).getTextContent());
                    String imagePath = carElement.getElementsByTagName("imagen").item(0).getTextContent();
        			BufferedImage image = ImageIO.read(new File(imagePath));
        			car.setImage(image);
                    ListCars.add(car);
                }
            }


        } catch (Exception e) {
        	e.printStackTrace();
        }
        // LEE CIRCUITOS
        try {
        	File inputFile = new File ("src/resources/files/circuitos.xml");
        	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        	Document doc = dBuilder.parse(inputFile);
        	doc.getDocumentElement().normalize();
        	
        	NodeList circuitNodes = doc.getElementsByTagName("circuito");
        	for (int i = 0; i < circuitNodes.getLength(); i++) {
        		Node circuitNode = circuitNodes.item(i);
        		if (circuitNode.getNodeType() == Node.ELEMENT_NODE) {
        			Element circuitElement = (Element) circuitNode;
        			ListCircuits.add(new Circuit(
                    circuitElement.getElementsByTagName("nombre").item(0).getTextContent(),
                    new Country(circuitElement.getElementsByTagName("pais").item(0).getTextContent()),
                    Integer.parseInt(circuitElement.getElementsByTagName("longitud").item(0).getTextContent()),
                    Integer.parseInt(circuitElement.getElementsByTagName("cantVueltas").item(0).getTextContent()),
                    ImageIO.read(new File(circuitElement.getElementsByTagName("infografia").item(0).getTextContent())),
                    LocalTime.ofSecondOfDay(Integer.parseInt(circuitElement.getElementsByTagName("recordVueltaRapida").item(0).getTextContent())),
                    Integer.parseInt(circuitElement.getElementsByTagName("cantZonasSobrepaso").item(0).getTextContent()),
                    Integer.parseInt(circuitElement.getElementsByTagName("cantCurvas").item(0).getTextContent()),
                    circuitElement.getElementsByTagName("map").item(0).getTextContent()));
        		}
        	}
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        //LEE PAISES
        try {
        	File inputFile = new File ("src/resources/files/paises.xml");
        	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        	Document doc = dBuilder.parse(inputFile);
        	doc.getDocumentElement().normalize();
        	
        	NodeList countryNodes = doc.getElementsByTagName("pais");
        	for (int i = 0; i < countryNodes.getLength(); i++) {
        		Node countryNode = countryNodes.item(i);
        		if (countryNode.getNodeType() == Node.ELEMENT_NODE) {
        			Element countryElement = (Element) countryNode;
        			Country country = new Country();
        			country.setName(countryElement.getElementsByTagName("nombre").item(0).getTextContent());
        			country.setNameAbbreviated(countryElement.getElementsByTagName("nombreAbreviado").item(0).getTextContent());
        			ListCountries.add(country);
        		}
        	}
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        //LEE PILOTOS
        try {
        	File inputFile = new File ("src/resources/files/pilotos.xml");
        	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        	Document doc = dBuilder.parse(inputFile);
        	doc.getDocumentElement().normalize();
        	
        	NodeList pilotNodes = doc.getElementsByTagName("piloto");
        	for (int i = 0; i < pilotNodes.getLength(); i++) {
        		Node pilotNode = pilotNodes.item(i);
        		if (pilotNode.getNodeType() == Node.ELEMENT_NODE) {
        			Element pilotElement = (Element) pilotNode;
        			Pilot pilot = new Pilot();
        			pilot.setBudget(Integer.parseInt(pilotElement.getElementsByTagName("presupuesto").item(0).getTextContent()));
        			pilot.setPositiondefense(Integer.parseInt(pilotElement.getElementsByTagName("defensa").item(0).getTextContent()));
        			pilot.setOvertaking(Integer.parseInt(pilotElement.getElementsByTagName("sobrepaso").item(0).getTextContent()));
        			pilot.setRanking(Integer.parseInt(pilotElement.getElementsByTagName("cuidadoNeumaticos").item(0).getTextContent()));
        			pilot.setStart(Integer.parseInt(pilotElement.getElementsByTagName("largada").item(0).getTextContent()));
        			pilot.setNamepilot(pilotElement.getElementsByTagName("nombre").item(0).getTextContent());
        			pilot.setNameabbreviated(pilotElement.getElementsByTagName("nombreAbreviado").item(0).getTextContent());
        			pilot.setQuantitycarrerwin(Integer.parseInt(pilotElement.getElementsByTagName("cantCarrerasGanadas").item(0).getTextContent()));
        			pilot.setQuantitypolepositions(Integer.parseInt(pilotElement.getElementsByTagName("cantPolePositions").item(0).getTextContent()));
        			pilot.setQuantitychampionswin(Integer.parseInt(pilotElement.getElementsByTagName("cantCampeonatos").item(0).getTextContent()));
        			pilot.setQuantitycarrer(Integer.parseInt(pilotElement.getElementsByTagName("cantParticipaciones").item(0).getTextContent()));
        			pilot.setCountry(new Country(pilotElement.getElementsByTagName("pais").item(0).getTextContent())); // creo que hay que crear un pais abreviado
        			String imagePath = pilotElement.getElementsByTagName("imagen").item(0).getTextContent();
        			BufferedImage image = ImageIO.read(new File(imagePath));
        			pilot.setInfographic(image);
        			// falta la cantidad de campeonatos participados en los xml
        			ListPilots.add(pilot);
        		}
        	} 
        	
        } catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    public void createPlayersRandom(Set<Car> usedCars, Set<Pilot> usedPilots, Set<Color> usedColors) {
        int cont = 1;
        ListColors.addAll(new ColorPanel(usedColors).getColors());

        
        for (Car car : ListCars) { 
            if (usedCars.contains(car)) continue;
    
            for (Pilot pilot : ListPilots) { 
                if (usedPilots.contains(pilot)) continue;
    
                for (Color color : ListColors) { 
                    if (usedColors.contains(color)) continue;
    
                    Player newPlayer = new PlayerSimulator("bot "+ cont, color,pilot, car);
                    ListPlayers.add(newPlayer); 
    
                    usedCars.add(car);
                    usedPilots.add(pilot);
                    usedColors.add(color);
                    cont ++;
                    break; 
                }
            }
        }
    }
    
    public void createTournament(){
            Collections.sort(ListPlayers);
            
    }


    //---------------------------------------------------------------Vista

    public void startGame(){
        
        frame_init = new Frame_Init(this);
        frame_init.setVisible(true);
        
    }
    public void createSelect(){

        frame_init.setVisible(false);
        frame_create = new Frame_Create(this);
        frame_create.setVisible(true);
    }

    public void selectCar(){
        frame_create.switchToPanel("panelCar");
    }
    public void selectPilot(){
        frame_create.switchToPanel("panelPilot");
    }

    public Frame_Init getFrame_init() {
        return frame_init;
    }
    public void setFrame_init(Frame_Init frame_init) {
        this.frame_init = frame_init;
    }
    public Frame_Create getFrame_create() {
        return frame_create;
    }
    public void setFrame_create(Frame_Create frame_create) {
        this.frame_create = frame_create;
    }
    public void removePlayer(BoxPlayer boxPlayer) {
        
        ListPlayers.remove(boxPlayer.getPlayer());
        Container parent = boxPlayer.getParent();
        frame_create.getPanelCreate().removePlayer(boxPlayer.getPlayer());
        frame_create.getPanelCreate().updateBoxes();
        parent.remove(boxPlayer);
        parent.revalidate();
        parent.repaint();
    }
    public void serializar() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(nameGame + ".ser"))) {
            outputStream.writeObject(this);
            System.out.println("Objeto serializado exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
