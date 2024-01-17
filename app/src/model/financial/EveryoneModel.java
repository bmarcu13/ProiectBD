package model.financial;

import model.AuthenticationService;
import view.financial.EveryoneView;

import javax.swing.*;
import model.DatabaseService;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Calendar;
public class EveryoneModel {
    protected final DatabaseService databaseService;
    protected String selectedMonth = "Ianuarie";
    protected final String cnp;
    protected int year;
    public EveryoneModel(AuthenticationService authenticationService) {
        this.databaseService = DatabaseService.getInstance();

        // initialize the cnp of the user
        String temp_cnp;
        try {
            temp_cnp = this.databaseService.getUserCNP(authenticationService.getEmail());
        } catch (SQLException e) {
            temp_cnp = "";
            e.printStackTrace();
        }
        this.cnp = temp_cnp;
    }

    public String getCnp() {
        return this.cnp;
    }

    public String getSelectedMonth() {
        return this.selectedMonth;
    }

    public int getYear() {
        return this.year;
    }

    public void setSelectedMonth(String _month) {
        this.selectedMonth = _month;
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

    public int getEmployeeEarnings() throws SQLException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, this.year);
        calendar.set(Calendar.MONTH, this.convertMonth() - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        long milliseconds = calendar.getTimeInMillis();

        Date date = new Date(milliseconds);
        return this.databaseService.getEmployeeEarnings(this.cnp, date);
    }
}
