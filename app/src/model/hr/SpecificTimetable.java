package model.hr;

import java.sql.Date;
import java.sql.Time;

public class SpecificTimetable {
    private Date date;
    private Time startTime;
    private Time endTime;
    private String medicalUnitName;

    public SpecificTimetable(Date _date, Time _startTime, Time _endTime, String _medicalUnitName) {
        this.date = _date;
        this.startTime = _startTime;
        this.endTime = _endTime;
        this.medicalUnitName = _medicalUnitName;
    }

    public Date getDate() {
        return this.date;
    }

    public Time getStartTime() {
        return this.startTime;
    }

    public Time getEndTime() {
        return this.endTime;
    }

    public String getMedicalUnitName() {
        return this.medicalUnitName;
    }
}
