package view.medical.resources;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import model.Investigation;

public class InvestigationSection extends JPanel{
	private JPanel leftPanel = new JPanel();
	
	private JComboBox<Investigation> investigationComboBox = new JComboBox<>();
	
	private JTextField valueField = new JTextField();
	
	private JLabel investigationLabel = new JLabel("Analiza");
	private JLabel valueLabel = new JLabel("Valoare");
	
	private JButton deleteButton = new JButton("Stergere");
	
	public InvestigationSection(Investigation investigation)
	{	
		setLayout(new BorderLayout());
		setBackground(Color.white);
		if(investigation.getValue() != -1)
		{
			valueField.setText(String.valueOf(investigation.getValue()));
		}
		
		investigationComboBox.setModel(new DefaultComboBoxModel<Investigation>(investigation.getSelectableInvestigations()));
		
		if(investigation.getName() != null)
		{
			int index = 0;
			for(Investigation i : investigation.getSelectableInvestigations())
			{
				if(i.getId() == investigation.getId())
				{
					index = investigation.getSelectableInvestigations().indexOf(i);
				}
			}
			investigationComboBox.setSelectedIndex(index); //db ids are 1 indexed and combobox ids are 0 index
			investigationComboBox.setEnabled(false);
		}
		
		investigationComboBox.setMaximumSize(new Dimension(300, 30));
		investigationComboBox.setBorder(new LineBorder(Color.white, 5));
		add(investigationComboBox, BorderLayout.WEST);
		
//		add(Box.createHorizontalStrut(100));
		
		leftPanel.setBackground(Color.white);
		valueField.setPreferredSize(new Dimension(100, 30));
		leftPanel.add(valueLabel);
		leftPanel.add(valueField);
		leftPanel.add(deleteButton);
		add(leftPanel, BorderLayout.EAST);
		
		setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
	}
	
	public void setInvestigationComboBoxActionListener(ActionListener al)
	{
		investigationComboBox.addActionListener(al);
	}
	
	public void setDeleteButtonActionListener(ActionListener al)
	{
		deleteButton.addActionListener(al);
	}
	
	public int getValue()
	{
		return Integer.parseInt(valueField.getText());
	}
	
	public String getRawValue()
	{
		return valueField.getText();
	}
	
	public Investigation getSelectedItem()
	{
		return (Investigation) investigationComboBox.getSelectedItem();
	}
	
	public void setId(int id)
	{
		deleteButton.setName(String.valueOf(id));
		setName(String.valueOf(id));
	}
	
	public void setButtonEnabled(boolean isEnabled)
	{
		deleteButton.setEnabled(isEnabled);
	}
}
