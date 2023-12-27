package view.medical;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MedicalReceptionHomeView extends JPanel{
	private JPanel createAppointmentButtonContainer = new JPanel();
	
	private JButton createAppointmentButton = new JButton("Creeaza programare");
	
	public MedicalReceptionHomeView()
	{
		setLayout(new BorderLayout());
		
		createAppointmentButtonContainer.add(createAppointmentButton);
		add(createAppointmentButtonContainer, BorderLayout.SOUTH);
		
		setBackground(Color.pink);
	}
	
	public void addCreateAppointmentButtonListener(ActionListener actionListener)
	{
		createAppointmentButton.addActionListener(actionListener);
	}
}
