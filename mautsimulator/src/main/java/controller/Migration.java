package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Origin;
import model.Vehicle;

public class Migration {
	static Connection c = null;
	
	protected void finalize() {
		if (c != null) {
			try {
				c.close();
			} catch (SQLException e) {
				System.err.println( e.toString() );
			}
		}
	}
	
	public static Vehicle add(Vehicle vehicle) {
		try {
			c = Database.getConnectiontoSimulator();
		} catch (SQLException e1) {
			// TODO Automatisch generierter Erfassungsblock
			e1.printStackTrace();
		}
		if (c == null) {
			return null;
		}
		try {
			String queryString = "INSERT INTO Public.\"Vehicle\"(regnumber, origin)" +
					" VALUES (?, ?)";
			System.out.println(queryString);
			PreparedStatement prepStmt = c.prepareStatement(queryString);
			prepStmt.setString (1, vehicle.getRegistrationNumber());
			prepStmt.setString (2, vehicle.getOrigin().toString());

			prepStmt.execute();
			prepStmt.close();

			return vehicle;
		} 
		catch ( Exception e ) {
			System.err.println(e.toString());
			return null;
		}
	}
	
	public boolean setAllVehicle(Vehicle vehicle) {
		if (vehicle == null) {
			return false;
		}

		// Existiert das Auto bereits?
		if (vehicle.getRegistrationNumber() == null) {
			// Auto existiert noch nicht!
			// -> Auto hinzufügen!
			return (add(vehicle) != null);
		}

		// Auto existiert bereits!
		// -> Auto akualisieren!
		try {
			String queryString = "UPDATE VEHICLE SET " +
			"CID = ?, " +
			"WHERE regNumber = ?;";

			PreparedStatement prepStmt = c.prepareStatement(queryString);
			prepStmt.setString (1, "regNumber");
			prepStmt.setString (2, "origin");

			prepStmt.execute();
			prepStmt.close();

			return true;
		} 
		catch (SQLException e) {
			System.err.println(e.toString());
			e.printStackTrace();
			return false;
		}
	}
} 