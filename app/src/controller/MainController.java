package controller;

import app.IController;
import view.ApplicationView;
import view.MainView;

public class MainController implements IController{
	
	private MainView mainView;
	private ApplicationView appApplicationView;
	
	public MainController(MainView _mainView, ApplicationView _appApplicationView)
	{
		this.mainView = _mainView;
		this.appApplicationView = _appApplicationView;
		
		this.appApplicationView.addCardComponent("MAIN", mainView);
		
		mainView.setHrButtonListener(e -> 
		{
			System.out.println("ceva1");
			mainView.switchTab(MainView.HR_TAB);
		});
		
		mainView.setFinancialButtonListener(e -> 
		{
			System.out.println("ceva2");
			mainView.switchTab(MainView.FINANCIAL_TAB);
		});
		
		mainView.setMedicalButtonListener(e -> 
		{
			System.out.println("ceva2");
			mainView.switchTab(MainView.MEDICAL_TAB);
		});
	}
	
	public boolean isAvailable()
	{
		return true;
	}
	
	@Override
	public void ShowComponent() {

	}

}
