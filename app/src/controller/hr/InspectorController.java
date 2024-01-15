package controller.hr;

import model.hr.InspectorModel;
import view.hr.InspectorView;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Vector;

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
            this.inspectorModel.setSelectedName((String) this.inspectorView.getNameHolder().getSelectedItem());
            this.inspectorView.updateRankHolder(this.inspectorModel.getAllRanks());
            this.inspectorModel.setSelectedRank((String) this.inspectorView.getRankHolder().getSelectedItem());
            System.out.println("Name listener: " + this.inspectorView.getRankHolder().getSelectedItem());
        });

        this.inspectorView.getNameHolder().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                inspectorModel.setSelectedName((String) inspectorView.getNameHolder().getSelectedItem());
                inspectorView.updateRankHolder(inspectorModel.getAllRanks());
                inspectorModel.setSelectedRank((String) inspectorView.getRankHolder().getSelectedItem());
                System.out.println("Name listener: " + inspectorView.getRankHolder().getSelectedItem());
            }
        });

        this.inspectorView.getRankHolder().addActionListener(e -> {
            this.inspectorModel.setSelectedRank((String) this.inspectorView.getRankHolder().getSelectedItem());
            System.out.println("Rank ActionListener: " + this.inspectorView.getRankHolder().getSelectedItem());
        });

        this.inspectorView.getRankHolder().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                inspectorModel.setSelectedRank((String) inspectorView.getRankHolder().getSelectedItem());
                System.out.println("Rank FocusListener: " + inspectorView.getRankHolder().getSelectedItem());
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
            this.inspectorView.initSpecificTimetableTable(this.inspectorModel.getEmployeeSpecificTimetable());
            this.inspectorView.initEmployeeVacationsTable(this.inspectorModel.getEmployeeVacations());
        });

    }
}
