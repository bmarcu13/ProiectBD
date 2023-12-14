package model;

import java.sql.*;

public class DatabaseService {
	
	private final String URL = "jdbc:mysql://localhost:3306/policlinica";
	private final String USERNAME = "policlinica_generic_user";
	private final String GENERIC_PASSWORD = "Strongpassword";
	
	private Connection connection;
	
	public void connectToDatabaseGeneric() throws SQLException
	{
		connection = DriverManager.getConnection(URL, USERNAME, GENERIC_PASSWORD);
	}
}
