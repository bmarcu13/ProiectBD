package app;

import java.util.ArrayList;
import java.util.List;

import controller.LoginController;
import controller.MainController;
import model.AuthenticationService;
import view.*;

public class Bootstrapper {
	public void run()
	{
		Application application = buildApplication();
		application.run();
	}
	
	public Application buildApplication()
	{
		ApplicationView applicationView = new ApplicationView();
		LoginView loginView = new LoginView();
		MainView mainView = new MainView();
		
		AuthenticationService authenticationService = new AuthenticationService();
		
		List<IController> controllers = new ArrayList<IController>();
		
		controllers.add(new LoginController(loginView, authenticationService, applicationView));
		controllers.add(new MainController(mainView, applicationView));
		
//		applicationView.addCardComponent("LOGIN_PAGE", loginView);
		
		return new Application(applicationView, controllers);
	}
}
