package app;

import java.util.List;

import view.ApplicationView;

public class Application {
	
	private ApplicationView applicationView;
	private List<IController> controllers;
	
	public Application (ApplicationView _applicationView, List<IController> _controllers)
	{
		this.applicationView = _applicationView;
		this.controllers = _controllers;
	}
	
	public void run()
	{
		applicationView.openWindow();
	}
}
