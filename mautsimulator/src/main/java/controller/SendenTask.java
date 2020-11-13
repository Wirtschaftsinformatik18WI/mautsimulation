package controller;

import java.util.ArrayList;
import java.util.TimerTask;

import model.TransmitterData;

public class SendenTask extends TimerTask{
	
	
	ArrayList<TransmitterData> list = new ArrayList<>();
	Senden senden = new Senden();
	

	@Override
	public void run() {
		
		senden.insertTransmitterDataMotorwayToll(list);
		System.out.println("SENDEN");
	}
	
	
	
	public ArrayList<TransmitterData> getList() {
		return list;
	}

	public void setList(ArrayList<TransmitterData> list) {
		this.list = list;
	}
}
