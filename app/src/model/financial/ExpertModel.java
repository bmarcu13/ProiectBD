package model.financial;

import model.AuthenticationService;
import model.DatabaseService;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

public class ExpertModel {
    DatabaseService databaseService = DatabaseService.getInstance();
    private String selectedMonth = "Ianuarie";
    private int year;

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

    public int getMedicalUnitProfits(int _medicalUnitID) throws SQLException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, this.year);
        calendar.set(Calendar.MONTH, this.convertMonth() - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        long milliseconds = calendar.getTimeInMillis();
        Date date = new Date(milliseconds);
        return this.databaseService.getMedicalUnitProfit(date, _medicalUnitID);
    }
}
