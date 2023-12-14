package view;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class HRManagementView extends JPanel implements IView{
	
	public HRManagementView()
	{
		initComponent();
	}
	
	@Override
	public void initComponent() {
		setBackground(Color.blue);
	}

}
