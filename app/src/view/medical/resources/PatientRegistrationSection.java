package view.medical.resources;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.time.LocalTime;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PatientRegistrationSection extends JPanel{
	
	private JLabel nameameLabel = new JLabel();
	private JLabel timeLabel = new JLabel();
	private int id;
	
	private JButton registerButton = new JButton("Inregistrare");
	
	public PatientRegistrationSection(int _index, String firstName, String secondName, LocalTime time)
	{
		registerButton.setName(String.valueOf(_index));
		
		setBackground(Color.white);
		
		nameameLabel.setText(firstName + " " + secondName);
		timeLabel.setText(time.getHour() + ":" + time.getMinute());
		
		setLayout(new GridBagLayout());
		setBorder(new EmptyBorder(5, 5, 5, 5));
		
		GridBagConstraints constraints = new GridBagConstraints();
		
		int sectionWidth = 5;
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = sectionWidth;
		add(Box.createHorizontalStrut(300), constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.anchor = GridBagConstraints.WEST;
		add(nameameLabel, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.anchor = GridBagConstraints.WEST;
		add(timeLabel, constraints);
		
		constraints.gridx = 4;
		constraints.gridy = 0;
		constraints.gridheight = 3;
		constraints.insets = new Insets(0, 0, 0, 0);
		constraints.anchor = GridBagConstraints.EAST;
		add(registerButton, constraints);
	}
	
	public void setRegisterButtonActionListener(ActionListener al)
	{
		registerButton.addActionListener(al);
	}
}
