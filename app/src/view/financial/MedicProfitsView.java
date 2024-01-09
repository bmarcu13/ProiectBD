package view.financial;

import controller.financial.EveryoneController;

import javax.swing.*;

public class MedicProfitsView extends EveryoneView {
    public MedicProfitsView() {
        super();
        this.submit.setText("View profits");
    }

    public JComboBox getMonthHolder() {
        return this.monthHolder;
    }

    public JFormattedTextField getYearHolder() {
        return this.yearHolder;
    }


    public JButton getSubmit() {
        return this.submit;
    }
}
