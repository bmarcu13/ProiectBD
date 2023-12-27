package controller.medical;

import view.medical.CreateAppointmentView;
import view.medical.MedicalReceptionHomeView;
import view.medical.MedicalReceptionView;

public class MedicalReceptionController {
	private MedicalReceptionView receptionView;
	private MedicalReceptionHomeView homeView;
	private CreateAppointmentView appointmentView;
	
	public MedicalReceptionController(MedicalReceptionView _medicalReceptionView, 
			MedicalReceptionHomeView _medicalReceptionHomeView, 
			CreateAppointmentView _createAppointmentView)
	{
		this.receptionView = _medicalReceptionView;
		this.homeView = _medicalReceptionHomeView;
		this.appointmentView = _createAppointmentView;
		
		receptionView.addTab(MedicalReceptionView.HOME_VIEW, homeView);
		receptionView.addTab(MedicalReceptionView.APPOINTMENT_VIEW, appointmentView);
		
		receptionView.switchTab(receptionView, MedicalReceptionView.HOME_VIEW);
		
		homeView.addCreateAppointmentButtonListener(e -> {
			receptionView.switchTab(receptionView, MedicalReceptionView.APPOINTMENT_VIEW);
		});
		
		appointmentView.addBackButtonListener(e -> 
		{
			receptionView.switchTab(receptionView, MedicalReceptionView.HOME_VIEW);
		});
	}
	
	
}
