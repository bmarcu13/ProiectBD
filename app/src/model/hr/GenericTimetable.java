package model.hr;

import java.sql.Time;

public class GenericTimetable {
    private String day;
    private Time startTime;
    private Time endTime;
    private String medicalUnitName;

    public GenericTimetable(String _day, Time _startTime, Time _endTime, String _medicalUnitName) {
        this.day = _day;
        this.startTime = _startTime;
        this.endTime = _endTime;
        this.medicalUnitName = _medicalUnitName;
    }

    public String getDay() {
        return day;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public String getMedicalUnitName() {
        return medicalUnitName;
    }
}
