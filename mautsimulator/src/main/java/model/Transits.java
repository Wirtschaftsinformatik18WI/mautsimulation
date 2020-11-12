package model;

/**
 * 
 * class to:
 * 		~ save Transits from Database for local handling
 * 
 * @author marcel.lehmann Mail: 18wi1341@ba-bautzen.de
 * */
public class Transits {

	private String pointA;
	private String pointB;
	private int km;
	
	public Transits(String pointA, String pointB, int km) {
		this.pointA = pointA;
		this.pointB = pointB;
		this.km = km;
		
	}
	
	public String getPointA() {
		return pointA;
	}
	public void setPointA(String pointA) {
		this.pointA = pointA;
	}
	public String getPointB() {
		return pointB;
	}
	public void setPointB(String pointB) {
		this.pointB = pointB;
	}
	public int getKm() {
		return km;
	}
	public void setKm(int km) {
		this.km = km;
	}
	
	
}
