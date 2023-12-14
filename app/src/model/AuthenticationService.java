package model;

import java.net.PasswordAuthentication;
import java.sql.SQLException;

public class AuthenticationService {
	private boolean isSignedIn = false;
	
	private DatabaseService databaseService = new DatabaseService();
	
	public void signIn(PasswordAuthentication credentials) throws SQLException
	{
		databaseService.connectToDatabaseGeneric();
		isSignedIn = true;
	}
	
	public boolean getIsSignedIn()
	{
		return isSignedIn; 
	}
}
