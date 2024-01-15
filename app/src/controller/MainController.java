package controller;

import java.util.Map;

import javax.swing.JPanel;

import model.AuthenticationService;
import view.ApplicationView;
import view.MainView;

public class MainController
{
	
	private MainView mainView;
	private ApplicationView applicationView;
	private AuthenticationService authenticationService;
	
	public MainController(MainView _mainView, AuthenticationService _authenticationService, ApplicationView _appApplicationView, Map<String, JPanel> children)
	{
		this.mainView = _mainView;
		this.applicationView = _appApplicationView;
		this.authenticationService = _authenticationService;
		
		this.applicationView.addCardComponent("MAIN", mainView);
		
		children.forEach((String key, JPanel jpanel) -> 
		{
			mainView.addTab(key, jpanel);	
		});
		
		mainView.setHrButtonListener(e -> 
		{
			mainView.switchTab(MainView.HR_TAB);
		});
		
		mainView.setFinancialButtonListener(e -> 
		{
			mainView.switchTab(MainView.FINANCIAL_TAB);
		});
		
		mainView.setMedicalButtonListener(e -> 
		{
			mainView.switchTab(MainView.MEDICAL_TAB);
		});
	}
}
