package controller;

import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import java.awt.image.BufferedImage;
import java.io.File;
import model.*;
import view.Frame_Create;
import view.Frame_Init;

public class Championship {
    
    private List<Pilot> ListPilots = new ArrayList<>();
    private List<Car> ListCars = new ArrayList<>();
    private List<Player> ListPlayers = new ArrayList<>();
    private List<Circuit> ListCircuits = new ArrayList<>();
    private List<Race> ListRace = new ArrayList<>();
    private List<Date> ListDate = new ArrayList<>();
    private List <Country> ListCountries = new ArrayList<>();
    private String nameGame;
    private int numberPlayers;
    private Frame_Init frame_init;
    private Frame_Create frame_create;
    

    public Championship(){

    
	     //Se crean las ruedas de los autos con su atributo durabilidad !! 
	Tires soft = new Soft(70);
	Tires wet = new Wet();
	Tires hard= new Hard();
	Tires medioum = new Medium();
	
    ChargeXML();
    CreateRaces();
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

    public void CreatePlayer(String name, String color, Pilot pilot, Car car){
        PlayerReal player = new PlayerReal(name, color, pilot, car);
        ListPlayers.add(player);
        Collections.sort(ListPlayers);
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
    
    private void CreateRaces(){
        //Se crean las carreras usando la lista de circuitos ,con las fechas del cronograma.
    }
   
    private void ChargeXML(){
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
        			Circuit circuit = new Circuit();
        			circuit.setNumberflaps(Integer.parseInt(circuitElement.getElementsByTagName("cantVueltas").item(0).getTextContent()));
        			String imagePath = circuitElement.getElementsByTagName("infografia").item(0).getTextContent();
        			BufferedImage image = ImageIO.read(new File(imagePath));
        			circuit.setInfographic(image);
        			circuit.setTracklength(Integer.parseInt(circuitElement.getElementsByTagName("longitud").item(0).getTextContent()));
        			circuit.setNumberovertaking(Integer.parseInt(circuitElement.getElementsByTagName("cantZonasSobrepaso").item(0).getTextContent()));
        			circuit.setNumbercurves(Integer.parseInt(circuitElement.getElementsByTagName("cantCurvas").item(0).getTextContent()));
        			circuit.setTimerecord(LocalTime.ofSecondOfDay(Integer.parseInt(circuitElement.getElementsByTagName("recordVueltaRapida").item(0).getTextContent())));
        			circuit.setCountrycircuit(new Country(circuitElement.getElementsByTagName("pais").item(0).getTextContent()));
        			circuit.setNamecircuit(circuitElement.getElementsByTagName("nombre").item(0).getTextContent());
        			ListCircuits.add(circuit);
        			
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
        			// falta la cantidad de campeonatos participados en los xml
        			ListPilots.add(pilot);
        		}
        	} 
        	
        } catch (Exception e) {
    		e.printStackTrace();
    	}
    }

    //---------------------------------------------------------------Vista

    public void StartGame(){
        
        frame_init = new Frame_Init(this);
        frame_init.setVisible(true);
        
    }
    public void CreateSelect(){

        frame_init.setVisible(false);
        frame_create = new Frame_Create(this);
        frame_create.setVisible(true);
    }

    public void SelectCar(){
        frame_create.switchToPanel("panelCar");
    }
    public void SelectPilot(){
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
    

}
