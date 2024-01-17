package view.medical.resources;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import model.Investigation;
import model.MedicalReport;

public class MedicalReportSection extends JPanel{
	
	private MedicalReport mr;
	private JPanel investigationsPanel = new JPanel();
	
	private BoxLayout bl = new BoxLayout(this, BoxLayout.Y_AXIS);
	
	private JLabel simptomsLabel = new JLabel("Simptome");
	private JLabel diagnosisLabel = new JLabel("Diagnostic");
	private JLabel recommendationsLabel = new JLabel("Recomandari");
	private JLabel dateLabel = new JLabel();
	
	private JTextArea simptomsArea = new JTextArea(5, 20);
	private JTextArea diagnosisArea = new JTextArea(5, 20);
	private JTextArea recommendationsArea = new JTextArea(5, 20);
	
	public MedicalReportSection(MedicalReport mr)
	{
		this.mr = mr;
		this.setLayout(bl);
		this.setBorder(new LineBorder(Color.gray));
		
		simptomsArea.setText(mr.getSimptoms());
		diagnosisArea.setText(mr.getDiagnosis());
		recommendationsArea.setText(mr.getRecommendations());
		
		add(simptomsLabel);
		
		simptomsArea.setDisabledTextColor(Color.darkGray);
		simptomsArea.setEnabled(false);
		add(simptomsArea);
		
		add(diagnosisLabel);

		diagnosisArea.setDisabledTextColor(Color.darkGray);
		diagnosisArea.setEnabled(false);
		add(diagnosisArea);
		
		add(recommendationsLabel);
		
		recommendationsArea.setDisabledTextColor(Color.darkGray);
		recommendationsArea.setEnabled(false);
		add(recommendationsArea); 

		add(investigationsPanel);
		
		setInvestigations();
	}
	
	public void setInvestigations()
	{
		for(Investigation i : mr.getServices())
		{			
			InvestigationSection is = new InvestigationSection(i);
			is.setViewOnly();
			add(is);
			add(Box.createVerticalStrut(10));
		}
		
		revalidate();
		repaint();
	}
}
