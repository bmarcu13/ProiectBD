package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;

import exception.IncorrectCredentialsException;
import model.AuthenticationService;
import model.Credentials;
import view.ApplicationView;
import view.LoginView;

public class LoginController
{
	private LoginView loginView;
	private AuthenticationService authenticationService;
	private ApplicationView applicationView;
	
	public LoginController(LoginView _loginView, AuthenticationService _auAuthenticationService, ApplicationView _aplicationView)
	{
		this.loginView= _loginView;
		this.authenticationService = _auAuthenticationService;
		this.applicationView = _aplicationView;
		
		applicationView.addCardComponent("LOGIN", _loginView);
		
		loginView.setLoginButtonActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				signIn(collectCredentials());
			}
		});
	}
	
	private Credentials collectCredentials()
	{
		return new Credentials(loginView.getUsername(), loginView.getPassword());
	}
	
	private void signIn(Credentials credentials)
	{
		try
		{
			authenticationService.signIn(credentials);
			applicationView.showCardComponent("MAIN");
		}
		catch(SQLException ex)
		{
			System.out.println(ex.toString());
			loginView.setErrorMessage("Internal error.");
			loginView.showError();
		}
		catch(IncorrectCredentialsException ex)
		{
			loginView.setErrorMessage("Incorrect credentials.");
			loginView.showError();
			System.out.println(ex);
		}
	}
	
	
}
