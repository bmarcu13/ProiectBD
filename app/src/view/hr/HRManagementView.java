package view.hr;

import java.awt.*;

import javax.swing.JPanel;

public class HRManagementView extends JPanel
{

	private InspectorView inspectorView;
	private HRMedicalView hrMedicalView;

	public void addInspectorView(InspectorView _inspectorView) {
		this.inspectorView = _inspectorView;
		this.add(this.inspectorView);
	}

	public void addMedicalView(HRMedicalView _hrMedicalView) {
		this.hrMedicalView = _hrMedicalView;
		this.add(this.hrMedicalView);
	}

}
