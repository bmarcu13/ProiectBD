package view.medical;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.Investigation;
import view.medical.resources.InvestigationSection;

public class MedicalAssistentInvestigationsView extends JPanel{
	private JPanel buttonsContainer = new JPanel();
	private JPanel investigationContainer = new JPanel();
	
	private BoxLayout investigationContainerBoxLayout = new BoxLayout(investigationContainer, BoxLayout.Y_AXIS);
	
	private JButton addInvestigationButton = new JButton("Adaugare analiza"); 
	private JButton finishButton = new JButton("Finalizare"); 
	private JButton backButton = new JButton("Inapoi"); 
	
	private ActionListener investiagtionSectionActionListener;
	
	private Vector<InvestigationSection> addedInvestigations = new Vector<InvestigationSection>();
	
	public MedicalAssistentInvestigationsView() {
		setLayout(new BorderLayout());
		
		buttonsContainer.add(backButton);
		buttonsContainer.add(addInvestigationButton);
		buttonsContainer.add(finishButton);
		add(buttonsContainer, BorderLayout.SOUTH);
		investigationContainer.setLayout(investigationContainerBoxLayout);
		add(investigationContainer);
	}
	

	private void renderInvestigationList()
	{
		investigationContainer.removeAll();
		investigationContainer.add(Box.createVerticalStrut(10));
		for(InvestigationSection i : addedInvestigations)
		{
			investigationContainer.add(i);
			investigationContainer.add(Box.createVerticalStrut(10));
		}
		investigationContainer.revalidate();
		investigationContainer.repaint();
	}
	
	public void setInvestigationButtonActionListener(ActionListener al)
	{
		addInvestigationButton.addActionListener(al);
	}
	
//	Make sure setInvestigationSectionActionListener is called before calling this function
	public void addInvestigation(Investigation investigation)
	{
		InvestigationSection investigationSection = new InvestigationSection(investigation);
		if(investiagtionSectionActionListener != null)
		{
			investigationSection.setInvestigationComboBoxActionListener(investiagtionSectionActionListener);
		}
		addedInvestigations.add(investigationSection);
		renderInvestigationList();
	}
	
	public Vector<Investigation> getAddedInvestigations()
	{
		Vector<Investigation> investigations = new Vector<Investigation>();
		for(InvestigationSection is : addedInvestigations)
		{
			Investigation investigation = is.getSelectedItem();
			investigation.setValue(is.getValue());
			investigations.add(investigation);
			
			System.out.println(investigation.getId() + " " + investigation.getName());
		}
		
		return investigations;
	}
	
	public void emptyFields()
	{
		addedInvestigations.clear();
		renderInvestigationList();
	}
	
	public void setInvestigationSectionActionListener(ActionListener al)
	{
		investiagtionSectionActionListener = al;
	}
	
	public void setFinishButtonActionListener(ActionListener al)
	{
		finishButton.addActionListener(al);
	}
	
	public void setBackButtonActionListener(ActionListener al)
	{
		backButton.addActionListener(al);
	}
}
