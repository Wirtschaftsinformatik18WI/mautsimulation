package model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Vehicle {
	
	private Origin origin;
    private String registrationNumber = new String();
    private String actuallPos;
    private String lastPos;
    private LocalDateTime estimatedArrival;
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

	public String getActuallPos() {
		return actuallPos;
	}

	public void setActuallPos(String acuallPos) {
		this.actuallPos = acuallPos;
		this.passedPoints++;
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

	public LocalDateTime getEstimatedArrival() {
		return estimatedArrival;
	}

	public void setEstimatedArrival(LocalDateTime estimatedArrival) {
		this.estimatedArrival = estimatedArrival;
	}
	
}
