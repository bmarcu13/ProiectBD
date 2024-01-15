package model.financial;

import model.AuthenticationService;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

public class MedicProfitsModel extends EveryoneModel {
    public MedicProfitsModel(AuthenticationService _authenticationService) {
        super(_authenticationService);
    }

    public int getMedicSalary() throws SQLException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, this.year);
        calendar.set(Calendar.MONTH, this.convertMonth() - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        long milliseconds = calendar.getTimeInMillis();

        Date date = new Date(milliseconds);
        System.out.println(date);
        return this.databaseService.getEmployeeEarnings(this.cnp, date);
    }

    public int getMedicPaidServicesProfit(int medicalUnitID) throws SQLException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, this.year);
        calendar.set(Calendar.MONTH, this.convertMonth() - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        long milliseconds = calendar.getTimeInMillis();

        Date date = new Date(milliseconds);
        System.out.println(date);
        return this.databaseService.getMedicPaidServicesProfit(this.cnp, date, medicalUnitID);
    }

    public int getMedicProfitOnOneUnit(int medicalUnitID) throws SQLException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, this.year);
        calendar.set(Calendar.MONTH, this.convertMonth() - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        long milliseconds = calendar.getTimeInMillis();

        Date date = new Date(milliseconds);
        System.out.println(date);
        return this.databaseService.getMedicProfitOnOneUnit(this.cnp, date, medicalUnitID);
    }

}
