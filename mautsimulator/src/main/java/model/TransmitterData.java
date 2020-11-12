package model;

/**
 * 
 * class to:
 * 		~ create TransmitterData 
 * 
 * @author marcel.lehmann Mail: 18wi1341@ba-bautzen.de
 * */
public class TransmitterData {
	
	private String point;
	private String registrationNumber;
	private Origin origin;
	private String date;
	private String time;
	
	public TransmitterData(String point, String reg, Origin origin, String date, String time) {
		this.point = point;
		this.registrationNumber = reg;
		this.origin = origin;
		this.date = date;
		this.time = time;
	}
	
	public TransmitterData() {
		
	}
	
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public Origin getOrigin() {
		return origin;
	}
	public void setOrigin(Origin origin) {
		this.origin = origin;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

}
