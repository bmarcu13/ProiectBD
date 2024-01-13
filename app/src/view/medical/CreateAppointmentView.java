package view.medical;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.Console;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import model.Doctor;
import model.MedicalService;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class CreateAppointmentView extends JPanel{
	private JPanel buttonsContainer = new JPanel();
	private JPanel buttonsWrapper = new JPanel();
	private JPanel fieldContainer = new JPanel(new GridBagLayout());
	
	private JPanel servicesContainer = new JPanel();
	
	private BoxLayout buttonsContainerLayout = new BoxLayout(buttonsContainer, BoxLayout.X_AXIS);
	private BoxLayout servicesContainerLayout = new BoxLayout(servicesContainer, BoxLayout.Y_AXIS);
	
	private JButton backButton = new JButton("Inapoi");
	private JButton submitButton = new JButton("Finalizare");
	
	private JTextField patientFirstNameField = new JTextField();
	private JTextField patientSecondNameField = new JTextField();
	private JTextField timeField = new JTextField();
	private JTextField patientCNPField = new JTextField();
	
	private JComboBox<Doctor> doctorsComboBox = new JComboBox<Doctor>();
	private DefaultComboBoxModel<Doctor> doctorComboBoxModel;
	
	private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	private JFormattedTextField dateField = new JFormattedTextField(df);
	
	private JLabel patientFirstNameLabel = new JLabel("Prenume pacient");
	private JLabel patientSecondNameLabel = new JLabel("Nume pacient");
	private JLabel doctorLabel = new JLabel("Medic");
	private JLabel dateLabel = new JLabel("Data (zi/luna/an)");
	private JLabel timeLabel = new JLabel("Ora (hh:mm)");
	private JLabel patientCNPLabel = new JLabel("CNP pacient");
	private JLabel messageLabel = new JLabel();
	
	private List<Component> fieldList = new ArrayList<Component>();
	private List<JCheckBox> availableServicesCheckBoxes;
	
	public CreateAppointmentView()
	{
		setLayout(new BorderLayout());
		servicesContainer.setLayout(servicesContainerLayout);
		
		renderFields();
		
		add(fieldContainer, BorderLayout.CENTER);
		
		buttonsContainer.setLayout(buttonsContainerLayout);
		buttonsContainer.add(backButton);
		buttonsContainer.add(Box.createHorizontalStrut(10));
		buttonsContainer.add(submitButton);
		buttonsWrapper.add(buttonsContainer);
		add(buttonsWrapper, BorderLayout.SOUTH);
		
		setBackground(Color.black);
	}
	
	public void addBackButtonListener(ActionListener actionListener)
	{
		backButton.addActionListener(actionListener);
	}
	
	public void addSubmitButtonListener(ActionListener actionListener)
	{
		submitButton.addActionListener(actionListener);
	}
	
	public void setDoctorComboBoxItems(Vector<Doctor> doctors)
	{
		doctorComboBoxModel = new DefaultComboBoxModel<Doctor>(doctors);
		doctorsComboBox.setModel(doctorComboBoxModel);
	}
	
	public void setComboBoxListener(ActionListener al)
	{
		doctorsComboBox.addActionListener(al);
	}
	
	public void updateServices(Vector<MedicalService> medicalServices)
	{
		servicesContainer.removeAll();
		availableServicesCheckBoxes = new ArrayList<JCheckBox>();
		
		int maxWidth = 300;
		JPanel linePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		servicesContainer.add(linePanel);
		
		for(MedicalService m : medicalServices)
		{
			JCheckBox checkbox = new JCheckBox(m.toString());
			availableServicesCheckBoxes.add(checkbox);
			
			linePanel.add(checkbox);
			if(linePanel.getPreferredSize().getWidth() > maxWidth)
			{
				linePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
				linePanel.add(checkbox);
				servicesContainer.add(linePanel);
			}
		}
		servicesContainer.revalidate();
		servicesContainer.repaint();
	}
	
	public List<MedicalService> getSelectedServices()
	{
		List<MedicalService> selectedServices = new ArrayList<MedicalService>();
		
		for(JCheckBox c : availableServicesCheckBoxes)
		{
			if (c.isSelected())
			{
				Doctor selectedDoctor = (Doctor) doctorsComboBox.getSelectedItem();
				for(MedicalService ms : selectedDoctor.getServices())
				{
					if(ms.getName().equals(c.getText()))
					{
						selectedServices.add(ms);
					}
				}
			}
		}
		
		return selectedServices;
	}
	
	public int getSelectedDoctorIndex()
	{
		return doctorsComboBox.getSelectedIndex();
	}
	
	public void clearFields()
	{
		patientSecondNameField.setText("");
		patientFirstNameField.setText("");
		patientCNPField.setText("");
		timeField.setText("");
		dateField.setText("");
	}
	
	public LocalDate getDate()
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
		return LocalDate.parse(dateField.getText(), formatter);
	}
	
	public LocalTime getTime()
	{
		return LocalTime.parse(timeField.getText());
	}
	
	public String getPatientFirstName()
	{
		return patientFirstNameField.getText();
	}
	
	public String getPatientSecondName()
	{
		return patientSecondNameField.getText();
	}
	
	public String getPatientCNP()
	{
		return patientCNPField.getText();
	}

	public void displayError(String message)
	{
		messageLabel.setForeground(Color.red);
		messageLabel.setText(message);
		messageLabel.setVisible(true);
	}
	
	public void displaySuccessMessage()
	{
		messageLabel.setForeground(Color.green);
		messageLabel.setText("Programare efectuata cu succes.");
		messageLabel.setVisible(true);
	}
	
	public void hideMessage()
	{
		messageLabel.setVisible(false);
	}
	
	private void renderFields()
	{
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(0, 0, 10, 0);
		
		int rowIndex = 0;
		
//		Message
		constraints.gridx = 0;
		constraints.gridy = rowIndex++;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridwidth = 2;
		messageLabel.setVisible(false);
//		errorLabel.setSize(new Dimension(300, 20));
		fieldContainer.add(messageLabel, constraints);
		
//		Patient name field
		constraints.gridx = 0;
		constraints.gridy = rowIndex++;
//		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridwidth = 1;
		fieldContainer.add(patientSecondNameLabel, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = rowIndex++;
//		constraints.anchor = GridBagConstraints.CENTER;
		constraints.gridwidth = 2;
		patientSecondNameField.setPreferredSize(new Dimension(300, 30));
		fieldContainer.add(patientSecondNameField, constraints);
		
		
		constraints.gridx = 0;
		constraints.gridy = rowIndex++;
//		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridwidth = 1;
		fieldContainer.add(patientFirstNameLabel, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = rowIndex++;
//		constraints.anchor = GridBagConstraints.CENTER;
		constraints.gridwidth = 2;
		patientFirstNameField.setPreferredSize(new Dimension(300, 30));
		fieldContainer.add(patientFirstNameField, constraints);
		
//		Patient CNP field
		constraints.gridx = 0;
		constraints.gridy = rowIndex++;
//		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridwidth = 1;
		fieldContainer.add(patientCNPLabel, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = rowIndex++;
//		constraints.anchor = GridBagConstraints.CENTER;
		patientCNPField.setPreferredSize(new Dimension(300, 30));
		constraints.gridwidth = 2;
		fieldContainer.add(patientCNPField, constraints);
		
//		Doctor dropdown
		constraints.gridx = 0;
		constraints.gridy = rowIndex++;
//		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridwidth = 1;
		fieldContainer.add(doctorLabel, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = rowIndex++;
		constraints.gridwidth = 2;
		doctorsComboBox.setPreferredSize(new Dimension(300, 30));
		fieldContainer.add(doctorsComboBox, constraints);
		
//		Date and time fields
		constraints.gridwidth = 1;
		
		constraints.gridx = 0;
		constraints.gridy = rowIndex;
//		constraints.anchor = GridBagConstraints.WEST;
		fieldContainer.add(dateLabel, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = rowIndex++;
		fieldContainer.add(timeLabel, constraints);
		
		constraints.insets = new Insets(0, 0, 10, 10);
		constraints.gridx = 0;
		constraints.gridy = rowIndex;
//		constraints.anchor = GridBagConstraints.CENTER;
		dateField.setPreferredSize(new Dimension(145, 30));
		fieldContainer.add(dateField, constraints);
		
		constraints.insets = new Insets(0, 0, 10, 0);
		constraints.gridx = 1;
		constraints.gridy = rowIndex++;
		timeField.setPreferredSize(new Dimension(145, 30));
		fieldContainer.add(timeField, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = rowIndex++;
		constraints.gridwidth = 2;
//		constraints.anchor = GridBagConstraints.WEST;
		fieldContainer.add(servicesContainer, constraints);
	}
}
