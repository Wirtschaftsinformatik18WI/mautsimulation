package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Origin;
import model.Vehicle;

public class Database {
	
	public static Connection getConnectiontoMotorwayToll() throws SQLException {

	    Connection conn = null;
	    try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://ssabautzen3.ba-bautzen.de:5433/MotorwayToll","postgres", "postgres");
			conn.setAutoCommit(true);
		} 
		catch ( Exception e ) {
			System.err.println( e.toString() );
		}
	    return conn;
	}

	public static Connection getConnectiontoSimulator() throws SQLException {

	    Connection conn = null;
	    try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://ssabautzen3.ba-bautzen.de:5433/Simulator","postgres", "postgres");
			conn.setAutoCommit(true);
		} 
		catch ( Exception e ) {
			System.err.println( e.toString() );
		}
	    return conn;
	}
	
	
	protected void finalize() {
		Connection conn = null;
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.err.println( e.toString() );
			}
		}
	}
	
	public static ArrayList<Vehicle> getAllVehicle() {
		ArrayList<Vehicle> allvehicle  = new ArrayList<>();
		try {
			Connection conn = getConnectiontoMotorwayToll();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT cid, regNumber  FROM Public.\"Vehicle\"" );
			while ( rs.next() ) {
				
				Vehicle vehicle = new Vehicle(Origin.valueOf(rs.getString("cid")), rs.getString("regNumber"));
				allvehicle.add(vehicle);
			}
			rs.close();
			stmt.close();
		} 
		catch (SQLException e) {
			System.err.println(e.toString());
		}
		return allvehicle;
	}
}