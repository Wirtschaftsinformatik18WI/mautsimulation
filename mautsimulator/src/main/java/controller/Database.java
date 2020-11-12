package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Origin;
import model.Transits;
import model.TransmitterData;
import model.Vehicle;

public class Database {

	Connection conn = null;

	public void DatabaseConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://ssabautzen3.ba-bautzen.de:5433/Simulator", "postgres",
					"postgres");
			conn.setAutoCommit(true);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	protected void finalize() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.err.println(e.toString());
			}
		}
	}

	public static Connection getConnectiontoMotorwayToll() throws SQLException {

		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://ssabautzen3.ba-bautzen.de:5433/MotorwayToll",
					"postgres", "postgres");
			conn.setAutoCommit(true);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		return conn;
	}

	public static Connection getConnectiontoSimulator() throws SQLException {

		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://ssabautzen3.ba-bautzen.de:5433/Simulator", "postgres",
					"postgres");
			conn.setAutoCommit(true);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		return conn;
	}

	public ArrayList<Vehicle> getAllVehicle() {
		ArrayList<Vehicle> allvehicle = new ArrayList<>();
		try {
			Connection conn = getConnectiontoMotorwayToll();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Public.\"Vehicle\"");
			while (rs.next()) {

				Vehicle vehicle = new Vehicle(Origin.valueOf(rs.getString("cid")), rs.getString("regNumber"));
				allvehicle.add(vehicle);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.err.println(e.toString());
		}
		return allvehicle;
	}

	public void setAllVehicle(ArrayList<Vehicle> vehicleList) {
		for (Vehicle v : vehicleList) {
			if (VehicleInList(v)) {
				try {
					Connection conn = getConnectiontoSimulator();
					
					String query = "INSERT INTO public.\"Vehicle\" values (?, ?)";
					PreparedStatement prepStmt = conn.prepareStatement(query);
					prepStmt.setString(1, v.getRegistrationNumber());
					prepStmt.setString(2, v.getOrigin().toString());
					
					prepStmt.executeQuery();
					
					prepStmt.close();
				} catch (SQLException e) {
					System.err.println(e.toString());
				}
			}
		}
	}

	public boolean VehicleInList(Vehicle v) {

		Statement stmt;
		try {
			Connection conn = getConnectiontoSimulator();
			stmt = conn.createStatement();
			String query = "SELECT * FROM public.\"Vehicle\" ";
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				if (rs.getString("regNumber").equals(v.getRegistrationNumber())) {
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public ArrayList<Vehicle> getrandomVehicles(int i) {
		ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();

		Statement stmt;
		try {
			stmt = conn.createStatement();
			String queryString = "SELECT * FROM \"Vehicle\" ORDER BY Random() LIMIT ?";

			PreparedStatement prepStmt = conn.prepareStatement(queryString);
			prepStmt.setInt(1, i);

			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) {
				Vehicle v = new Vehicle(Origin.valueOf(rs.getString("origin")), rs.getString("regNumber"));
				vehicleList.add(v);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.err.println(e.toString());
		}
		return vehicleList;
	}

	public ArrayList<Transits> getTransits() {
		Statement stmt;
		ArrayList<Transits> transits = new ArrayList<Transits>();

		try {
			stmt = conn.createStatement();
			String queryString = "SELECT * FROM \"Transits\" ";

			ResultSet rs = stmt.executeQuery(queryString);
			while (rs.next()) {
				Transits t = new Transits(rs.getString("pointA"), rs.getString("pointB"), rs.getInt("km"));
				transits.add(t);
			}

			rs.close();
			stmt.close();

		} catch (Exception e) {
			System.err.println(e.toString());
		}
		return transits;
	}

	public void insertTransmitterData(ArrayList<TransmitterData> transmitterData, int test) {

		for (TransmitterData t : transmitterData) {
			try {

				String queryString = "Insert into public.\"TransmitterData\" values (?, ?, ?, ?, ?, ?)";

				PreparedStatement prepStmt = conn.prepareStatement(queryString);
				prepStmt.setString(1, t.getPoint());
				prepStmt.setString(2, t.getTime());
				prepStmt.setString(3, t.getDate());
				prepStmt.setInt(4, test);
				prepStmt.setString(5, t.getOrigin().toString());
				prepStmt.setString(6, t.getRegistrationNumber());

				prepStmt.execute();
				prepStmt.close();

			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}

	}
	
	public void insertTransmitterDataMotorwayToll(ArrayList<TransmitterData> transmitterData) {
		
		for (TransmitterData t : transmitterData) {
			try {
				Connection conn = getConnectiontoMotorwayToll();
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
	
	public ArrayList<TransmitterData> getTransmitterData(int test) {
		
		ArrayList<TransmitterData> tList = new ArrayList<TransmitterData>();
		
		try {
			Connection conn = getConnectiontoSimulator();
			String query = "Select * from public.\"TransmitterData\" WHERE \"testNr\" = ?";
			
			PreparedStatement prepStmt = conn.prepareStatement(query);
			prepStmt.setInt(1, test);
			
			ResultSet rs = prepStmt.executeQuery();
			while(rs.next()) {
				TransmitterData t = new TransmitterData(rs.getString("point"), rs.getString("regNumber"), Origin.valueOf( rs.getString("origin")), rs.getString("date"), rs.getString("time"));
				tList.add(t);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return tList;
	}
}