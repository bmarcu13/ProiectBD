package view.medical;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import model.Appointment;
import view.medical.resources.PatientRegistrationSection;

public class MedicalReceptionHomeView extends JPanel{
	private JPanel createAppointmentButtonContainer = new JPanel();
	private JPanel basePanel = new JPanel(new GridLayout(1, 2));
	private JPanel leftPanel = new JPanel();
	private JPanel rightPanel = new JPanel();
	
	private BoxLayout leftPanelBoxLayout = new BoxLayout(leftPanel, BoxLayout.Y_AXIS);
	
	private JScrollPane leftPanelScrollPane = new JScrollPane(leftPanel);
	private JScrollPane rightPanelScrollPane = new JScrollPane(rightPanel);
	
	private JButton createAppointmentButton = new JButton("Creeaza programare");
	
	public MedicalReceptionHomeView()
	{
		setLayout(new BorderLayout());
		
		createAppointmentButtonContainer.add(createAppointmentButton);
		add(createAppointmentButtonContainer, BorderLayout.SOUTH);
		
		leftPanelScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		leftPanelScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		leftPanel.setLayout(leftPanelBoxLayout);
		rightPanelScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		rightPanel.setBackground(Color.CYAN);
		basePanel.add(leftPanelScrollPane);
		basePanel.add(rightPanelScrollPane);
		add(basePanel, BorderLayout.CENTER);
	}
	
	public void renderUnregisteredAppointments(Vector<Appointment> appointments)
	{
		leftPanel.add(Box.createVerticalStrut(5));
		for(Appointment a : appointments)
		{	
			PatientRegistrationSection s  = new PatientRegistrationSection(a.getPatientFirstName(), a.getPatientSecondName(), a.getTime());
			s.setMaximumSize(new Dimension(310, 50));
			leftPanel.add(s);
			leftPanel.add(Box.createVerticalStrut(5));
		}
	}
	
	public void addCreateAppointmentButtonListener(ActionListener actionListener)
	{
		createAppointmentButton.addActionListener(actionListener);
	}
}
