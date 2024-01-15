package view.medical.doctor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.JTextComponent;

import model.Appointment;
import model.Investigation;
import model.MedicalReport;
import view.medical.resources.AppointmentSection;
import view.medical.resources.InvestigationSection;

public class MedicalDoctorHomeView extends JPanel{
	private JPanel appointmentsPanel = new JPanel();
	private JPanel appointmentDetailsPanel = new JPanel();
	private JPanel investigationsPanel = new JPanel();
	
	private JScrollPane appointmentsPanelScrollPane = new JScrollPane(appointmentsPanel);
	private JScrollPane appointmentDetailsPanelScrollPane = new JScrollPane(appointmentDetailsPanel);
	
	private BoxLayout appointmentsPanelBoxLayout = new BoxLayout(appointmentsPanel, BoxLayout.Y_AXIS);
	private BoxLayout investigationsPanelBoxLayout = new BoxLayout(investigationsPanel, BoxLayout.Y_AXIS);
	
	private JTextField patientFirstNameField = new JTextField();
	private JTextField patientSecondNameField = new JTextField();
	private JTextField doctorFirstNameField = new JTextField();
	private JTextField doctorSecondNameField = new JTextField();
	private JTextField recommendingDoctorFirstNameField = new JTextField();
	private JTextField recommendingDoctorSecondNameField = new JTextField();
	private JTextField assistentFirstNameField = new JTextField();
	private JTextField assistentSecondNameField = new JTextField(); 
	
	private JTextArea simptomsTextArea = new JTextArea(5, 20);
	private JTextArea diagnosisTextArea = new JTextArea(5, 20);
	private JTextArea recommandationsTextArea = new JTextArea(5, 20);
	
	private JLabel patientFirstNameLabel = new JLabel("Nume pacient");
	private JLabel patientSecondNameLabel = new JLabel("Prenume pacient");
	private JLabel doctorFirstNameLabel = new JLabel("Nume medic");
	private JLabel doctorSecondNameLabel = new JLabel("Prenume medic");
	private JLabel recommendingDoctorFirstNameLabel = new JLabel("Nume medic care a facut recomandarea");
	private JLabel recommendingDoctorSecondNameLabel = new JLabel("Prenume medic care a facut recomandarea");
	private JLabel assistentFirstNameLabel = new JLabel("Nume asistent");
	private JLabel assistentSecondNameLabel = new JLabel("Prenume asistent"); 
	private JLabel simptomsLabel = new JLabel("Simptome"); 
	private JLabel diagnosisLabel = new JLabel("Diagnostic"); 	
	private JLabel recommandationsLabel = new JLabel("Recomandari"); 	
	private JLabel servicesLabel = new JLabel("Servicii medicale");
	
	private JButton addServiceButton = new JButton("Adaugare");
	private JButton parafareButton = new JButton("Parafare");
	
	private List<AppointmentSection> appointmentSectionList = new ArrayList<AppointmentSection>();
	private List<InvestigationSection> services = new ArrayList<InvestigationSection>();
	
	private int investigationLayoutY = 11;
	private int nextInvestigationId = 0;
	
	public MedicalDoctorHomeView()
	{
		setLayout(new GridLayout(1, 2));
		
		appointmentsPanel.setLayout(appointmentsPanelBoxLayout);
		
		appointmentsPanel.setBorder(new EmptyBorder(0, 5, 0, 5));
		
		appointmentsPanelScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		appointmentsPanelScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		appointmentDetailsPanelScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		appointmentDetailsPanelScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		add(appointmentsPanelScrollPane);
		add(appointmentDetailsPanelScrollPane);
		
		renderAppointmentsPanel();
		renderAppointmentDetailsPanel();
	}
	
	private void renderAppointmentsPanel()
	{	

	}
	
	private void renderAppointmentDetailsPanel()
	{
		appointmentDetailsPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
		
		int lineIndex = 0;
		int lateralBorder = 10;
		
		constraints.anchor = GridBagConstraints.NORTHWEST;
		constraints.weightx = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		
		//Nume prenume pacient
		constraints.gridy = 0;
		constraints.gridx = 0;
		constraints.insets = new Insets(2,  lateralBorder, 2, lateralBorder);
		appointmentDetailsPanel.add(patientFirstNameLabel, constraints);
		constraints.gridy = 1;
		constraints.gridx = 0;
		patientFirstNameField.setBorder(new CompoundBorder(new LineBorder(Color.gray), new EmptyBorder(5, 5, 5, 5)));
		patientFirstNameField.setEditable(false);
		appointmentDetailsPanel.add(patientFirstNameField, constraints);
		
		constraints.gridy = 0;
		constraints.gridx = 1;
		appointmentDetailsPanel.add(patientSecondNameLabel, constraints);
		constraints.gridy = 1;
		constraints.gridx = 1;
		constraints.insets = new Insets(2, lateralBorder, 15, lateralBorder);
		patientSecondNameField.setBorder(new CompoundBorder(new LineBorder(Color.gray), new EmptyBorder(5, 5, 5, 5)));
		patientSecondNameField.setEditable(false);
		appointmentDetailsPanel.add(patientSecondNameField, constraints);
		
		//Nume prenume medic
		constraints.gridy = 2;
		constraints.gridx = 0;
		constraints.insets = new Insets(2, lateralBorder, 2, lateralBorder);
		appointmentDetailsPanel.add(doctorFirstNameLabel, constraints);
		constraints.gridy = 3;
		constraints.gridx = 0;
		doctorFirstNameField.setBorder(new CompoundBorder(new LineBorder(Color.gray), new EmptyBorder(5, 5, 5, 5)));
		doctorFirstNameField.setEditable(false);
		appointmentDetailsPanel.add(doctorFirstNameField, constraints);
		
		constraints.gridy = 2;
		constraints.gridx= 1;
		appointmentDetailsPanel.add(doctorSecondNameLabel, constraints);
		constraints.gridy = 3;
		constraints.gridx = 1;
		constraints.insets = new Insets(2, lateralBorder, 15, lateralBorder);
		doctorSecondNameField.setBorder(new CompoundBorder(new LineBorder(Color.gray), new EmptyBorder(5, 5, 5, 5)));
		doctorSecondNameField.setEditable(false);
		appointmentDetailsPanel.add(doctorSecondNameField, constraints);
		
//		//Nume prenume medic recomandare
		constraints.gridy = 4;
		constraints.gridx = 0;
		constraints.insets = new Insets(2, lateralBorder, 2, lateralBorder);
		appointmentDetailsPanel.add(recommendingDoctorFirstNameLabel, constraints);
		constraints.gridy = 5;
		constraints.gridx = 0;
		recommendingDoctorFirstNameField.setBorder(new CompoundBorder(new LineBorder(Color.gray), new EmptyBorder(5, 5, 5, 5)));
		appointmentDetailsPanel.add(recommendingDoctorFirstNameField, constraints);
		
		constraints.gridy = 4;
		constraints.gridx = 1;		
		appointmentDetailsPanel.add(recommendingDoctorSecondNameLabel, constraints);
		constraints.gridy = 5;
		constraints.gridx = 1;
		constraints.insets = new Insets(2, lateralBorder, 15, lateralBorder);
		recommendingDoctorSecondNameField.setBorder(new CompoundBorder(new LineBorder(Color.gray), new EmptyBorder(5, 5, 5, 5)));
		appointmentDetailsPanel.add(recommendingDoctorSecondNameField, constraints);
		
		//Nume prenume asistent
		constraints.gridy = 6;
		constraints.gridx = 0;
		constraints.insets = new Insets(2, lateralBorder, 2, lateralBorder);
		appointmentDetailsPanel.add(assistentFirstNameLabel, constraints);
		constraints.gridy = 7;
		constraints.gridx = 0;
		assistentFirstNameField.setBorder(new CompoundBorder(new LineBorder(Color.gray), new EmptyBorder(5, 5, 5, 5)));
		appointmentDetailsPanel.add(assistentFirstNameField, constraints);
		
		constraints.gridy = 6;
		constraints.gridx = 1;
		appointmentDetailsPanel.add(assistentSecondNameLabel, constraints); 
		constraints.gridy = 7;
		constraints.gridx = 1;
		constraints.insets = new Insets(2, lateralBorder, 15, lateralBorder);
		assistentSecondNameField.setBorder(new CompoundBorder(new LineBorder(Color.gray), new EmptyBorder(5, 5, 5, 5)));
		appointmentDetailsPanel.add(assistentSecondNameField, constraints); 
	
		//Istoric
		constraints.gridy = 8;
		constraints.gridx = 0;
		constraints.insets = new Insets(2, lateralBorder, 2, lateralBorder);
		appointmentDetailsPanel.add(simptomsLabel, constraints);
		
		constraints.gridy = 9;
		constraints.gridx = 0;
		constraints.gridwidth = 2;
		simptomsTextArea.setLineWrap(true);
		simptomsTextArea.setBorder(new CompoundBorder(new LineBorder(Color.gray), new EmptyBorder(5, 5, 5, 5)));
		appointmentDetailsPanel.add(simptomsTextArea, constraints);
		
		constraints.gridy = 10;
		constraints.gridx = 0;
		constraints.gridwidth = 2;
		investigationsPanel.setLayout(investigationsPanelBoxLayout);
		appointmentDetailsPanel.add(investigationsPanel, constraints);
		
		constraints.gridy = 11;
		appointmentDetailsPanel.add(servicesLabel, constraints);
		
		constraints.gridy = 12;
		appointmentDetailsPanel.add(investigationsPanel, constraints);
		
		constraints.gridy = 13;
		appointmentDetailsPanel.add(addServiceButton, constraints);
		
		//Diagnostic
		constraints.gridy = 14;
		constraints.gridx = 0;
		constraints.gridwidth = 1;
		constraints.insets = new Insets(2, lateralBorder, 2, lateralBorder);
		appointmentDetailsPanel.add(diagnosisLabel, constraints);
		
		constraints.gridy = 15;
		constraints.gridx = 0;
		constraints.gridwidth = 2;
		diagnosisTextArea.setLineWrap(true);
		diagnosisTextArea.setBorder(new CompoundBorder(new LineBorder(Color.gray), new EmptyBorder(5, 5, 5, 5)));
		appointmentDetailsPanel.add(diagnosisTextArea, constraints);
		
		//Recomandari
		constraints.gridy = 16;
		constraints.gridx = 0;
		constraints.gridwidth = 1;
		constraints.insets = new Insets(2, lateralBorder, 2, lateralBorder);
		appointmentDetailsPanel.add(recommandationsLabel, constraints);
		
		constraints.gridy = 17;
		constraints.gridx = 0;
		constraints.gridwidth = 2;
		recommandationsTextArea.setLineWrap(true);
		recommandationsTextArea.setBorder(new CompoundBorder(new LineBorder(Color.gray), new EmptyBorder(5, 5, 5, 5)));
		appointmentDetailsPanel.add(recommandationsTextArea, constraints);
		
		constraints.gridy = 18;
		constraints.gridx = 0;
		appointmentDetailsPanel.add(parafareButton, constraints);
	}
	
	public void addAppointment(Appointment a, ActionListener al)
	{
		AppointmentSection as = new AppointmentSection(a.getId(), a.getPatientFirstName(), a.getPatientSecondName(), a.getTime(), "Vizualizare");
		as.setRegisteredAppointment(a.getIsRegistered());
		as.setButtonActionListener(al);
		as.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		
		appointmentSectionList.add(as);
		
		renderAppointments();
	}
	
	public void removeAppointment(int index)
	{
		System.out.println(appointmentSectionList);
		appointmentSectionList.remove(index);
		System.out.println(appointmentSectionList);
		renderAppointments();
	}
	
	private void renderAppointments()
	{
		appointmentsPanel.removeAll();
		appointmentsPanel.add(Box.createVerticalStrut(5));
		
		for(AppointmentSection as : appointmentSectionList)
		{
			appointmentsPanel.add(as);
			appointmentsPanel.add(Box.createVerticalStrut(5));
		}
		
		appointmentsPanel.revalidate();
		appointmentsPanel.repaint();
		
		revalidate();
		repaint();
	}

	public void addService(Investigation i, ActionListener al)
	{		
		InvestigationSection is = new InvestigationSection(i);
		is.setId(nextInvestigationId++);
		is.setDeleteButtonActionListener(al);
		
		services.add(is);
		renderServices();
	}
	
	private void renderServices()
	{
		investigationsPanel.removeAll();
		investigationsPanel.add(Box.createVerticalStrut(10));
		
		for(InvestigationSection is : services)
		{
			investigationsPanel.add(is);
			investigationsPanel.add(Box.createVerticalStrut(10));
		}
		
		investigationsPanel.revalidate();
		investigationsPanel.repaint();
	}
	
	public void deleteInvestigation(int id)
	{
		Iterator<InvestigationSection> iterator = services.iterator();
		while (iterator.hasNext()) {
		    InvestigationSection is = iterator.next();
		    if (is.getName().equals(String.valueOf(id))) {
		        iterator.remove();
		    }
		}

		renderServices();
	}
	
	public void setPatientName(String firstName, String secondName)
	{
		patientFirstNameField.setText(firstName);
		patientSecondNameField.setText(secondName);
	}
	
	public void setDoctorName(String firstName, String secondName)
	{
		doctorFirstNameField.setText(firstName);
		doctorSecondNameField.setText(secondName);
	}
	
	public void setAddServiceActionListener(ActionListener al)
	{
		addServiceButton.addActionListener(al);
	}
	
	public void setParafareButtonActionListener(ActionListener al)
	{
		parafareButton.addActionListener(al);
	}
	
	public void openMedicalReport()
	{
		appointmentDetailsPanel.setVisible(true);
	}
	
	public MedicalReport closeMedicalReport()
	{
		MedicalReport medicalReport = new MedicalReport();
		medicalReport.setPatientFirstName(patientFirstNameField.getText());
		medicalReport.setPatientSecondName(patientSecondNameField.getText());
		
		medicalReport.setDoctorFirstName(doctorFirstNameField.getText());
		medicalReport.setDoctorSecondName(doctorSecondNameField.getText());
		
		medicalReport.setRecommendingDoctorFirstName(recommendingDoctorFirstNameField.getText());
		medicalReport.setRecommendingDoctorSecondName(recommendingDoctorSecondNameField.getText());
		
		medicalReport.setAssistentFirstName(assistentFirstNameField.getText());
		medicalReport.setAssistentSecondName(assistentSecondNameField.getText());
		
		medicalReport.setSimptoms(simptomsTextArea.getText());
		medicalReport.setDiagnosis(diagnosisTextArea.getText());
		medicalReport.setRecommendations(recommandationsTextArea.getText());
		
		Vector<Investigation> finalServices = new Vector<Investigation>();
		for(InvestigationSection is : services)
		{
			Investigation i = is.getSelectedItem();
			i.setRawValue(is.getRawValue());
			finalServices.add(i);
		}
		
		medicalReport.setServices(finalServices);
		
		resetFields();
		
		return medicalReport;
	}
	
	public void resetFields()
	{
		appointmentDetailsPanel.setVisible(false);
		for(Component c : appointmentDetailsPanel.getComponents())
		{
			if(c instanceof JTextComponent)
			{
				((JTextComponent) c).setText("");
			}
		}
		services.clear();
		nextInvestigationId = 0;
		
		revalidate();
		repaint();
	}
}
