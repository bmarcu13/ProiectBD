package app;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import controller.financial.EveryoneController;
import controller.financial.FinancialController;
import controller.LoginController;
import controller.MainController;
import controller.MedicalController;
import controller.medical.MedicalAssistentController;
import controller.medical.MedicalDoctorController;
import controller.medical.MedicalReceptionController;
import model.AuthenticationService;
import view.ApplicationView;
import view.financial.EveryoneView;
import view.financial.FinancialView;
import view.HRManagementView;
import view.LoginView;
import view.MainView;
import view.MedicalView;
import view.medical.assistent.MedicalAssistentHomeView;
import view.medical.assistent.MedicalAssistentInvestigationsView;
import view.medical.assistent.MedicalAssistentView;
import view.medical.doctor.MedicalDoctorHomeView;
import view.medical.doctor.MedicalDoctorView;
import view.medical.reception.CreateAppointmentView;
import view.medical.reception.MedicalReceptionHomeView;
import view.medical.reception.MedicalReceptionView;

public class Application {	
	private AuthenticationService authenticationService = new AuthenticationService();
	
	private ApplicationView applicationView = new ApplicationView();
	private LoginController loginController;
	private MainController mainController;
	private MedicalController medicalController;
	private FinancialController financialController;
  
	private MedicalReceptionController medicalReceptionHomeController;
	private MedicalAssistentController medicalAssistentController;
	private MedicalDoctorController medicalDoctorController;

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

	private EveryoneView everyoneView = new EveryoneView();
  
	private MedicalAssistentHomeView medicalAssistentHomeView = new MedicalAssistentHomeView();
	private MedicalAssistentInvestigationsView medicalAssistentInvestigationsView = new MedicalAssistentInvestigationsView();
	private MedicalDoctorHomeView medicalDoctorHomeView = new MedicalDoctorHomeView();
	
	public Application ()
	{		
		Map<String, JPanel> mainPanelChildren = new HashMap<String, JPanel>();
		mainPanelChildren.put("HR", hrManagementView);
		mainPanelChildren.put("FINANCIAL", financialView);
		mainPanelChildren.put("MEDICAL", medicalView);
		
		Map<String, JPanel> permissionViewsMedical = new HashMap<String, JPanel>();
		permissionViewsMedical.put("RECEPTIONIST", medicalReceptionView);
		permissionViewsMedical.put("ASSISTENT", medicalAssistentView);
		permissionViewsMedical.put("DOCTOR", medicalDoctorHomeView);
		
		this.loginController = new LoginController(loginView, authenticationService, applicationView);
		this.mainController = new MainController(mainView, authenticationService, applicationView, mainPanelChildren);
		this.medicalController = new MedicalController(medicalView, authenticationService, mainView, permissionViewsMedical);
		this.medicalReceptionHomeController = new MedicalReceptionController(medicalReceptionView, medicalReceptionHomeView, createAppointmentView, authenticationService);
		this.medicalAssistentController = new MedicalAssistentController(medicalAssistentView, medicalAssistentHomeView, medicalAssistentInvestigationsView, authenticationService);
		this.medicalDoctorController = new MedicalDoctorController(medicalDoctorHomeView, authenticationService);
    
		this.financialController = new FinancialController(this.financialView, this.authenticationService);
	}
	
	public void run()
	{
		
		applicationView.openWindow();
	}
}
