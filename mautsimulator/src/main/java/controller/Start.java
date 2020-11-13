package controller;

import java.util.Timer;

/**
 * 
 * class to:
 * 		~ start Simulationszeit for Simulation
 * 
 * @author marcel.lehmann Mail: 18wi1341@ba-bautzen.de
 * */
public class Start {
	
	
	public void simulZeitStarten() {
		
		Timer simulTimer = new Timer();
		Simulationszeit simulationszeit = new Simulationszeit();
		//Startzeit der Simulation festlegen
		simulationszeit.setHour(12);
		simulationszeit.setMinute(00);
		simulationszeit.setSecond(00);
		//Startdatum der Simulation festlegen
		simulationszeit.setDay(30);
		simulationszeit.setMonth(11);
		simulationszeit.setYear(2020);
		//Geschwindigkeit der Simulationszeit festlegen
		
		simulTimer.schedule(simulationszeit, 0, simulationszeit.getTimeSpeed());
		
	}
}