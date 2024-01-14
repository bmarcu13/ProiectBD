package controller.hr;

import model.hr.InspectorModel;
import view.hr.InspectorView;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class InspectorController {
    InspectorView inspectorView;
    InspectorModel inspectorModel;
    public InspectorController(InspectorView _inspectorView, InspectorModel _inspectorModel) {
        this.inspectorView = _inspectorView;
        this.inspectorModel = _inspectorModel;
        this.inspectorView.initNameHolder(this.inspectorModel.getAllEmployeeNames());
        this.inspectorView.initRankHolder(this.inspectorModel.getAllRanks());
        this.inspectorView.initUserInputPanel();
        this.inspectorView.addUserInputToView();
        this.setListeners();
    }

    public void setListeners() {
        this.inspectorView.getNameHolder().addActionListener(e -> {
            this.inspectorModel.setSelectedName(this.inspectorView.getNameHolder().getSelectedItem().toString());
        });

        this.inspectorView.getNameHolder().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                inspectorModel.setSelectedName(inspectorView.getNameHolder().getSelectedItem().toString());
            }
        });

        this.inspectorView.getRankHolder().addActionListener(e -> {
            this.inspectorModel.setSelectedRank(this.inspectorView.getRankHolder().getSelectedItem().toString());
        });

        this.inspectorView.getRankHolder().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                inspectorModel.setSelectedRank(inspectorView.getRankHolder().getSelectedItem().toString());
            }
        });

        this.inspectorView.getYearHolder().addActionListener(e -> {
            try {
                int temp_year = Integer.parseInt(inspectorView.getYearHolder().getText());
                inspectorModel.setSelectedYear(temp_year);

            } catch (NumberFormatException ex) {
            }
        });

        this.inspectorView.getYearHolder().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                try {
                    int temp_year = Integer.parseInt(inspectorView.getYearHolder().getText());
                    inspectorModel.setSelectedYear(temp_year);

                } catch (NumberFormatException ex) {
                }
            }
        });

        this.inspectorView.getMonthHolder().addActionListener(e -> {
            this.inspectorModel.setSelectedMonth(this.inspectorView.getMonthHolder().getSelectedItem().toString());
        });

        this.inspectorView.getMonthHolder().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                inspectorModel.setSelectedMonth(inspectorView.getMonthHolder().getSelectedItem().toString());
            }
        });

        this.inspectorView.getSubmitHolder().addActionListener(e -> {
            this.inspectorView.initGenericTimetableTable(this.inspectorModel.getEmployeeGenericTimetable());
//            this.inspectorModel.getEmployeeSpecificTimetable();
        });

    }
}
