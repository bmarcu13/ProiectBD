package model;

import java.net.PasswordAuthentication;
import java.sql.SQLException;

import exception.IncorrectCredentialsException;

public class AuthenticationService {
	private boolean isSignedIn = false;
	private int accType;
	private String username;

	private static final int ACC_HR = 0;
	private static final int ACC_FINANCIAL = 1;
	private static final int ACC_MEDICAL = 2;
	
	private DatabaseService databaseService = DatabaseService.getInstance();
	
	public void signIn(Credentials credentials) throws SQLException, IncorrectCredentialsException
	{
		databaseService.connectToDatabaseGeneric();
		
		Credentials dbCredentials = databaseService.getAccountDetails(credentials);
		
		if(dbCredentials.getPassword() == null)
		{
			throw new IncorrectCredentialsException();
		}
		
		databaseService.connectToDatabaseWithCredentials(dbCredentials);
		
		switch(dbCredentials.getUsername())
		{
			case "acc_hr":
				accType = ACC_HR;
				break;
			case "acc_financial":
				accType = ACC_FINANCIAL;
				break;
			case "acc_medical":
				accType = ACC_MEDICAL;
				break;
		}
		username = credentials.getUsername();
		isSignedIn = true;
	}
	
	public boolean getIsSignedIn()
	{
		return isSignedIn; 
	}
	
	public String getUsername()
	{
		return username;
	}
}
