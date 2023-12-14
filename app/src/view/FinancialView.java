package view;

import java.awt.Color;

import javax.swing.JPanel;

public class FinancialView extends JPanel implements IView {

	public FinancialView()
	{
		initComponent();
	}
	
	@Override
	public void initComponent() {
		setBackground(Color.cyan);
	}

}
