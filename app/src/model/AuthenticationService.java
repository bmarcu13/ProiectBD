package model;

import java.net.PasswordAuthentication;
import java.sql.SQLException;

import exception.IncorrectCredentialsException;

public class AuthenticationService {
	private boolean isSignedIn = false;
	
	private DatabaseService databaseService = DatabaseService.getInstance();
	
	public void signIn(Credentials credentials) throws SQLException, IncorrectCredentialsException
	{
		databaseService.connectToDatabaseGeneric();
		
		Credentials dbCredentials = databaseService.getAccountDetails(credentials);
		
		if(dbCredentials.getPassword() == null)
		{
			throw new IncorrectCredentialsException();
		}
		
		System.out.println(dbCredentials.getUsername() + " " + dbCredentials.getPassword());
		
		databaseService.connectToDatabaseWithCredentials(dbCredentials);
		
		isSignedIn = true;
	}
	
	public boolean getIsSignedIn()
	{
		return isSignedIn; 
	}
}
