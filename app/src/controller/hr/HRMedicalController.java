package controller.hr;

import model.hr.HRMedicalModel;
import view.hr.HRMedicalView;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class HRMedicalController {
    HRMedicalView hrMedicalView;
    HRMedicalModel hrMedicalModel;

    public HRMedicalController(HRMedicalView _hrMedicalView, HRMedicalModel _hrMedicalModel) {
        this.hrMedicalView = _hrMedicalView;
        this.hrMedicalModel = _hrMedicalModel;
        this.setListeners();
    }

    public void setListeners() {
        this.hrMedicalView.getYearHolder().addActionListener(e -> {
            try {
                int temp_year = Integer.parseInt(hrMedicalView.getYearHolder().getText());
                hrMedicalModel.setSelectedYear(temp_year);

            } catch (NumberFormatException ex) {
            }
        });

        this.hrMedicalView.getYearHolder().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                try {
                    int temp_year = Integer.parseInt(hrMedicalView.getYearHolder().getText());
                    hrMedicalModel.setSelectedYear(temp_year);

                } catch (NumberFormatException ex) {
                }
            }
        });

        this.hrMedicalView.getMonthHolder().addActionListener(e -> {
            this.hrMedicalModel.setSelectedMonth(this.hrMedicalView.getMonthHolder().getSelectedItem().toString());
        });

        this.hrMedicalView.getMonthHolder().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                hrMedicalModel.setSelectedMonth(hrMedicalView.getMonthHolder().getSelectedItem().toString());
            }
        });

        this.hrMedicalView.getSubmitHolder().addActionListener(e -> {
            this.hrMedicalView.initGenericTimetableTable(this.hrMedicalModel.getEmployeeGenericTimetable());
            this.hrMedicalView.initSpecificTimetableTable(this.hrMedicalModel.getEmployeeSpecificTimetable());
            this.hrMedicalView.initEmployeeVacationsTable(this.hrMedicalModel.getEmployeeVacations());
        });
    }
}
