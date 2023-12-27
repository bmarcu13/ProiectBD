package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.*;

public class ApplicationView {
	private final String appName = "Management Policlinica";
	
	private JFrame jframe = new JFrame(appName);
	private JPanel mainPanel = new JPanel();
	private CardLayout cardLayout = new CardLayout();
	
	public ApplicationView()
	{
		jframe.setSize(1080, 720);
//		mainPanel.setSize(new Dimension(1080, 720));
//		mainPanel.setBackground(Color.blue);
		mainPanel.setLayout(cardLayout);
		
		jframe.setLocationRelativeTo(null);
		jframe.setLayout(new BorderLayout());
		jframe.add(mainPanel);
//		jframe.pack();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void openWindow()
	{
		jframe.setVisible(true);
	}
	
	public void addCardComponent(String componentName, Component component)
	{
		mainPanel.add(component, componentName);
	}
	
	public void showCardComponent(String componentName)
	{
		cardLayout.show(mainPanel, componentName);
	}
}
