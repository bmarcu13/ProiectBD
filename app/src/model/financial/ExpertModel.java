package model.financial;

import model.AuthenticationService;
import model.DatabaseService;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Vector;

public class ExpertModel {
    DatabaseService databaseService = DatabaseService.getInstance();
    private String selectedMonth = "Ianuarie";
    private int year;
    private HashMap<Integer, String> medicalUnits;
    private String selectedName = "";
    private String selectedMedicProfitsMonth = "Ianuarie";
    private int medicProfitsYear;

    public String getSelectedMonth() {
        return this.selectedMonth;
    }

    public void setSelectedMonth(String _month) {
        this.selectedMonth = _month;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int _year) {
        this.year = _year;
    }

    public String getSelectedName() {
        return this.selectedName;
    }

    public void setSelectedName(String _name) {
        this.selectedName = _name;
    }

    public void setSelectedMedicProfitsMonth(String _month) {
        this.selectedMedicProfitsMonth = _month;
    }

    public String getSelectedMedicProfitsMonth() {
        return this.selectedMedicProfitsMonth;
    }

    public void setMedicProfitsYear(int _year) {
        this.medicProfitsYear = _year;
    }

    public int getMedicProfitsYear() {
        return this.medicProfitsYear;
    }

    protected int convertMonth(String _month) {
        switch (_month) {
            case "Ianuarie":
                return 1;
            case "Februarie":
                return 2;
            case "Martie":
                return 3;
            case "Aprilie":
                return 4;
            case "Mai":
                return 5;
            case "Iunie":
                return 6;
            case "Iulie":
                return 7;
            case "August":
                return 8;
            case "Septembrie":
                return 9;
            case "Octombrie":
                return 10;
            case "Noiembrie":
                return 11;
            case "Decembrie":
                return 12;
            default:
                return -1;
        }
    }
    public ExpertModel() {

    }

    public int getMedicalUnitIncome(int _medicalUnitID) throws SQLException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, this.year);
        calendar.set(Calendar.MONTH, this.convertMonth(this.selectedMonth) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        long milliseconds = calendar.getTimeInMillis();
        Date date = new Date(milliseconds);

        return this.databaseService.getMedicalUnitIncome(date, _medicalUnitID);
    }

    public int getMedicalUnitExpenses(int _medicalUnitID) throws SQLException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, this.year);
        calendar.set(Calendar.MONTH, this.convertMonth(this.selectedMonth) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        long milliseconds = calendar.getTimeInMillis();
        Date date = new Date(milliseconds);

        return this.databaseService.getMedicalUnitExpenses(date, _medicalUnitID);
    }

    public int getMedicalUnitProfits(int _medicalUnitID) throws SQLException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, this.year);
        calendar.set(Calendar.MONTH, this.convertMonth(this.selectedMonth) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        long milliseconds = calendar.getTimeInMillis();
        Date date = new Date(milliseconds);
        return this.databaseService.getMedicalUnitProfit(date, _medicalUnitID);
    }

    public void getAllMedicalUnits() throws SQLException {
        this.medicalUnits = this.databaseService.getAllMedicalUnits();
    }
    public Vector<MedicalUnitProfitData> getMedicalUnitsProfits() throws SQLException {
        // initialize a new vector to hold the data
        Vector<MedicalUnitProfitData> medicalUnitsProfits = new Vector<>();
        // get the medical units data
        this.getAllMedicalUnits();
        // iterate through the medical units data and add it to the vector
        this.medicalUnits.forEach((medicalUnitID, medicalUnitName) -> {
            System.out.println("ID: " + medicalUnitID + ", Name " + medicalUnitName);
            try {
                medicalUnitsProfits.add(new MedicalUnitProfitData(medicalUnitID, medicalUnitName, this.getMedicalUnitIncome(medicalUnitID),
                        this.getMedicalUnitExpenses(medicalUnitID), this.getMedicalUnitProfits(medicalUnitID)));
            } catch(SQLException ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
        });
        return medicalUnitsProfits;
    }

    public Vector<String> getAllMedicNames() {
        try {
            return this.databaseService.getAllMedicNames();
        } catch(SQLException ex) {
            ex.printStackTrace();
            return new Vector<>();
        }
    }

    public ArrayList<Integer> getMedicWorkingUnits() {
        String name = selectedName.split(" ")[0];
        String surname = selectedName.split(" ")[1];
        try {
            return this.databaseService.getMedicWorkingUnits(this.databaseService.getMedicCnpByName(name, surname));
        } catch(SQLException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Vector<MedicProfitData> getMedicProfitOnWorkingUnits() {
        String name = selectedName.split(" ")[0];
        String surname = selectedName.split(" ")[1];

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, this.medicProfitsYear);
        calendar.set(Calendar.MONTH, this.convertMonth(this.selectedMedicProfitsMonth) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        long milliseconds = calendar.getTimeInMillis();

        Date date = new Date(milliseconds);

        Vector<MedicProfitData> medicProfits = new Vector<>();
        for (Integer workingUnit: this.getMedicWorkingUnits()) {
            try {
                medicProfits.add(new MedicProfitData(workingUnit, this.databaseService.getMedicalUnitName(workingUnit),
                        this.databaseService.getMedicProfitOnOneUnit(this.databaseService.getMedicCnpByName(name, surname), date, workingUnit)));
            } catch(SQLException ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
        }

        return medicProfits;
    }
}
