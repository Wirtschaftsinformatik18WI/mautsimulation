package model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Vehicle {
	
	private Origin origin;
    private String registrationNumber = new String();
    private ArrayList<String> transitList = new ArrayList<>();
    private String actuallPos;
    private String lastPos;
    private LocalTime estimatedArrival;
    private LocalDateTime estimatedArrival2;
    private int passedPoints;
    
    public Vehicle (Origin origin, String registrationNumber) {
        this.origin = origin;
        this.registrationNumber = registrationNumber;
    }

	public Origin getOrigin() {
		return origin;
	}

	public void setOrigin(Origin origin) {
		this.origin = origin;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public ArrayList<String> getTransitList() {
		return transitList;
	}

	public void setTransitList(ArrayList<String> transitList) {
		this.transitList = transitList;
	}

	public String getActuallPos() {
		return actuallPos;
	}

	public void setActuallPos(String acuallPos) {
		this.actuallPos = acuallPos;
		this.passedPoints++;
	}

	public LocalTime getEstimatedArrival() {
		return estimatedArrival;
	}

	public void setEstimatedArrival(LocalTime estimatedArrival) {
		this.estimatedArrival = estimatedArrival;
	}
	public int getPassedPoints() {
		return passedPoints;
	}

	public String getLastPos() {
		return lastPos;
	}

	public void setLastPos(String lastPos) {
		this.lastPos = lastPos;
	}

	public LocalDateTime getEstimatedArrival2() {
		return estimatedArrival2;
	}

	public void setEstimatedArrival2(LocalDateTime estimatedArrival2) {
		this.estimatedArrival2 = estimatedArrival2;
	}
	
}
