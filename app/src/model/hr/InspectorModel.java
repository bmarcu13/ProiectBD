package model.hr;

import model.DatabaseService;

import java.sql.Date;
import java.sql.SQLException;
import java.time.Year;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Vector;

public class InspectorModel {
    DatabaseService databaseService = DatabaseService.getInstance();

    private String selectedMonth = "Ianuarie";
    private int selectedYear = Year.now().getValue();
    private String selectedName = " ";
    private String selectedRank = "";
    public InspectorModel() {

    }

    public void setSelectedMonth(String _selectedMonth) {
        this.selectedMonth = _selectedMonth;
    }

    public void setSelectedYear(int _selectedYear) {
        this.selectedYear = _selectedYear;
    }

    public void setSelectedName(String _selectedName) {
        this.selectedName = _selectedName;
    }

    public void setSelectedRank(String _selectedRank) {
        this.selectedRank = _selectedRank;
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

    public Vector<String> getAllEmployeeNames() {
        try {
            Vector<String> names = this.databaseService.getAllEmployeeNames();
            this.setSelectedName(names.getFirst());
            return names;
        } catch(SQLException ex) {
            ex.printStackTrace();
            return new Vector<>();
        }
    }

    public Vector<String> getAllRanks() {
        try {
            Vector<String> ranks = this.databaseService.getAllRanks();
            this.setSelectedRank(ranks.getFirst());
            return ranks;
        } catch(SQLException ex) {
            ex.printStackTrace();
            return new Vector<>();
        }
    }

    public Vector<GenericTimetable> getEmployeeGenericTimetable() {
        String[] nameParts = this.selectedName.split(" ");
        String name = nameParts.length > 0 ? nameParts[0] : "";
        String surname = nameParts.length > 1 ? nameParts[1] : "";

        try {
            return this.databaseService.getEmployeeGenericTimetable(name, surname);
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
            return this.databaseService.getEmployeeSpecificTimetable(name, surname, date);
        } catch(SQLException ex) {
            ex.printStackTrace();
            return new Vector<>();
        }
    }
}
