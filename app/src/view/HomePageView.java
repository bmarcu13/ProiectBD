package view;

import model.EmployeeInformation;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class HomePageView extends JPanel {
    JTextField cnp;
    JTextField departmentName;
    JTextField rankName;
    JTextField name;
    JTextField surname;
    JTextField address;
    JTextField phoneNumber;
    JTextField email;
    JTextField iban;
    JTextField contractNumber;
    JFormattedTextField hireDate;
    JTextField salary;
    JTextField requiredHours;

    public HomePageView() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void initEmployeeInformation(EmployeeInformation _employeeInformation) {
        this.cnp = new JTextField(_employeeInformation.getCnp());
        this.cnp.setEditable(false);

        this.add(createLabelAndField("CNP", this.cnp));

        this.departmentName = new JTextField(_employeeInformation.getDepartmentName());
        this.departmentName.setEditable(false);

        this.add(createLabelAndField("Departament", this.departmentName));

        this.rankName = new JTextField(_employeeInformation.getRankName());
        this.rankName.setEditable(false);

        this.add(createLabelAndField("Functie", this.rankName));

        this.name = new JTextField(_employeeInformation.getName());
        this.name.setEditable(false);

        this.add(createLabelAndField("Nume", this.name));

        this.surname = new JTextField(_employeeInformation.getSurname());
        this.surname.setEditable(false);

        this.add(createLabelAndField("Prenume", this.surname));

        this.address = new JTextField(_employeeInformation.getAddress());
        this.address.setEditable(false);

        this.add(createLabelAndField("Adresa", this.address));

        this.phoneNumber = new JTextField(_employeeInformation.getPhoneNumber());
        this.phoneNumber.setEditable(false);

        this.add(createLabelAndField("Numar de telefon", this.phoneNumber));

        this.email = new JTextField(_employeeInformation.getEmail());
        this.email.setEditable(false);

        this.add(createLabelAndField("Email", this.email));

        this.iban = new JTextField(_employeeInformation.getIban());
        this.iban.setEditable(false);

        this.add(createLabelAndField("IBAN", this.iban));

        this.contractNumber = new JTextField(String.valueOf(_employeeInformation.getContractNumber()));
        this.contractNumber.setEditable(false);

        this.add(createLabelAndField("Numar de contract", this.contractNumber));

        try {
            MaskFormatter dateMask = new MaskFormatter("####-##-##");
            dateMask.setPlaceholderCharacter('_');
            this.hireDate = new JFormattedTextField(dateMask);
            this.hireDate.setValue(_employeeInformation.getHireDate());
            this.hireDate.setEditable(false);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.add(createLabelAndField("Data angajarii", this.hireDate));

        this.salary = new JTextField(String.valueOf(_employeeInformation.getSalary()));
        this.salary.setEditable(false);

        this.add(createLabelAndField("Salariu", this.salary));

        this.requiredHours = new JTextField(String.valueOf(_employeeInformation.getRequiredHours()));
        this.requiredHours.setEditable(false);

        this.add(createLabelAndField("Numar de ore necesar", this.requiredHours));
    }

    private JPanel createLabelAndField(String label, JTextField field) {
        JPanel panel = new JPanel(new FlowLayout());
        JLabel jLabel = new JLabel(label);
        panel.add(jLabel);
        panel.add(field);
        return panel;
    }
}
