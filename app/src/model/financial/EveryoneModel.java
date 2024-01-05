package model.financial;

import view.financial.EveryoneView;

import javax.swing.*;
import model.DatabaseService;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Calendar;

public class EveryoneModel {
    private final DatabaseService databaseService;
    private String selectedMonth = "Ianuarie";
    private String cnp;
    private int year;
    public EveryoneModel() {
        this.databaseService = DatabaseService.getInstance();
    }

    public String getSelectedMonth() {
        return this.selectedMonth;
    }

    public String getCnp() {
        return this.cnp;
    }

    public int getYear() {
        return this.year;
    }

    public void setSelectedMonth(String _month) {
        this.selectedMonth = _month;
    }

    public void setCnp(String _cnp) {
        this.cnp = _cnp;
    }

    public void setYear(int _year) {
        this.year = _year;
    }

    private int convertMonth() {
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

    public int getEmployeeEarnings() throws SQLException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, this.year);
        calendar.set(Calendar.MONTH, this.convertMonth() - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        long milliseconds = calendar.getTimeInMillis();

        Date date = new Date(milliseconds);
        System.out.println(date);
        return this.databaseService.getEmployeeEarnings(this.cnp, date);
    }
}
