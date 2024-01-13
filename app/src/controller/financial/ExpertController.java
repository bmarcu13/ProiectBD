package controller.financial;

import model.financial.ExpertModel;
import view.financial.ExpertView;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;

public class ExpertController {
    ExpertView expertView;
    ExpertModel expertModel;
    ExpertController(ExpertView _expertView, ExpertModel _expertModel) {
        this.expertView = _expertView;
        this.expertModel = _expertModel;
        this.setListeners();
    }

    private void setListeners() {
        this.expertView.getMonthHolder().addActionListener(e -> {
            this.expertModel.setSelectedMonth(this.expertView.getMonthHolder().getSelectedItem().toString());
            System.out.println("From the month listener: " + this.expertModel.getSelectedMonth());
        });

        this.expertView.getMonthHolder().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                expertModel.setSelectedMonth(expertView.getMonthHolder().getSelectedItem().toString());
                System.out.println("From the month listener: " + expertModel.getSelectedMonth());
            }
        });

        this.expertView.getYearHolder().addActionListener(e -> {
            this.expertModel.setYear(Integer.parseInt(this.expertView.getYearHolder().getText()));
            System.out.println("From the year listener: " + this.expertModel.getYear());
        });

        this.expertView.getYearHolder().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                try {
                    int temp_year = Integer.parseInt(expertView.getYearHolder().getText());
                    expertModel.setYear(temp_year);

                } catch (NumberFormatException ex) {
                }
                System.out.println("From the year listener: " + expertModel.getYear());
            }
        });

        this.expertView.getSubmitHolder().addActionListener(e -> {
            try {
                System.out.println("Profits: " + this.expertModel.getMedicalUnitProfits(1));
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                this.expertView.showErrorMessage(ex.getMessage());
                EveryoneController.setTimeout(this.expertView::hideErrorMessage, 2000);
            }
        });

        this.expertView.getSubmitHolder().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                try {
                    System.out.println("Profits: " + expertModel.getMedicalUnitProfits(1));
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                    expertView.showErrorMessage(ex.getMessage());
                    EveryoneController.setTimeout(expertView::hideErrorMessage, 2000);
                }
            }
        });
    }
}
