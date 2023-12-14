package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.PasswordAuthentication;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;

import app.IController;
import model.AuthenticationService;
import view.ApplicationView;
import view.LoginView;

public class LoginController implements IController {
	private LoginView loginView;
	private AuthenticationService authenticationService;
	private ApplicationView applicationView;
	
	PasswordAuthentication passwordAuthentication;
	
	public LoginController(LoginView _loginView, AuthenticationService _auAuthenticationService, ApplicationView _aplicationView)
	{
		this.loginView= _loginView;
		this.authenticationService = _auAuthenticationService;
		this.applicationView = _aplicationView;
		
		applicationView.addCardComponent("LOGIN", _loginView);
		
		loginView.setLoginButtonActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				collectCredentials();
				signIn(passwordAuthentication);
			}
		});
	}
	
	public boolean isAvailable()
	{
		return !authenticationService.getIsSignedIn();
	}
	
	public void ShowComponent()
	{
//		applicationView.showCardComponent("LOGIN");
	}
	
	private void collectCredentials()
	{
		passwordAuthentication = new PasswordAuthentication(loginView.getUsername(), loginView.getPassword());
	}
	
	private void signIn(PasswordAuthentication credentials)
	{
		try
		{
			authenticationService.signIn(passwordAuthentication);
			applicationView.showCardComponent("MAIN");
		}
		catch(SQLTimeoutException ex)
		{
			System.out.println(ex.getMessage());
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
		finally
		{
			
		}
	}
	
	
}
