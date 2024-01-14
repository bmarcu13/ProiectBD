package controller.financial;

import model.financial.ExpertModel;
import model.financial.MedicalUnitProfitData;
import view.financial.ExpertView;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;

public class ExpertController {
    ExpertView expertView;
    ExpertModel expertModel;
    ExpertController(ExpertView _expertView, ExpertModel _expertModel) {
        this.expertView = _expertView;
        this.expertModel = _expertModel;
        this.expertView.setNameHolderNames(this.expertModel.getAllMedicNames());
        this.expertView.addNameHolderToView();
        this.setListeners();
    }

    private void setListeners() {
        this.expertView.getMonthHolder().addActionListener(e -> {
            this.expertModel.setSelectedMonth(this.expertView.getMonthHolder().getSelectedItem().toString());
        });

        this.expertView.getMonthHolder().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                expertModel.setSelectedMonth(expertView.getMonthHolder().getSelectedItem().toString());
            }
        });

        this.expertView.getMedicProfitsMonthHolder().addActionListener(e -> {
            this.expertModel.setSelectedMedicProfitsMonth(this.expertView.getMedicProfitsMonthHolder().getSelectedItem().toString());
        });

        this.expertView.getMedicProfitsMonthHolder().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                expertModel.setSelectedMonth(expertView.getMedicProfitsMonthHolder().getSelectedItem().toString());
            }
        });

        this.expertView.getYearHolder().addActionListener(e -> {
            this.expertModel.setYear(Integer.parseInt(this.expertView.getYearHolder().getText()));
        });

        this.expertView.getYearHolder().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                try {
                    int temp_year = Integer.parseInt(expertView.getYearHolder().getText());
                    expertModel.setYear(temp_year);

                } catch (NumberFormatException ex) {
                }
            }
        });

        this.expertView.getMedicProfitsYearHolder().addActionListener(e -> {
            try {
                int temp_year = Integer.parseInt(expertView.getMedicProfitsYearHolder().getText());
                expertModel.setMedicProfitsYear(temp_year);
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
        });

        this.expertView.getMedicProfitsYearHolder().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                try {
                    int temp_year = Integer.parseInt(expertView.getMedicProfitsYearHolder().getText());
                    expertModel.setMedicProfitsYear(temp_year);

                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }
            }
        });

        this.expertView.getSubmitHolder().addActionListener(e -> {
            try {
                Vector<MedicalUnitProfitData> medicalUnitsProfits = this.expertModel.getMedicalUnitsProfits();
                this.expertView.initTable(medicalUnitsProfits);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                this.expertView.showErrorMessage(ex.getMessage());
                EveryoneController.setTimeout(this.expertView::hideErrorMessage, 2000);
            }
        });

        this.expertView.getNameHolder().addActionListener(e -> {
            this.expertModel.setSelectedName(this.expertView.getNameHolder().getSelectedItem().toString());
        });

        this.expertView.getNameHolder().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                expertModel.setSelectedName(expertView.getNameHolder().getSelectedItem().toString());
            }
        });

        this.expertView.getViewMedicProfits().addActionListener(e -> {
            this.expertView.initProfitsTable(this.expertModel.getMedicProfitOnWorkingUnits());
        });
    }
}
