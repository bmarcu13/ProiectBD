package view;

import java.awt.Color;

import javax.swing.JPanel;

public class MedicalView extends JPanel implements IView {

	public MedicalView()
	{
		initComponent();
	}
	
	@Override
	public void initComponent() {
		setBackground(Color.orange);
	}

}
