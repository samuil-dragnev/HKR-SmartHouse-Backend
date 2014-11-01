package com.hkr.smarthouse.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBHandler {

	public DBHandler() {}
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/localdatabase",
				"root","fagaha");
	}

	private void closeConnection(Connection connection){
		if (connection == null) {
			return;
		} else {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getStateOfCoffeeMachine() {
		String state = "";
		String sql = "select state from device where name = 'Coffee Machine';";
		
		Connection con = null;
		try {
			con =getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				state = result.getString("state");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
		return state;
	}
	
	public String updateStateOfCoffeeMachine(String state) {
		String sql = "update device set state = '"+state+"' where name = 'Coffee Machine'";
		
		Connection con = null;
		try {
			con =getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
		return state;
	}

}
