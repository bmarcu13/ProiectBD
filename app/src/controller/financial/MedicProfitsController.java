package controller.financial;

import model.DatabaseService;
import model.financial.MedicProfitsModel;
import view.financial.MedicProfitsView;
import view.financial.MedicView;

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
    private ArrayList<Integer> medicCommission = new ArrayList<>();
    private int medicSalary;
    public MedicProfitsController(MedicProfitsView _medicProfitsView, MedicProfitsModel _medicProfitsModel, MedicView _medicView) {
        this.medicProfitsView = _medicProfitsView;
        this.medicProfitsModel = _medicProfitsModel;
        this.medicProfitsView.getMonthHolder().addActionListener(e -> {
            this.medicProfitsModel.setSelectedMonth(this.medicProfitsView.getMonthHolder().getSelectedItem().toString());
        });

        this.medicProfitsView.getMonthHolder().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                medicProfitsModel.setSelectedMonth(medicProfitsView.getMonthHolder().getSelectedItem().toString());
            }
        });

        this.medicProfitsView.getYearHolder().addActionListener(e -> {
            this.medicProfitsModel.setYear(Integer.parseInt(this.medicProfitsView.getYearHolder().getText()));
        });

        this.medicProfitsView.getYearHolder().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                try {
                    int temp_year = Integer.parseInt(medicProfitsView.getYearHolder().getText());
                    medicProfitsModel.setYear(temp_year);
                } catch(NumberFormatException ex) {
                }
            }
        });

        this.medicProfitsView.getSubmit().addActionListener(e -> {
            try {
                workingUnitIDs.clear();
                medicalUnitNames.clear();
                medicGeneratedProfit.clear();
                medicTotalProfit.clear();
                medicCommission.clear();
                this.workingUnitIDs = this.databaseService.getMedicWorkingUnits(this.medicProfitsModel.getCnp());
                for (int i = 0; i < this.workingUnitIDs.size(); i++) {
                    this.medicalUnitNames.add(this.databaseService.getMedicalUnitName(this.workingUnitIDs.get(i)));
                    this.medicGeneratedProfit.add(this.medicProfitsModel.getMedicPaidServicesProfit(this.workingUnitIDs.get(i)));
                    this.medicSalary = this.medicProfitsModel.getMedicSalary();
                    this.medicTotalProfit.add(this.medicProfitsModel.getMedicProfitOnOneUnit(this.workingUnitIDs.get(i)));
                    this.medicCommission.add(this.medicGeneratedProfit.get(i) * this.databaseService.getMedicCommission(this.medicProfitsModel.getCnp())/100);
                    _medicView.initTable(convertIntoTableData());
                }
            } catch (SQLException ex) {
                this.medicProfitsView.showErrorMessage(ex.getMessage());
                setTimeout(this.medicProfitsView::hideErrorMessage, 2000);
            }
        });
    }

    public Object[][] convertIntoTableData() {
        int size = Math.min(
                Math.min(workingUnitIDs.size(), medicalUnitNames.size()),
                Math.min(medicGeneratedProfit.size(), medicTotalProfit.size())
        );

        Object[][] tableData = new Object[size][6];

        for (int i = 0; i < size; i++) {
            tableData[i][0] = medicalUnitNames.get(i);
            tableData[i][1] = workingUnitIDs.get(i);
            tableData[i][2] = i < medicGeneratedProfit.size() ? medicGeneratedProfit.get(i) : null;
            tableData[i][3] = medicSalary;
            tableData[i][4] = medicCommission.get(i);
            tableData[i][5] = medicTotalProfit.get(i);
        }

        return tableData;
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
