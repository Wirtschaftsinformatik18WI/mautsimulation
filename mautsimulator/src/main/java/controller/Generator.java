package controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import model.TransmitterData;
import model.Vehicle;

import model.*;

/**
 * 
 * class to:
 * 		~ create an ArrayList with random transits for specific Vehicles
 * 
 * @author marcel.lehmann Mail: 18wi1341@ba-bautzen.de
 * */
public class Generator {

	//init as class parameter
	Random random = new Random();
	public Simulationszeit simulationszeit = new Simulationszeit();
	public ArrayList<Vehicle> vehicleList;
	public ArrayList<Transits> transitList;
	public ArrayList<TransmitterData> transmitterList = new ArrayList<TransmitterData>();

	/**
	 * 
	 * @param year 
	 * @param month 
	 * @param day 
	 * @param hour 
	 * @param minute 
	 * @param second 
	 * @param cars The vehicles to generate TransmitterData for
	 * @return an ArrayList with all generated TransmitterData objects
	 */
	public ArrayList<TransmitterData> startGeneration(int year, int month, int day, int hour, int minute, int second, int cars) {
		
		//Database Connection to Simulation
		final Database db = new Database();
		db.DatabaseConnection();

		//Start of the Simulation Time values
		Timer simulTimer = new Timer();
		simulationszeit.setYear(year);
		simulationszeit.setMonth(month);
		simulationszeit.setDay(day);
		simulationszeit.setHour(hour);
		simulationszeit.setMinute(minute);
		simulationszeit.setSecond(second);

		//Setting Simulation Time and date values and Start Timer
		simulationszeit.setSimulTime(
				simulationszeit.getHour() + ":" + simulationszeit.getMinute() + ":" + simulationszeit.getSecond());
		simulationszeit.setSimulDate(
				simulationszeit.getDay() + "." + simulationszeit.getMonth() + "." + simulationszeit.getYear());
		System.out.print(simulationszeit.getSimulTime());
		System.out.println("");
		simulTimer.schedule(simulationszeit, 0, 1);

		//dekleration of vehicle and transits
		vehicleList = db.getrandomVehicles(cars);
		transitList = db.getTransits();

		//Start TimerTask for the Generation
		Timer genTimer = new Timer();
		genTimer.schedule(new TimerTask() {

			@Override
			public void run() {
				//get Simulation Time as LocalDateTime
				LocalDateTime time2 = LocalDateTime.of(simulationszeit.getYear(), simulationszeit.getMonth(), simulationszeit.getDay(), simulationszeit.getHour(), simulationszeit.getMinute(),
						simulationszeit.getSecond());
				
				for (Vehicle vehicle : vehicleList) {

					//if vehicle hasnt passed a point yet
					if (vehicle.getActuallPos() == null) {
						//Get Random Transit from ArrayList transits from Database. Point A is 100% a "D" any random Transit can be the starting point
						Transits transit = transitList.get(random.nextInt(transitList.size()));
						vehicle.setActuallPos(transit.getPointA());

						//Insert new transmitterData into transmitterData
						TransmitterData t = new TransmitterData(transit.getPointA(), vehicle.getRegistrationNumber(),
								vehicle.getOrigin(), simulationszeit.getSimulDate() , simulationszeit.getSimulTime());
						transmitterList.add(t);

						//get the Destination of the Transit and set it to actuall point
						String pointB = transit.getPointB();
						vehicle.setActuallPos(pointB);
						
						int driveTime = (int) Math.floor(transit.getKm() / (120 / 3.6) * 1000 / 60);
						LocalDateTime timeV = time2;
						vehicle.setEstimatedArrival(timeV.plusMinutes(driveTime));
						
						//Just so the Cars wont start driving at the same time
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
					} else {
						// check if vehicle passed too many points and next point would still be "D"
						if (vehicle.getPassedPoints() > 3 && vehicle.getActuallPos().startsWith("D")) {
							ArrayList<Transits> fahrten = new ArrayList<Transits>();
							//getting possible "A" point for the current location
							for (Transits t : transitList) {
								if (t.getPointA().equals(vehicle.getLastPos())) {
									if (t.getPointB().startsWith("A")) {
										fahrten.add(t);
									}
								}
							}
							if (fahrten.isEmpty()) {
								System.err.println("Error getting possible 'A' point");
								break;
							}
							Transits newTrans = fahrten.get(random.nextInt(1));
							vehicle.setActuallPos(newTrans.getPointB());
							break;
						}

						// Check if time is ready for Vehicle to Arrive
						if (time2.isAfter(vehicle.getEstimatedArrival())) {
							String point = vehicle.getActuallPos();
							TransmitterData t = new TransmitterData(point, vehicle.getRegistrationNumber(),
									vehicle.getOrigin(), simulationszeit.getSimulDate() , simulationszeit.getSimulTime());
							transmitterList.add(t);

							// Check if next point is "Abfahrt"
							if (vehicle.getActuallPos().startsWith("A")) {
								vehicleList.remove(vehicle);
								break;
							}
							ArrayList<Transits> fahrten = new ArrayList<Transits>();
							//get new point
							for (Transits tran : transitList) {
								if (tran.getPointA().equals(vehicle.getActuallPos())) {
									fahrten.add(tran);
								}
							}
							if (fahrten.isEmpty()) {
								vehicle.setActuallPos("A-XY");
								break;
							}

							Transits newTrans = fahrten.get(random.nextInt(1));
							vehicle.setLastPos(newTrans.getPointA());
							vehicle.setActuallPos(newTrans.getPointB());

							int driveTime = (int) Math.floor(newTrans.getKm() / (120 / 3.6) * 1000 / 60);
							LocalDateTime timeV = time2;
							vehicle.setEstimatedArrival(timeV.plusMinutes(driveTime));

						}
					}
				}
				if (vehicleList.isEmpty()) {
					cancel();
					simulationszeit.cancel();
					simulTimer.cancel();
					genTimer.cancel();
				}
			}
		}, 0, 1);
		return transmitterList;
	}
	
	/**
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 * @param second
	 * @param testcase flag for witch testcase the TransmitterData should be generated
	 * @param cars 
	 * 
	 * method for saving generated Data into Database Needs CompletableFuture and Thread.sleep to wait until all Data is Generated and save it into Database
	 */
	public void waitForGenerationAndSave(int year, int month, int day, int hour, int minute, int second, int testcase, int cars) {
		Generator gen = new Generator();
		Database db = new Database();
		db.DatabaseConnection();

		ArrayList<TransmitterData> transmitterData23 = new ArrayList<>();

		CompletableFuture<ArrayList<TransmitterData>> future = CompletableFuture.supplyAsync(() -> {
			ArrayList<TransmitterData> transmitterData = new ArrayList<>();
			transmitterData = gen.startGeneration(year, month, day, hour, minute, second, cars);
			return transmitterData;

		});

		try {
			Thread.sleep(1000 * 60 * 2);
			transmitterData23.addAll(future.get());
		} catch (InterruptedException e) {
			// TODO Automatisch generierter Erfassungsblock
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Automatisch generierter Erfassungsblock
			e.printStackTrace();
		}

		for (TransmitterData t : transmitterData23) {
			System.out.println(
					t.getDate() + " >> " + t.getTime() + " >> " + t.getPoint() + " >> " + t.getRegistrationNumber());
		}
		db.insertTransmitterDataSimulator(transmitterData23, testcase);

	}

}