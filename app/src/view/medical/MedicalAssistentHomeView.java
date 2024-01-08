package view.medical;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Appointment;
import view.medical.resources.AppointmentSection;
import view.medical.resources.InvestigationSection;

public class MedicalAssistentHomeView extends JPanel{
	
	private JPanel leftPanel = new JPanel();
	private JScrollPane leftPanelScrollPane = new JScrollPane(leftPanel);
	private BoxLayout leftPanelBoxLayout = new BoxLayout(leftPanel, BoxLayout.Y_AXIS);
	
	private ActionListener manageInvestigationsActionListener;
	
	public MedicalAssistentHomeView() {
		leftPanelScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		leftPanelScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		leftPanel.setLayout(leftPanelBoxLayout);
		add(leftPanel);
	}
	
	public void renderRegisteredAppointments(Vector<Appointment> appointments)
	{
		leftPanel.removeAll();
		leftPanel.add(Box.createVerticalStrut(5));
		int index = 0;
		for(Appointment a : appointments)
		{	
			AppointmentSection s  = new AppointmentSection(a.getId(), a.getPatientFirstName(), a.getPatientSecondName(), a.getTime(), "Analize medicale");
			s.setMaximumSize(new Dimension(310, 50));
			if(manageInvestigationsActionListener != null)			
				s.setRegisterButtonActionListener(manageInvestigationsActionListener);
			leftPanel.add(s);
			leftPanel.add(Box.createVerticalStrut(5));
		}
		leftPanel.revalidate();
		leftPanel.repaint();
	}
	
	public void setManageInvestigationsActionListener(ActionListener al)
	{
		manageInvestigationsActionListener = al;
	}
}
