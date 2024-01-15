package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LoginView extends JPanel
{
	
	private JPanel inputPanel = new JPanel();
	
	private BoxLayout inputPanelBoxLayout = new BoxLayout(inputPanel, BoxLayout.Y_AXIS);
	
	private JTextField usernameTextField = new JTextField(20);
	private JPasswordField passwordTextField = new JPasswordField(20);
	
	private JLabel errorMessageLabel = new JLabel();
	private JLabel titleLabel = new JLabel("Login");
	private JLabel usernameLabel = new JLabel("Username");
	private JLabel passwordLabel = new JLabel("Password");
	
	private JButton submitButton = new JButton("Login");
	
	public LoginView() 
	{
		initComponent();
	}
	
	public void initComponent() {
		setSize(new Dimension(1080, 720));
		setLayout(new GridBagLayout());
		
		inputPanel.setBackground(Color.white);
		inputPanel.setLayout(new GridLayout(8, 1));
		inputPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
	
		
		usernameTextField.setPreferredSize(new Dimension(200, 30));
		passwordTextField.setPreferredSize(new Dimension(200, 30));
		
		errorMessageLabel.setForeground(Color.red);
		errorMessageLabel.setVisible(false);
		
		titleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		
		inputPanel.add(errorMessageLabel);
		inputPanel.add(titleLabel);
		inputPanel.add(usernameLabel);
		inputPanel.add(usernameTextField);
		inputPanel.add(passwordLabel);
		inputPanel.add(passwordTextField);
		inputPanel.add(Box.createRigidArea(new Dimension(0, 0)));
		inputPanel.add(submitButton);
		
		add(inputPanel);
	}
	
	public String getUsername()
	{
		return usernameTextField.getText();
	}
	
	public String getPassword()
	{
		return String.valueOf(passwordTextField.getPassword());
	}
	
	public void setErrorMessage(String errorMessage)
	{
		errorMessageLabel.setText(errorMessage);
	}
	
	public void showError()
	{
		errorMessageLabel.setVisible(true);
	}
	
	public void hideError()
	{
		errorMessageLabel.setVisible(false);
	}
	
	public void setLoginButtonActionListener(ActionListener actionListener)
	{
		if(submitButton.getActionListeners().length > 0)
		{
			submitButton.removeActionListener(submitButton.getActionListeners()[0]);	
		}
		submitButton.addActionListener(actionListener);
	}
}
