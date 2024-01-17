package view.medical.resources;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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

public class AppointmentSection extends JPanel{
	private JPanel nameTimePanel = new JPanel();
	private JLabel nameLabel = new JLabel();
	private JLabel timeLabel = new JLabel();
	private int id;
	
	private JButton button = new JButton();
	
	public AppointmentSection(int _index, String firstName, String secondName, LocalTime time, String buttonText)
	{
		button.setName(String.valueOf(_index));
		button.setText(buttonText);
		
		setBackground(Color.white);
		
		nameLabel.setText(firstName + " " + secondName);
		timeLabel.setText(time.getHour() + ":" + (time.getMinute() > 0 ? time.getMinute() : time.getMinute() + "0"));
		
		nameTimePanel.setLayout(new GridBagLayout());
		nameTimePanel.setBackground(Color.white);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
		
		int sectionWidth = 5;
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.anchor = GridBagConstraints.WEST;
		nameTimePanel.add(nameLabel, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.anchor = GridBagConstraints.WEST;
		nameTimePanel.add(timeLabel, constraints);
		
		add(nameTimePanel, BorderLayout.WEST);
		
		add(button, BorderLayout.EAST);
	}
	
	public void setRegisteredAppointment(boolean isRegistered)
	{
		if(isRegistered)
		{			
			setBackground(Color.yellow);
			nameTimePanel.setBackground(Color.yellow);
		}
		else
		{
			setBackground(Color.white);
		}
	}
	
	public void setButtonActionListener(ActionListener al)
	{
		button.addActionListener(al);
	}
}
