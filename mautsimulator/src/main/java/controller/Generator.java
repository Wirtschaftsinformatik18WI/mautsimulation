package controller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import model.TransmitterData;
import model.Vehicle;

import model.*;

public class Generator {

	Random random = new Random();

	public Simulationszeit simulationszeit = new Simulationszeit();
	public ArrayList<Vehicle> vehicleList;
	public ArrayList<Transits> transitList;
	public ArrayList<TransmitterData> transmitterList = new ArrayList<TransmitterData>();

	public ArrayList<TransmitterData> startGeneration(int year, int month, int day, int hour, int minute, int second, int cars) {

		final Database db = new Database();
		db.DatabaseConnection();

		Timer simulTimer = new Timer();
		simulationszeit.setYear(year);
		simulationszeit.setMonth(month);
		simulationszeit.setDay(day);
		simulationszeit.setHour(hour);
		simulationszeit.setMinute(minute);
		simulationszeit.setSecond(second);

		simulationszeit.setSimulTime(
				simulationszeit.getHour() + ":" + simulationszeit.getMinute() + ":" + simulationszeit.getSecond());
		simulationszeit.setSimulDate(
				simulationszeit.getDay() + "." + simulationszeit.getMonth() + "." + simulationszeit.getYear());
		System.out.print(simulationszeit.getSimulTime());
		System.out.println("");
		simulTimer.schedule(simulationszeit, 0, 1);

		vehicleList = db.getrandomVehicles(cars);
		transitList = db.getTransits();

		Timer genTimer = new Timer();
		genTimer.schedule(new TimerTask() {

			@Override
			public void run() {

				LocalDateTime time2 = LocalDateTime.of(simulationszeit.getYear(), simulationszeit.getMonth(), simulationszeit.getDay(), simulationszeit.getHour(), simulationszeit.getMinute(),
						simulationszeit.getSecond());
				for (Vehicle vehicle : vehicleList) {

					if (vehicle.getActuallPos() == null) {
						System.out.println("First Pass");
						Transits transit = transitList.get(random.nextInt(transitList.size()));
						vehicle.setActuallPos(transit.getPointA());

						TransmitterData t = new TransmitterData(transit.getPointA(), vehicle.getRegistrationNumber(),
								vehicle.getOrigin(), simulationszeit.getSimulDate() , simulationszeit.getSimulTime());
						transmitterList.add(t);

						String pointB = transit.getPointB();
						vehicle.setActuallPos(pointB);

						int driveTime = (int) Math.floor(transit.getKm() / (120 / 3.6) * 1000 / 60);

//						time = LocalTime.of(simulationszeit.getHour(), simulationszeit.getMinute(),
//								simulationszeit.getSecond());
						LocalDateTime timeV = time2;
						vehicle.setEstimatedArrival2(timeV.plusMinutes(driveTime));
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Automatisch generierter Erfassungsblock
							e.printStackTrace();
						}
						
					} else {
						// check if vehicle passed too many points
						if (vehicle.getPassedPoints() > 3 && vehicle.getActuallPos().startsWith("D")) {
							// -----------
							ArrayList<Transits> fahrten = new ArrayList<Transits>();

							for (Transits t : transitList) {
								if (t.getPointA().equals(vehicle.getLastPos())) {
									if (t.getPointB().startsWith("A")) {
										fahrten.add(t);
									}
								}
							}
							if (fahrten.isEmpty()) {
								vehicle.setActuallPos("A-XX");
								break;
							}
							Transits newTrans = fahrten.get(random.nextInt(1));
							// ---------
							vehicle.setActuallPos(newTrans.getPointB());
							break;
						}

						// Check if time is ready for Vehicle to Arrive
						if (time2.isAfter(vehicle.getEstimatedArrival2())) {
							String point = vehicle.getActuallPos();
							TransmitterData t = new TransmitterData(point, vehicle.getRegistrationNumber(),
									vehicle.getOrigin(), simulationszeit.getSimulDate() , simulationszeit.getSimulTime());
							transmitterList.add(t);

							// Check if next point is "Abfahrt"
							if (vehicle.getActuallPos().startsWith("A")) {
								vehicleList.remove(vehicle);
								break;
							}
							// ------------
							ArrayList<Transits> fahrten = new ArrayList<Transits>();

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
							// ----------
							vehicle.setLastPos(newTrans.getPointA());
							vehicle.setActuallPos(newTrans.getPointB());

							int driveTime = (int) Math.floor(newTrans.getKm() / (120 / 3.6) * 1000 / 60);

//							time = LocalTime.of(simulationszeit.getHour(), simulationszeit.getMinute(),
//									simulationszeit.getSecond());
							LocalDateTime timeV = time2;
							vehicle.setEstimatedArrival2(timeV.plusMinutes(driveTime));
//							vehicle.setEstimatedArrival(time.plusMinutes(driveTime));

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
		db.insertTransit(transmitterData23, testcase);

	}

}