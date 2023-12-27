package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainView extends JPanel
{
	
	private CardLayout cardLayout = new CardLayout();
	
	private JPanel menuContainer = new JPanel(new GridLayout(1, 3));
	private JPanel pageContainer = new JPanel(cardLayout);
	
	private JButton module1Button = new JButton("Gestiune Resurse Umane");
	private JButton module2Button = new JButton("Financiar Contabil");
	private JButton module3Button = new JButton("Medical");
	
	public final static String HR_TAB = "HR";
	public final static String FINANCIAL_TAB = "FINANCIAL";
	public final static String MEDICAL_TAB = "MEDICAL";
	
	public MainView()
	{
		initComponent();
	}

	public void initComponent() {
		setLayout(new BorderLayout());
		
		menuContainer.setPreferredSize(new Dimension(getWidth(), 40));
		
		menuContainer.add(module1Button);
		menuContainer.add(module2Button);
		menuContainer.add(module3Button);
		
		add(menuContainer, BorderLayout.NORTH);
		add(pageContainer, BorderLayout.CENTER);
	}
	
	public void addTab(String key, JPanel panel)
	{
		pageContainer.add(key, panel);
	}
	
	public void switchTab(String tabName)
	{
		cardLayout.show(pageContainer, tabName);
	}
	
	public void setHrButtonListener(ActionListener _actionListener)
	{
		if(module1Button.getActionListeners().length > 0)
		{
			module1Button.removeActionListener(module1Button.getActionListeners()[0]);
		}
		
		module1Button.addActionListener(_actionListener);
	}
	
	public void setFinancialButtonListener(ActionListener _actionListener)
	{
		if(module2Button.getActionListeners().length > 0)
		{
			module2Button.removeActionListener(module2Button.getActionListeners()[0]);
		}
		
		module2Button.addActionListener(_actionListener);
	}
	
	public void setMedicalButtonListener(ActionListener _actionListener)
	{
		if(module3Button.getActionListeners().length > 0)
		{
			module3Button.removeActionListener(module3Button.getActionListeners()[0]);
		}
		
		module3Button.addActionListener(_actionListener);
	}
}
