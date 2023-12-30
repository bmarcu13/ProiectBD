package model;

import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class DoctorComboBoxModel extends AbstractListModel<Doctor> implements ComboBoxModel<Doctor>{
	private Doctor selectedDoctor;
	private List<Doctor> doctors;
	
    public DoctorComboBoxModel(List<Doctor> _doctors) {
        this.doctors = _doctors;
    }
	
	@Override
	public int getSize() {
		return doctors.size();
	}

	@Override
	public Doctor getElementAt(int index) {
		return doctors.get(index);
	}

	@Override
	public void setSelectedItem(Object anItem) {
		selectedDoctor = (Doctor) anItem;
	}

	@Override
	public Object getSelectedItem() {
		return selectedDoctor;
	}

}
