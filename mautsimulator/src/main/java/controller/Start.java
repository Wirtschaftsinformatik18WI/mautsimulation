package controller;

import java.util.Timer;

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