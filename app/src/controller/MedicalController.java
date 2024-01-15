package controller;

import java.awt.BorderLayout;
import java.util.Map;
import javax.swing.JPanel;

import model.AuthenticationService;
import view.MainView;
import view.MedicalView;
import view.medical.reception.MedicalReceptionView;

public class MedicalController {
	
	private MainView mainView;
	private MedicalView medicalView;
	private AuthenticationService authenticationService;
	private Map<String, JPanel> permissionViews;
	
	public MedicalController(MedicalView _medicalView, AuthenticationService _authenticationService, MainView _mainView, Map<String, JPanel> _permissionViews)
	{
		this.mainView = _mainView;
		this.medicalView = _medicalView;
		this.authenticationService = _authenticationService;
		this.permissionViews = _permissionViews;
		
		authenticationService.addCallback(() -> 
		{
			loadView();
		});
	}
	
	private void loadView()
	{
		switch(authenticationService.getAccountType())
		{
			case AuthenticationService.ACC_MEDICAL_RECEPTIONIST: 
				medicalView.add(permissionViews.get("RECEPTIONIST"), BorderLayout.CENTER);
				break;
			case AuthenticationService.ACC_MEDICAL_ASSISTENT:
				medicalView.add(permissionViews.get("ASSISTENT"), BorderLayout.CENTER);
				break;
			case AuthenticationService.ACC_MEDICAL_DOCTOR:
				medicalView.add(permissionViews.get("DOCTOR"), BorderLayout.CENTER);
				break;
		}
	}
}
