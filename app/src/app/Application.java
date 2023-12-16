package app;

import java.util.List;

import controller.LoginController;
import controller.MainController;
import model.AuthenticationService;
import view.ApplicationView;
import view.LoginView;
import view.MainView;

public class Application {	
	private AuthenticationService authenticationService = new AuthenticationService();
	
	private ApplicationView applicationView = new ApplicationView();
	private LoginController loginController;
	private MainController mainController;
	
	
	public Application (ApplicationView _applicationView)
	{
		this.applicationView = _applicationView;
		this.loginController = new LoginController(new LoginView(), authenticationService, applicationView);
		this.mainController = new MainController(new MainView(), applicationView);
	}
	
	public void run()
	{
		applicationView.openWindow();
	}
}
