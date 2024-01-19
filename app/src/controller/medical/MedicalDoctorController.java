package controller.medical;

import java.awt.Component;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import model.Appointment;
import model.AuthenticationService;
import model.DatabaseService;
import model.Investigation;
import model.MedicalReport;
import view.medical.doctor.MedicalDoctorHomeView;
import view.medical.resources.InvestigationSection;

public class MedicalDoctorController 
{
	private MedicalDoctorHomeView medicalDoctorHomeView;
	private AuthenticationService authenticationService;
	
	private DatabaseService databaseService = DatabaseService.getInstance();
	
	private Vector<Appointment> appointmentList;
	private Vector<Investigation> selectableServices;
	
	private int selectedAppointmentId = -1;
	
	public MedicalDoctorController(MedicalDoctorHomeView _medicalDoctorHomeView, AuthenticationService _authenticationService)
	{
		this.medicalDoctorHomeView = _medicalDoctorHomeView;
		this.authenticationService = _authenticationService;
		
		medicalDoctorHomeView.resetFields();
		
		authenticationService.addCallback(() -> 
		{			
			try
			{
				String doctorCNP = databaseService.getUserCNP(authenticationService.getUsername());
				appointmentList = databaseService.getAppointments(doctorCNP, LocalDate.now());
				selectableServices = databaseService.getSelectableServices(doctorCNP);
				
				for (Appointment a : appointmentList)
				{
					medicalDoctorHomeView.addAppointment(a, e -> 
					{
						JButton source = (JButton) e.getSource();
						medicalDoctorHomeView.resetFields();
						selectedAppointmentId = Integer.parseInt(source.getName());
						try
						{							
							Vector<Investigation> existingServices = databaseService.getExistingServices(selectedAppointmentId);
							medicalDoctorHomeView.setPatientName(a.getPatientFirstName(), a.getPatientSecondName());
							String[] nameParts = databaseService.getDoctorName(doctorCNP).split("\\s+");
							medicalDoctorHomeView.setDoctorName(nameParts[0], nameParts[1]);
							if(selectableServices.size() == 0)
							{
								return;
							}
							for(Investigation i : existingServices)
							{
								i.setSelectableInvestigations(selectableServices);
								medicalDoctorHomeView.addService(i, ev -> 
								{
									Component s = (Component) ev.getSource();
									medicalDoctorHomeView.deleteInvestigation(Integer.valueOf(s.getName()));
								});
							}
							
							String patientCNP = databaseService.getPatientCNP(selectedAppointmentId);
							medicalDoctorHomeView.renderMedicalReports(databaseService.getMedicalReports(patientCNP));
							
							medicalDoctorHomeView.openMedicalReport();
						}
						catch(SQLException ex)
						{
							ex.printStackTrace();
						}
						
					});
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		});
		
		medicalDoctorHomeView.setAddServiceActionListener(e -> 
		{
			medicalDoctorHomeView.addService(new Investigation(selectableServices), ev -> 
			{
				Component s = (Component) ev.getSource();
				medicalDoctorHomeView.deleteInvestigation(Integer.valueOf(s.getName()));
			});
		});
		
		medicalDoctorHomeView.setParafareButtonActionListener(e ->
		{
			MedicalReport medicalReport = medicalDoctorHomeView.closeMedicalReport();
			medicalReport.setAppointmentId(selectedAppointmentId);
			try
			{				
				databaseService.insertMedicalReport(medicalReport);
				int deleteIndex = -1;
				for(int i = 0; i < appointmentList.size(); i++)
				{
					if(appointmentList.elementAt(i).getId() == selectedAppointmentId)
					{
						deleteIndex = i;
					}
				}
				if(deleteIndex >= 0)
				{
					System.out.println(deleteIndex);
					appointmentList.remove(deleteIndex);
					medicalDoctorHomeView.removeAppointment(deleteIndex);
				}
			}
			catch(SQLException ex)
			{
				ex.printStackTrace();
			}
		});
	}
}
