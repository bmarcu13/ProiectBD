package model;

import java.sql.Date;

public class EmployeeInformation {
    private String cnp;
    private String departmentName;
    private String rankName;
    private String name;
    private String surname;
    private String address;
    private String phoneNumber;
    private String email;
    private String iban;
    private int contractNumber;
    private Date hireDate;
    private int salary;
    private int requiredHours;

    public EmployeeInformation(String _cnp, String _departmentName, String _rankName, String _name,
                               String _surname, String _address, String _phoneNumber, String _email,
                               String _iban, int _contractNumber, Date _hireDate, int _salary, int _requiredHours) {
        this.cnp = _cnp;
        this.departmentName = _departmentName;
        this.rankName = _rankName;
        this.name = _name;
        this.surname = _surname;
        this.address = _address;
        this.phoneNumber = _phoneNumber;
        this.email = _email;
        this.iban = _iban;
        this.contractNumber = _contractNumber;
        this.hireDate = _hireDate;
        this.salary = _salary;
        this.requiredHours = _requiredHours;
    }
    public String getCnp() {
        return cnp;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getRankName() {
        return rankName;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getIban() {
        return iban;
    }

    public int getContractNumber() {
        return contractNumber;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public int getSalary() {
        return salary;
    }

    public int getRequiredHours() {
        return requiredHours;
    }
}
