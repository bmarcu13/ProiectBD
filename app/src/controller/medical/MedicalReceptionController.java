package controller.medical;

import java.awt.DisplayMode;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;

import model.AuthenticationService;
import model.DatabaseService;
import model.Doctor;
import model.MedicalService;
import view.medical.reception.CreateAppointmentView;
import view.medical.reception.MedicalReceptionHomeView;
import view.medical.reception.MedicalReceptionView;
import view.medical.resources.AppointmentSection;

public class MedicalReceptionController {
	private MedicalReceptionView receptionView;
	private MedicalReceptionHomeView homeView;
	private CreateAppointmentView appointmentView;
	
	private DatabaseService databaseService = DatabaseService.getInstance();
	private AuthenticationService authenticationService;
	
	private Vector<Doctor> doctors;
	
	public MedicalReceptionController(MedicalReceptionView _medicalReceptionView, 
			MedicalReceptionHomeView _medicalReceptionHomeView, 
			CreateAppointmentView _createAppointmentView,
			AuthenticationService _authenticationService)
	{
		this.receptionView = _medicalReceptionView;
		this.homeView = _medicalReceptionHomeView;
		this.appointmentView = _createAppointmentView;
		this.authenticationService = _authenticationService;
		
		receptionView.addTab(MedicalReceptionView.HOME_VIEW, homeView);
		receptionView.addTab(MedicalReceptionView.APPOINTMENT_VIEW, appointmentView);
		
		receptionView.switchTab(receptionView, MedicalReceptionView.HOME_VIEW);
		
		handleHomeView();
		
		handleAppointmetView();
		
	}
	
	private void handleHomeView()
	{
		homeView.setRegisterButtonActionListener(e ->
		{
			JButton source = (JButton) e.getSource();
			try
			{
				databaseService.registerPatientForAppointment(Integer.valueOf(source.getName()));
				homeView.renderUnregisteredAppointments(databaseService.getUnregisteredAppointments());
			}
			catch (SQLException ex) 
			{
				System.out.println(ex);
			}
		});
		
		authenticationService.addCallback(() -> 
		{				
			try {
				homeView.renderUnregisteredAppointments(databaseService.getUnregisteredAppointments());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});

		homeView.addCreateAppointmentButtonListener(e -> {
			receptionView.switchTab(receptionView, MedicalReceptionView.APPOINTMENT_VIEW);
			
			if(doctors == null || doctors.size() == 0)
			{
				try
				{
					doctors = databaseService.getDoctorList();	
				}
				catch(SQLException ex)
				{
					System.out.println(ex);
				}
				
				appointmentView.setDoctorComboBoxItems(doctors);
			}
		});
	}
	
	private void handleAppointmetView()
	{
		appointmentView.setComboBoxListener((e) ->
		{
			int selectedIndex = appointmentView.getSelectedDoctorIndex();
			if(doctors.elementAt(selectedIndex).getServices() == null || doctors.elementAt(selectedIndex).getServices().isEmpty())
			{	
				try {
					doctors.elementAt(selectedIndex).setServices(databaseService.getMedicalServicesForMedic(doctors.elementAt(selectedIndex).getCNP()));
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			
			appointmentView.updateServices(doctors.elementAt(selectedIndex).getServices());
		});
		
		appointmentView.addBackButtonListener(e -> 
		{
			receptionView.switchTab(receptionView, MedicalReceptionView.HOME_VIEW);
		});
		
		appointmentView.addSubmitButtonListener(e -> 
		{
			String patientFirstName = appointmentView.getPatientFirstName();
			String patientSecondName = appointmentView.getPatientSecondName();
			String patientCNP = appointmentView.getPatientCNP();
			Doctor doctor = doctors.get(appointmentView.getSelectedDoctorIndex());
			LocalDate date;
			LocalTime time;
			List<MedicalService> services = appointmentView.getSelectedServices();
			
			try
			{
				date = appointmentView.getDate();
			}
			catch(Exception ex)
			{
				appointmentView.displayError("Formatul datei e invalid.");
				System.out.println(ex);
				return;
			}
			
			try
			{
				time = appointmentView.getTime();
			}
			catch(Exception ex)
			{
				appointmentView.displayError("Formatul orei e invalid.");
				return;
			}
			
			if(patientFirstName.isEmpty())
			{
				appointmentView.displayError("Campul pentru numele pacientului este gol.");
				return;
			}
			if(patientSecondName.isEmpty())
			{
				appointmentView.displayError("Campul pentru prenumele pacientului este gol.");
				return;
			}
			if(patientCNP.isEmpty())
			{
				appointmentView.displayError("Campul pentru CNP-ul pacientului este gol.");
				return;
			}
			if(patientCNP.length() > 13)
			{
				appointmentView.displayError("CNP invalid");
				return;
			}
			if(date.isBefore(LocalDate.now()) || (date.isEqual(LocalDate.now()) && time.isBefore(LocalTime.now())))
			{
				appointmentView.displayError("Se pot efectua programari doar pentru date ulterioare");
				return;
			}
			if(services == null)
			{
				appointmentView.displayError("Selectati minim un serviciu medical");
				return;
			}
			
			LocalTime duration = LocalTime.parse("00:00:00");
			
			for(MedicalService ms : services)
			{
				duration = duration
					.plusHours(ms.getDuration().getHour())
					.plusMinutes(ms.getDuration().getMinute())
					.plusSeconds(ms.getDuration().getSecond());
			}
			
			LocalTime finalTime = time.plusHours(duration.getHour()).plusMinutes(duration.getMinute()).plusSeconds(duration.getSecond());
			
			if(finalTime.getHour() < time.getHour())
			{
				appointmentView.displayError("Durata programarii este prea mare");
				return;
			}
			
			int appointmentId = -1;
			
			try
			{
				appointmentId = databaseService.createAppointment(patientCNP, patientFirstName, patientSecondName, doctor.getCNP(), date, time, duration);
				if (appointmentId < 1)
				{
					appointmentView.displayError("Programarea nu a putut fi efectuata.");
					return;
				}
				for(MedicalService ms : services)
				{
					databaseService.createServiceAppointment(appointmentId, ms.getId());
				}
			}
			catch (SQLException ex)
			{
				System.out.println(ex);
			}
			
			appointmentView.hideMessage();
			appointmentView.displaySuccessMessage();
			appointmentView.clearFields();
			
			try
			{
				homeView.renderUnregisteredAppointments(databaseService.getUnregisteredAppointments());
			}
			catch(Exception ex)
			{
				System.out.println(ex);
			}
		});
	}
}
