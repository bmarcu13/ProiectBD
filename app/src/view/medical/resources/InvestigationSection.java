package view.medical.resources;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import model.Investigation;

public class InvestigationSection extends JPanel{
	private JComboBox<Investigation> investigationComboBox = new JComboBox<>();
	
	private JTextField valueField = new JTextField();
	
	private JLabel investigationLabel = new JLabel("Analiza");
	private JLabel valueLabel = new JLabel("Valoare");
	
	public InvestigationSection(Investigation investigation)
	{	
		setBackground(Color.white);
		if(investigation.getValue() != -1)
		{
			valueField.setText(String.valueOf(investigation.getValue()));
		}
		
		investigationComboBox.setModel(new DefaultComboBoxModel<Investigation>(investigation.getSelectableInvestigations()));
		
		if(investigation.getName() != null)
		{
			investigationComboBox.setSelectedIndex(investigation.getId() - 1); //db ids are 1 indexed and combobox ids are 0 index
			investigationComboBox.setEnabled(false);
		}
		
		investigationComboBox.setMaximumSize(new Dimension(300, 30));
		add(investigationComboBox);
		
		add(Box.createHorizontalStrut(100));
		
		add(valueLabel);
		valueField.setPreferredSize(new Dimension(100, 30));
		add(valueField);
		
		setMaximumSize(new Dimension(450, 40));
	}
	
	public void setInvestigationComboBoxActionListener(ActionListener al)
	{
		investigationComboBox.addActionListener(al);
	}
	
	public int getValue()
	{
		return Integer.parseInt(valueField.getText());
	}
	
	public Investigation getSelectedItem()
	{
		return (Investigation) investigationComboBox.getSelectedItem();
	}
}
