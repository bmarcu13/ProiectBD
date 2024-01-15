package model;

import java.net.PasswordAuthentication;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import exception.IncorrectCredentialsException;

public class AuthenticationService {
	private boolean isSignedIn = false;
	private int accType;
	private String username;

	private String email;
	
	private List<Callback> callbacks = new ArrayList<Callback>();

	public static final int ACC_HR = 0;
	public static final int ACC_FINANCIAL = 1;
	public static final int ACC_MEDICAL_RECEPTIONIST = 2;
	public static final int ACC_MEDICAL_ASSISTENT = 3;
	public static final int ACC_MEDICAL_DOCTOR = 4;
	
	private DatabaseService databaseService = DatabaseService.getInstance();
	
	public void signIn(Credentials credentials) throws SQLException, IncorrectCredentialsException
	{
		this.email = credentials.getUsername();
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
			case "acc_medical_doctor":
				accType = ACC_MEDICAL_DOCTOR;
				break;
			case "acc_medical_assistent":
				accType = ACC_MEDICAL_ASSISTENT;
				break;
			case "acc_medical_receptionist":
				accType = ACC_MEDICAL_RECEPTIONIST;
				break;
		}
		username = credentials.getUsername();
		isSignedIn = true;
		
		executeWhenSuccessfullyAuthenticated();
	}
	
	public void addCallback(Callback c)
	{
		callbacks.add(c);
	}
	
	public void executeWhenSuccessfullyAuthenticated()
	{
		for(Callback c : callbacks)
		{
			c.execute();
		}
	}
	
	public boolean getIsSignedIn()
	{
		return isSignedIn; 
	}
	
	public int getAccountType()
	{
		System.out.println(accType);
		return accType;
	}
	
	public String getUsername()
	{
		return username;
	}
	public String getEmail() { return this.email; }
}
