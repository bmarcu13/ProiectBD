package view.medical;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.time.*;

public class CreateAppointmentView extends JPanel{
	private JPanel buttonsContainer = new JPanel();
	private JPanel buttonsWrapper = new JPanel();
	private JPanel fieldContainer = new JPanel();
	
	private JScrollPane fieldScrollPane = new JScrollPane(fieldContainer);
	
	private BoxLayout buttonsContainerLayout = new BoxLayout(buttonsContainer, BoxLayout.X_AXIS);
	private BoxLayout fieldContainerLayout = new BoxLayout(fieldContainer, BoxLayout.Y_AXIS);
	
	private JButton backButton = new JButton("Inapoi");
	private JButton submitButton = new JButton("Finalizare");
	private JButton addService = new JButton("Arata specializari");
	
	private JTextField patientNameField = new JTextField();
	private JTextField doctorNameField = new JTextField();
	private JTextField timeField = new JTextField();
	private JTextField patientCNPField = new JTextField();
	
	private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	private JFormattedTextField dateField = new JFormattedTextField(df);
	
	private JLabel patientLabel = new JLabel("Nume si prenume pacient");
	private JLabel doctorLabel = new JLabel("Nume si prenume medic");
	private JLabel dateLabel = new JLabel("Data programarii (zi/luna/an)");
	private JLabel timeLabel = new JLabel("Ora programarii (hh:mm)");
	private JLabel patientCNPLabel = new JLabel("CNP pacient");
	
	private List<Component> fieldList = new ArrayList<Component>();
	
	public CreateAppointmentView()
	{
		setLayout(new BorderLayout());
		
		fieldList.add(patientLabel);
		fieldList.add(patientNameField);
		fieldList.add(patientCNPLabel);
		fieldList.add(patientCNPField);
		fieldList.add(doctorLabel);
		fieldList.add(doctorNameField);
		fieldList.add(dateLabel);
		fieldList.add(dateField);
		fieldList.add(timeLabel);
		fieldList.add(timeField);
		fieldList.add(addService);
		
		fieldContainer.setLayout(fieldContainerLayout);
		fieldContainer.setBorder(new EmptyBorder(0, 30, 0, 0));
		
		renderFields();

		buttonsContainer.setLayout(buttonsContainerLayout);
		buttonsContainer.add(backButton);
		buttonsContainer.add(Box.createHorizontalStrut(10));
		buttonsContainer.add(submitButton);
		buttonsWrapper.add(buttonsContainer);
		
		add(fieldScrollPane);
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
	
	public void addService()
	{
		fieldList.add()
		fieldContainer.removeAll();
		renderFields();
	}
	
	private void renderFields()
	{
		for(Component c : fieldList)
		{
			JPanel elementWrapper = new JPanel();
			c.setPreferredSize(new Dimension(300, 30));
			elementWrapper.add(c);
			elementWrapper.setMaximumSize(new Dimension(300, 30));
			
			fieldContainer.add(elementWrapper);
		}
		repaint();
	}
}
