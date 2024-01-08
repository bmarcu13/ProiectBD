package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import controller.LoginController;
import controller.MainController;
import controller.MedicalController;
import controller.medical.MedicalAssistentController;
import controller.medical.MedicalReceptionController;
import model.AuthenticationService;
import view.ApplicationView;
import view.FinancialView;
import view.HRManagementView;
import view.LoginView;
import view.MainView;
import view.MedicalView;
import view.medical.CreateAppointmentView;
import view.medical.MedicalAssistentHomeView;
import view.medical.MedicalAssistentInvestigationsView;
import view.medical.MedicalAssistentView;
import view.medical.MedicalDoctorView;
import view.medical.MedicalReceptionHomeView;
import view.medical.MedicalReceptionView;

public class Application {	
	private AuthenticationService authenticationService = new AuthenticationService();
	
	private ApplicationView applicationView = new ApplicationView();
	private LoginController loginController;
	private MainController mainController;
	private MedicalController medicalController;
	private MedicalReceptionController medicalReceptionHomeController;
	private MedicalAssistentController medicalAssistentController;
	
	private LoginView loginView = new LoginView();
	private MainView mainView = new MainView();
	private HRManagementView hrManagementView = new HRManagementView();
	private MedicalView medicalView = new MedicalView();
	private MedicalReceptionHomeView medicalReceptionHomeView = new MedicalReceptionHomeView();
	private FinancialView financialView = new FinancialView();
	private MedicalReceptionView medicalReceptionView = new MedicalReceptionView();
	private MedicalDoctorView medicalDoctorView = new MedicalDoctorView();
	private MedicalAssistentView medicalAssistentView = new MedicalAssistentView();
	private CreateAppointmentView createAppointmentView = new CreateAppointmentView();
	private MedicalAssistentHomeView assistentHomeView = new MedicalAssistentHomeView();
	private MedicalAssistentInvestigationsView assistentInvestigationsView = new MedicalAssistentInvestigationsView();
	
	public Application ()
	{		
		Map<String, JPanel> mainPanelChildren = new HashMap<String, JPanel>();
		mainPanelChildren.put("HR", hrManagementView);
		mainPanelChildren.put("FINANCIAL", financialView);
		mainPanelChildren.put("MEDICAL", medicalView);
		
		Map<String, JPanel> permissionViewsMedical = new HashMap<String, JPanel>();
		permissionViewsMedical.put("RECEPTIONIST", medicalReceptionView);
		permissionViewsMedical.put("ASSISTENT", medicalAssistentView);
		permissionViewsMedical.put("DOCTOR", medicalDoctorView);
		
		this.loginController = new LoginController(loginView, authenticationService, applicationView);
		this.mainController = new MainController(mainView, authenticationService, applicationView, mainPanelChildren);
		this.medicalController = new MedicalController(medicalView, authenticationService, mainView, permissionViewsMedical);
		this.medicalReceptionHomeController = new MedicalReceptionController(medicalReceptionView, medicalReceptionHomeView, createAppointmentView, authenticationService);
		this.medicalAssistentController = new MedicalAssistentController(medicalAssistentView, assistentHomeView, assistentInvestigationsView, authenticationService);
	}
	
	public void run()
	{
		
		applicationView.openWindow();
	}
}
