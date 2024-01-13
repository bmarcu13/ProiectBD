package model.financial;

import model.AuthenticationService;
import model.DatabaseService;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Vector;

public class ExpertModel {
    DatabaseService databaseService = DatabaseService.getInstance();
    private String selectedMonth = "Ianuarie";
    private int year;
    private HashMap<Integer, String> medicalUnits;

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

    protected int convertMonth() {
        switch (this.selectedMonth) {
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
        calendar.set(Calendar.MONTH, this.convertMonth() - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        long milliseconds = calendar.getTimeInMillis();
        Date date = new Date(milliseconds);

        return this.databaseService.getMedicalUnitIncome(date, _medicalUnitID);
    }

    public int getMedicalUnitExpenses(int _medicalUnitID) throws SQLException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, this.year);
        calendar.set(Calendar.MONTH, this.convertMonth() - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        long milliseconds = calendar.getTimeInMillis();
        Date date = new Date(milliseconds);

        return this.databaseService.getMedicalUnitExpenses(date, _medicalUnitID);
    }

    public int getMedicalUnitProfits(int _medicalUnitID) throws SQLException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, this.year);
        calendar.set(Calendar.MONTH, this.convertMonth() - 1);
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
}
