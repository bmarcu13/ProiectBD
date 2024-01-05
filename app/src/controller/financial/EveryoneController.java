package controller.financial;

import model.financial.EveryoneModel;
import view.financial.EveryoneView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class EveryoneController implements ActionListener {
    private final EveryoneView everyoneView;
    private final EveryoneModel everyoneModel;

    public EveryoneController(EveryoneView _everyoneView, EveryoneModel _everyoneModel) {
        this.everyoneView = _everyoneView;
        this.everyoneModel = _everyoneModel;

        this.everyoneView.getMonthHolder().addActionListener(e -> {
                this.everyoneModel.setSelectedMonth(this.everyoneView.getMonthHolder().getSelectedItem().toString());
            System.out.println("From the month listener: " + this.everyoneModel.getSelectedMonth());
        });

        this.everyoneView.getCnpHolder().addActionListener(e -> {
            this.everyoneModel.setCnp(this.everyoneView.getCnpHolder().getText());
            System.out.println("From the cnp listener: " + this.everyoneModel.getCnp());
        });

        this.everyoneView.getYearHolder().addActionListener(e -> {
            this.everyoneModel.setYear(Integer.parseInt(this.everyoneView.getYearHolder().getText()));
            System.out.println("From the year listener: " + this.everyoneModel.getYear());
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

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("From the listener: " + this.everyoneView.getMonthHolder().getSelectedItem().toString());
        this.everyoneModel.setSelectedMonth(this.everyoneView.getMonthHolder().getSelectedItem().toString());
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
}
