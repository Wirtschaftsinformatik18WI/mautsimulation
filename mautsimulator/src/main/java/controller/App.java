package controller;

import java.util.ArrayList;
import model.TransmitterData;
import model.Vehicle;

public class App {

	
	public static void main(String[] args) {
		
//		Database db = new Database();
//		ArrayList<Vehicle> vList = db.getAllVehicle();
//		db.setAllVehicle(vList);

		Generator gen = new Generator();

		gen.waitForGenerationAndSave(2020, 11, 30, 23, 12, 30, 6, 1);
		
//		ArrayList<TransmitterData> tList = db.getTransmitterDataSimulator(5);
//		db.insertTransmitterDataMotorwayToll(tList);
		
		
		
	}				
}	