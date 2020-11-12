package controller;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import model.Transits;
import model.TransmitterData;
import model.Vehicle;

public class App {

	
	public static void main(String[] args) {
//		
//		int testCaseID;
//		int timeSpeedMultiplikator;
//		
//		System.out.println("Willkommen zum Mautsimulator");
//		System.out.println("");
//		System.out.println("Testfälle:");
//		System.out.println("");
//		System.out.println("1 - Auto auf der Strecke verschollen");
//		System.out.println("2 - Performance Test - Ganz viele Autos");
//		System.out.println("3 - Rettungsgasse nicht beachtet");
//		System.out.println("4 - Geschwindigkeitsverstoß");
//		System.out.println("5 - Fahrt startet am letzten Tag des Monats und endet am ersten Tag des neuen Monats");
//		System.out.println("");
//		System.out.println("Geben Sie die gewünschte Nummer des Testfalls an");
//		Scanner testfallEingabe = new Scanner(System.in);
//		testCaseID = testfallEingabe.nextInt();
//		System.out.println("");
//		System.out.println("Geschwindigkeit der Simulationszeit:");
//		System.out.println("2 - 2-fache Geschwindigkeit");
//		System.out.println("4 - 4-fache Geschwindigkeit");
//		System.out.println("8 - 8-fache Geschwindigkeit");
//		System.out.println("16 - 16-fache Geschwindigkeit");
//		System.out.println("");
//		System.out.println("Geben Sie die gewünschte Nummer der Geschwindikeit an");
//		timeSpeedMultiplikator = testfallEingabe.nextInt();
//		System.out.println("");
//		System.out.println("Simulation mit Testfall: " + testCaseID + " startet in " + timeSpeedMultiplikator + "-facher Geschwindigkeit!");
//		System.out.println("");
//		System.out.println("Folgende Autos werden generiert:");
//		System.out.println("");
//		
//		Generator gen = new Generator();
//		for (Vehicle v : gen.generateVehicle()) {
//			System.out.println("Herkunft: " + v.getOrigin() + " | " + "Kennzeichen: " + v.getRegistrationNumber());
//		}
		
//		System.out.println("");
		
		//--------------------------Simulationszeit------------------------------------------
//		Timer simulTimer = new Timer();
//		Simulationszeit simulationszeit = new Simulationszeit();
		//Startzeit der Simulation festlegen
//		simulationszeit.setHour(12);
//		simulationszeit.setMinute(00);
//		simulationszeit.setSecond(00);
		//Startdatum der Simulation festlegen
//		simulationszeit.setDay(30);
//		simulationszeit.setMonth(11);
//		simulationszeit.setYear(2020);
		//Geschwindigkeit der Simulationszeit festlegen
		//simulationszeit.setTimeSpeed(timeSpeedMultiplikator);
		
//		System.out.println("Startzeit: " + simulationszeit.getSimulTime());
//		System.out.println("Startdatum: " + simulationszeit.getSimulDate());
//		System.out.println("");
		
//		simulTimer.schedule(simulationszeit, 0, simulationszeit.getTimeSpeed());
		
		
		/*//Fahrt der Autos starten und Standortdaten sammeln
		Timer transmitterTimer = new Timer();
		TransmitterZeit transmitterZeit = new TransmitterZeit();
		System.out.println("Abfrage der Transmitterdaten startet!");
		transmitterTimer.schedule(transmitterZeit, 2000, 4000);*/
		
		Database db = new Database();
//		ArrayList<Vehicle> vList = db.getAllVehicle();
//		db.setAllVehicle(vList);
//		
//		Generator gen = new Generator();
//		
//		gen.waitForGenerationAndSave(2020, 2, 1, 8, 0, 0, 6, 6);
		
		ArrayList<TransmitterData> tList = db.getTransmitterData(5);
		db.insertTransmitterDataMotorwayToll(tList);
		
		
		
	}				
}	