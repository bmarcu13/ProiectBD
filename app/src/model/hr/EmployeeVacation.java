package model.hr;

import java.sql.Date;

public class EmployeeVacation {
    private Date startDate;
    private Date endDate;
    public EmployeeVacation(Date _startDate, Date _endDate) {
        this.startDate = _startDate;
        this.endDate = _endDate;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }
}
