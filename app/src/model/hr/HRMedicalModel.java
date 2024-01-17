package model.hr;

import model.AuthenticationService;
import model.DatabaseService;

import java.sql.Date;
import java.sql.SQLException;
import java.time.Year;
import java.util.Calendar;
import java.util.Vector;

public class HRMedicalModel {
    DatabaseService databaseService = DatabaseService.getInstance();
    AuthenticationService authenticationService;

    private String selectedMonth = "Ianuarie";
    private int selectedYear = Year.now().getValue();
    private String selectedName;
    private String selectedRank;

    public HRMedicalModel(AuthenticationService _authenticationService) {
        this.authenticationService = _authenticationService;
        try {
            this.selectedName = this.databaseService.getEmployeeName(this.databaseService.getUserCnp(this.authenticationService.getEmail()));
            System.out.println("Got the name " + this.selectedName);
        } catch(SQLException ex) {
            ex.printStackTrace();
            this.selectedName = " ";
        }

        try {
            this.selectedRank = this.databaseService.getEmployeeRank(this.databaseService.getUserCnp(this.authenticationService.getEmail()));
            System.out.println("Got the rank " + this.selectedRank);
        } catch(SQLException ex) {
            ex.printStackTrace();
            this.selectedRank = "";
        }
    }

    public void setSelectedMonth(String _selectedMonth) {
        this.selectedMonth = _selectedMonth;
    }

    public void setSelectedYear(int _selectedYear) {
        this.selectedYear = _selectedYear;
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

    public Vector<GenericTimetable> getEmployeeGenericTimetable() {
        String[] nameParts = this.selectedName.split(" ");
        String name = nameParts.length > 0 ? nameParts[0] : "";
        String surname = nameParts.length > 1 ? nameParts[1] : "";

        try {
            return this.databaseService.getEmployeeGenericTimetable(name, surname, this.selectedRank);
        } catch(SQLException ex) {
            ex.printStackTrace();
            return new Vector<>();
        }
    }

    public Vector<SpecificTimetable> getEmployeeSpecificTimetable() {
        String[] nameParts = this.selectedName.split(" ");
        String name = nameParts.length > 0 ? nameParts[0] : "";
        String surname = nameParts.length > 1 ? nameParts[1] : "";

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, this.selectedYear);
        calendar.set(Calendar.MONTH, this.convertMonth(this.selectedMonth) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        long millis = calendar.getTimeInMillis();

        Date date = new Date(millis);
        try {
            return this.databaseService.getEmployeeSpecificTimetable(name, surname, this.selectedRank, date);
        } catch(SQLException ex) {
            ex.printStackTrace();
            return new Vector<>();
        }
    }

    public Vector<EmployeeVacation> getEmployeeVacations() {
        String[] nameParts = this.selectedName.split(" ");
        String name = nameParts.length > 0 ? nameParts[0] : "";
        String surname = nameParts.length > 1 ? nameParts[1] : "";

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, this.selectedYear);
        calendar.set(Calendar.MONTH, this.convertMonth(this.selectedMonth) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        long millis = calendar.getTimeInMillis();

        Date date = new Date(millis);
        try {
            return this.databaseService.getEmployeeVacations(name, surname, this.selectedRank, date);
        } catch(SQLException ex) {
            ex.printStackTrace();
            return new Vector<>();
        }
    }
}
