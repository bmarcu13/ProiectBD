package view.financial;

import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Color;

public class FinancialView extends JPanel
{
	private CardLayout cardLayout = new CardLayout();
	public final static String medicViewName = "MedicView";
	public final static String everyoneViewName = "EveryoneView";
	public final static String expertViewName = "ExpertView";

	private MedicView medicView;
	private EveryoneView everyoneView;
	private ExpertView expertView;

	public FinancialView()
	{
		initComponent();
	}

	public void initComponent() {
		setBackground(Color.white);
	}

	public void addMedicView(MedicView _medicView) {
		this.medicView = _medicView;
		this.add(this.medicView);
	}

	public void addExpertView(ExpertView _expertView) {
		this.expertView = _expertView;
		this.add(this.expertView);
	}

	public void addEveryoneView(EveryoneView _everyoneView) {
		this.everyoneView = _everyoneView;
		this.add(this.everyoneView);
	}

}