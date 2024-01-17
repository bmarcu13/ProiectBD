package view.medical.assistent;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class MedicalAssistentView extends JPanel{
	private CardLayout cardLayout = new CardLayout();
	
	public static String HOME_VIEW = "HOME";
	public static String INVESTIGATIONS_VIEW = "INVESTIGATIONS";
	
	
	public MedicalAssistentView() {
		setLayout(cardLayout);
		setBackground(Color.darkGray);
	}
	
	public void addTab(String key, JPanel component)
	{
		add(component, key);
	}
	
	public void switchTab(JPanel parent, String key)
	{
		cardLayout.show(parent, key);
	}
}
