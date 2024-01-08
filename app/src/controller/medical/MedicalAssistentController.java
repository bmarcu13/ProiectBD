package controller.medical;

import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;

import model.AuthenticationService;
import model.DatabaseService;
import model.Investigation;
import view.medical.MedicalAssistentHomeView;
import view.medical.MedicalAssistentInvestigationsView;
import view.medical.MedicalAssistentView;
import view.medical.resources.InvestigationSection;

public class MedicalAssistentController {
	private MedicalAssistentView medicalAssistentView;
	private MedicalAssistentHomeView medicalAssistentHomeView;
	private MedicalAssistentInvestigationsView medicalAssistentInvestigationsView;
	private AuthenticationService authenticationService;
	
	private DatabaseService databaseService = DatabaseService.getInstance();
	
	private int selectedAppointmentId = -1;
	
	private Vector<Investigation> selectableInvestigations = new Vector<>();
	
	public MedicalAssistentController(MedicalAssistentView medicalAssistentView,
			MedicalAssistentHomeView medicalAssistentHomeView,
			MedicalAssistentInvestigationsView medicalAssistentInvestigationsView,
			AuthenticationService authenticationService) {
		
		this.medicalAssistentView = medicalAssistentView;
		this.medicalAssistentHomeView = medicalAssistentHomeView;
		this.medicalAssistentInvestigationsView = medicalAssistentInvestigationsView;
		this.authenticationService = authenticationService;
		
		medicalAssistentView.addTab(MedicalAssistentView.HOME_VIEW, medicalAssistentHomeView);
		medicalAssistentView.addTab(MedicalAssistentView.INVESTIGATIONS_VIEW, medicalAssistentInvestigationsView);
		
		medicalAssistentView.switchTab(medicalAssistentView, MedicalAssistentView.HOME_VIEW);
		
		authenticationService.addCallback(() -> 
		{
			try
			{
				medicalAssistentHomeView.renderRegisteredAppointments(databaseService.getRegisteredAppointments());
			}
			catch(SQLException ex)
			{
				System.out.println(ex);
			}
		});
		
		medicalAssistentHomeView.setManageInvestigationsActionListener(e -> {
			JButton source = (JButton) e.getSource();
			selectedAppointmentId = Integer.parseInt(source.getName());
			medicalAssistentView.switchTab(medicalAssistentView, medicalAssistentView.INVESTIGATIONS_VIEW);
			try
			{				
				selectableInvestigations = databaseService.getAvailableInvestigations();
				Vector<Investigation> existingInvestigations = databaseService.getExistingInvestigations(selectedAppointmentId);
				for(Investigation i : existingInvestigations)
				{
					i.setSelectableInvestigations(selectableInvestigations);
					medicalAssistentInvestigationsView.addInvestigation(i);
				}
			}
			catch(SQLException ex)
			{
				System.out.println(ex);
			}
		});
		
		medicalAssistentInvestigationsView.setInvestigationButtonActionListener(e -> {
			medicalAssistentInvestigationsView.addInvestigation(new Investigation(selectableInvestigations));
		});
		
		medicalAssistentInvestigationsView.setBackButtonActionListener(e -> {
			medicalAssistentView.switchTab(medicalAssistentView, MedicalAssistentView.HOME_VIEW);
			medicalAssistentInvestigationsView.emptyFields();
		});
		
		medicalAssistentInvestigationsView.setFinishButtonActionListener(e -> {
			for(Investigation i : medicalAssistentInvestigationsView.getAddedInvestigations())
			{				
				try
				{
					databaseService.insertInvestigations(selectedAppointmentId, i);
				}
				catch(SQLException ex)
				{
					System.out.println(ex);
				}
			}
			
			medicalAssistentView.switchTab(medicalAssistentView, MedicalAssistentView.HOME_VIEW);
			medicalAssistentInvestigationsView.emptyFields();
		});
	}
}
