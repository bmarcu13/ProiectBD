package controller.financial;

import model.DatabaseService;
import model.financial.MedicProfitsModel;
import view.financial.MedicProfitsView;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedicProfitsController {
    private DatabaseService databaseService = DatabaseService.getInstance();
    private MedicProfitsView medicProfitsView;
    private MedicProfitsModel medicProfitsModel;

    private ArrayList<Integer> workingUnitIDs = new ArrayList<>();
    private ArrayList<String> medicalUnitNames = new ArrayList<>();
    private ArrayList<Integer> medicGeneratedProfit = new ArrayList<>();
    private ArrayList<Integer> medicTotalProfit = new ArrayList<>();
    private int medicSalary;
    public MedicProfitsController(MedicProfitsView _medicProfitsView, MedicProfitsModel _medicProfitsModel) {
        this.medicProfitsView = _medicProfitsView;
        this.medicProfitsModel = _medicProfitsModel;
        this.medicProfitsView.getMonthHolder().addActionListener(e -> {
            this.medicProfitsModel.setSelectedMonth(this.medicProfitsView.getMonthHolder().getSelectedItem().toString());
            System.out.println("From the month listener: " + this.medicProfitsModel.getSelectedMonth());
        });

        this.medicProfitsView.getMonthHolder().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                medicProfitsModel.setSelectedMonth(medicProfitsView.getMonthHolder().getSelectedItem().toString());
                System.out.println("From the month listener: " + medicProfitsModel.getSelectedMonth());
            }
        });

        this.medicProfitsView.getYearHolder().addActionListener(e -> {
            this.medicProfitsModel.setYear(Integer.parseInt(this.medicProfitsView.getYearHolder().getText()));
            System.out.println("From the year listener: " + this.medicProfitsModel.getYear());
        });

        this.medicProfitsView.getYearHolder().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                try {
                    int temp_year = Integer.parseInt(medicProfitsView.getYearHolder().getText());
                    medicProfitsModel.setYear(temp_year);
                } catch(NumberFormatException ex) {
                }
                System.out.println("From the year listener: " + medicProfitsModel.getYear());
            }
        });

        this.medicProfitsView.getSubmit().addActionListener(e -> {
            try {
                this.workingUnitIDs = this.databaseService.getMedicWorkingUnits(this.medicProfitsModel.getCnp());
                for (int i = 0; i < this.workingUnitIDs.size(); i++) {
                    this.medicalUnitNames.add(this.databaseService.getMedicalUnitName(this.workingUnitIDs.get(i)));
                    this.medicGeneratedProfit.add(this.medicProfitsModel.getMedicPaidServicesProfit(this.workingUnitIDs.get(i)));
                    this.medicSalary = this.medicProfitsModel.getMedicSalary();
                    this.medicTotalProfit.add(this.medicProfitsModel.getMedicProfitOnOneUnit(this.workingUnitIDs.get(i)));
                    System.out.println("{ " + this.medicalUnitNames.get(i) + " " + this.workingUnitIDs.get(i) + " " + this.medicGeneratedProfit.get(i) + " " + this.medicSalary + " " + this.medicTotalProfit.get(i) + " }\n");
                }
            } catch (SQLException ex) {
                this.medicProfitsView.showErrorMessage(ex.getMessage());
                setTimeout(this.medicProfitsView::hideErrorMessage, 2000);
            }
        });

        this.medicProfitsView.getSubmit().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                try {
                    medicProfitsView.getEarnings().setText(Integer.toString(medicProfitsModel.getEmployeeEarnings()));
                } catch (SQLException ex) {
                    medicProfitsView.showErrorMessage(ex.getMessage());
                    setTimeout(medicProfitsView::hideErrorMessage, 2000);
                }
            }
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
}
