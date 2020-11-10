package controller;

import java.util.TimerTask;

public class Simulationszeit extends TimerTask {
	
	private String simulTime;
	private int hour;
	private int minute;
	private int second;
	private int timeSpeed;
	
	private String simulDate;
	private int day;
	private int month;
	private int year;

	@Override
	public void run() {
		
		if(second < 59) {
			addSec();
		} else {
			setSecond(0);
			if(minute < 59) {
				addMin();
			} else {
				setMinute(0);
				if(hour < 23) {
					addHour();
				} else {
					setHour(0);
					if(day < 30) {
						addDay();
					} else {
						setDay(1);
						if(month < 12) {
							addMonth();
						} else {
							setMonth(1);
							addYear();
							}
						}
					}
					
			}
		}
		
		//simulTime = hour + ":" + minute + ":" + second; 
		System.out.println(getSimulTime() + " | " + getSimulDate());
		
	}
	
	
	public int getTimeSpeed() {
		return timeSpeed;
	}
	
	public void setTimeSpeed(String i) {
		switch(i) {
		
			case "2" : {
				timeSpeed = 500;
				break;
			}
			case "4" : {
				timeSpeed = 250;
				break;
			}
			case "8" : {
				timeSpeed = 125;
				break;
			}
			case "16" : {
				timeSpeed = 63;
				break;
			}
		}
	}

	public String checkTime(int i) {
		if(i < 10) {
			return "0"+i;
		}
		return Integer.toString(i);
	}

	public void addSec() {
		second = getSecond() + 1;	
		
	}
	public void addMin() {
		minute = getMinute() + 1;	
	}
	public void addHour() {
		hour = getHour() + 1;
	}

	
	public void addDay() {
		day = getDay() + 1;
	}
	public void addMonth() {
		month = getMonth() + 1;
	}
	public void addYear() {
		year = getYear() + 1;
	}
	
	
	
	public String getSimulTime() {
		simulTime = checkTime(getHour()) + ":" + checkTime(getMinute()) + ":" + checkTime(getSecond());
		return simulTime;
	}
	public String getSimulDate() {
		simulDate = checkTime(getDay()) + "." + checkTime(getMonth()) + "." + checkTime(getYear());
		return simulDate;
	}
	
	public void setSimulTime(String simulTime) {
		this.simulTime = simulTime;
	}
	
	public void setSimulDate(String simulDate){
		this.simulDate = simulDate;
	}
	
	
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
	public int getSecond() {
		return second;
	}
	public void setSecond(int second) {
		this.second = second;
	}	
}