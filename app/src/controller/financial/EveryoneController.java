package controller.financial;

import model.financial.EveryoneModel;
import view.financial.EveryoneView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;

public class EveryoneController {
    private final EveryoneView everyoneView;
    private final EveryoneModel everyoneModel;

    public EveryoneController(EveryoneView _everyoneView, EveryoneModel _everyoneModel) {
        this.everyoneView = _everyoneView;
        this.everyoneModel = _everyoneModel;

        this.everyoneView.getMonthHolder().addActionListener(e -> {
            this.everyoneModel.setSelectedMonth(this.everyoneView.getMonthHolder().getSelectedItem().toString());
            System.out.println("From the month listener: " + this.everyoneModel.getSelectedMonth());
        });

        this.everyoneView.getMonthHolder().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                everyoneModel.setSelectedMonth(everyoneView.getMonthHolder().getSelectedItem().toString());
                System.out.println("From the month listener: " + everyoneModel.getSelectedMonth());
            }
        });

        this.everyoneView.getYearHolder().addActionListener(e -> {
            this.everyoneModel.setYear(Integer.parseInt(this.everyoneView.getYearHolder().getText()));
            System.out.println("From the year listener: " + this.everyoneModel.getYear());
        });

        this.everyoneView.getYearHolder().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                try {
                    int temp_year = Integer.parseInt(everyoneView.getYearHolder().getText());
                    everyoneModel.setYear(temp_year);

                } catch (NumberFormatException ex) {
                }
                System.out.println("From the year listener: " + everyoneModel.getYear());
            }
        });

        this.everyoneView.getSubmit().addActionListener(e -> {
            try {
                this.everyoneView.getEarnings().setText(Integer.toString(this.everyoneModel.getEmployeeEarnings()));
            } catch (SQLException ex) {
                this.everyoneView.showErrorMessage(ex.getMessage());
                setTimeout(this.everyoneView::hideErrorMessage, 2000);
            }
            this.everyoneView.showEarnings();
        });
    }
    public static void setTimeout(Runnable runnable, int delay){
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            }
            catch (Exception e){
                System.err.println(e);
            }
        }).start();
    }

    public String getCnp() {
        return this.everyoneModel.getCnp();
    }
}
