package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Origin;
import model.TransmitterData;

public class Senden {
	
	static Connection c = null;
	ResultSet rsData;
	java.sql.Statement statement = null;
	
	ArrayList<TransmitterData> readedTransmitterData = new ArrayList<>();
	ArrayList<TransmitterData> actualTransmitterData = new ArrayList<>();
	
	
	//generierte Transmitterdaten nach ausgewähltem Fall auslesen
	public ResultSet readTransmitterData(String t) {
		
		try {
			c = Database.getConnectiontoSimulator();
			statement = c.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
		try {
			//WHERE TransmitterData.testNr ='" + t + "';"
			rsData = statement.executeQuery("SELECT \"point\", \"time\", \"date\", \"origin\", \"regNumber\" FROM Public.\"TransmitterData\"  WHERE \"testNr\" = '" + t + "' ORDER BY \"time\" ASC;");
			
		} 
		catch ( Exception e ) {
			System.err.println(e.toString());
		}
		
		return rsData;
	}	
	
	
	//Transmitterdaten in einem Array speichern
	public ArrayList<TransmitterData> saveTransmitterData(ResultSet resultSet) throws SQLException{
		
		ResultSetMetaData rsMetaData = resultSet.getMetaData();
     	int columnCount = rsMetaData.getColumnCount();
     	
     	while(resultSet.next()) {
      	 	
      	 	TransmitterData e = new TransmitterData(resultSet.getString("point"), resultSet.getString("regNumber"), Origin.valueOf(resultSet.getString("origin")), resultSet.getString("date"), resultSet.getString("time"));
      	 	readedTransmitterData.add(e);
      	 }
      	
		
		
		return readedTransmitterData;
	}
	
	
	//eine Arrayliste wiedergeben, die die aktuellen Daten abhängig der Simulationszeit beeinhaltet
	public ArrayList<TransmitterData> selectActualTransmitterData(ArrayList<TransmitterData> list, String simZeit){
		
		for (TransmitterData t : list) {
			if (t.getTime().substring(0, 5).equals(simZeit.substring(0, 5))) {
				actualTransmitterData.add(t);
			}
		}
		
		return actualTransmitterData;
	}
	
	
	//Schreiben/Senden der Daten in die Datenbank der Mautsoftware
	public void insertTransmitterDataMotorwayToll(ArrayList<TransmitterData> transmitterData) {
		
		for (TransmitterData t : transmitterData) {
			try {
				Connection conn = Database.getConnectiontoMotorwayToll();
				String queryString = "Insert into public.\"transmitterData\" values (?, ?, ?, ?, ?)";

				PreparedStatement prepStmt = conn.prepareStatement(queryString);
				prepStmt.setString(1, t.getPoint());
				prepStmt.setString(2, t.getTime());
				prepStmt.setString(3, t.getDate());
				prepStmt.setString(4, t.getOrigin().toString());
				prepStmt.setString(5, t.getRegistrationNumber());

				prepStmt.execute();
				prepStmt.close();

			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}
	}	
}