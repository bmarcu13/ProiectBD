package view.financial;

import controller.financial.EveryoneController;
import model.financial.MedicProfitData;
import model.financial.MedicalUnitProfitData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class ExpertView extends JPanel {
    private EveryoneView everyoneView;
    private JPanel expertFunctionalityContainer = new JPanel(new FlowLayout());
    private boolean setTable = false;
    private final JPanel viewUnitProfits = new JPanel();
    private final JPanel inputPanel = new JPanel();
    private final JPanel datePanel = new JPanel(new FlowLayout());
    private final JComboBox<String> monthHolder;
    private final JLabel yearHolderLabel = new JLabel("Year");
    private final JFormattedTextField yearHolder = new JFormattedTextField();
    private final JButton submit = new JButton();
    private final JLabel errorMessage = new JLabel();
    private final JPanel dateProfitsPanel = new JPanel(new FlowLayout());
    private final JLabel yearProfitsHolderLabel = new JLabel("Year");
    private final JFormattedTextField medicProfitsYearHolder = new JFormattedTextField();
    private final JComboBox<String> medicProfitsMonthHolder;

    private boolean setProfitsTable = false;
    private JComboBox<String> nameHolder;
    private JPanel viewMedicProfitsContainer = new JPanel();
    private JButton viewMedicProfits = new JButton();
    private JLabel medicProfits = new JLabel("");
    DefaultTableModel model;
    JTable table;
    JScrollPane tableScrollPane;

    DefaultTableModel profitsModel;
    JTable profitsTable;
    JScrollPane profitsTableScrollPane;
    public ExpertView(EveryoneView _everyoneView) {
        this.everyoneView = _everyoneView;
        this.viewUnitProfits.setLayout(new BoxLayout(this.viewUnitProfits, BoxLayout.Y_AXIS));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        this.add(this.everyoneView, gbc);

        this.setLayout(new GridBagLayout());

        try {
            MaskFormatter formatter = new MaskFormatter("####");
            formatter.setValidCharacters("0123456789");

            this.yearHolder.setFormatterFactory(new DefaultFormatterFactory(formatter));
            this.yearHolder.setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT);
            this.yearHolder.setPreferredSize(new Dimension(50, 18));
            this.yearHolder.setHorizontalAlignment(JTextField.CENTER);
            this.yearHolder.setText(Integer.toString(Year.now().getValue()));

            this.datePanel.add(this.yearHolderLabel);
            this.datePanel.add(this.yearHolder);
        } catch(ParseException e) {
            this.showErrorMessage("Please enter a valid year");
            e.printStackTrace();
        }

        String[] months = {"Ianuarie", "Februarie", "Martie", "Aprilie", "Mai", "Iunie", "Iulie", "August", "Septembrie", "Octombrie", "Noiembrie", "Decembrie"};
        this.monthHolder = new JComboBox(months);
        this.monthHolder.setToolTipText("Pick a month");
        datePanel.add(this.monthHolder);

        this.inputPanel.setLayout(new BoxLayout(this.inputPanel, BoxLayout.Y_AXIS));
        this.inputPanel.add(this.datePanel);

        this.submit.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.submit.setText("View medical units profits");
        this.submit.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        this.inputPanel.add(this.submit);

//        gbc.gridx = 1;
//        gbc.gridy = 0;
//        gbc.gridwidth = 1;
//        gbc.gridheight = 1;
        this.viewUnitProfits.add(this.inputPanel);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        this.expertFunctionalityContainer.add(this.viewUnitProfits);
        this.add(this.expertFunctionalityContainer, gbc);
        try {
            MaskFormatter formatter = new MaskFormatter("####");
            formatter.setValidCharacters("0123456789");
            formatter.setPlaceholderCharacter('_');

            this.medicProfitsYearHolder.setFormatterFactory(new DefaultFormatterFactory(formatter));
            this.medicProfitsYearHolder.setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT);
            this.medicProfitsYearHolder.setPreferredSize(new Dimension(50, 18));
            this.medicProfitsYearHolder.setHorizontalAlignment(JTextField.CENTER);

        } catch(ParseException e) {
            this.showErrorMessage("Please enter a valid year");
            e.printStackTrace();
        }

        this.medicProfitsMonthHolder = new JComboBox<>(months);
    }

    public void initTable(Vector<MedicalUnitProfitData> tableData) {
        String[] columnNames = {"Unitate medicala", "ID unitate", "Venituri", "Cheltuieli", "Profit"};
        Vector<Vector<Object>> customTableData = new Vector<>();
        for (MedicalUnitProfitData medicalUnitProfitData: tableData) {
            Vector<Object> row = new Vector<>();
            row.add(medicalUnitProfitData.getId());
            row.add(medicalUnitProfitData.getName());
            row.add(medicalUnitProfitData.getIncome());
            row.add(medicalUnitProfitData.getExpenses());
            row.add(medicalUnitProfitData.getProfit());

            customTableData.add(row);
        }
        if (!this.setTable) {
            this.model = new DefaultTableModel(customTableData, new Vector<>(Arrays.asList(columnNames))) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            this.table = new JTable(this.model);
            this.setTable = true;
            this.tableScrollPane = new JScrollPane(table);
//            this.tableScrollPane.setPreferredSize(new Dimension(500, 300));
            this.viewUnitProfits.add(this.tableScrollPane);
            this.viewUnitProfits.revalidate();
            this.viewUnitProfits.repaint();
        }

        this.model.setDataVector(customTableData, new Vector<>(Arrays.asList(columnNames)));

        this.tableScrollPane.revalidate();
        this.tableScrollPane.repaint();
    }

    public JComboBox getMonthHolder() {
        return this.monthHolder;
    }

    public JFormattedTextField getYearHolder() {
        return this.yearHolder;
    }

    public JButton getSubmitHolder() {
        return this.submit;
    }
    public JComboBox<String> getNameHolder() {
        return this.nameHolder;
    }

    public void setNameHolderNames(Vector<String> medicNames) {
        this.nameHolder = new JComboBox<>(medicNames);
    }

    public JButton getViewMedicProfits() {
        return this.viewMedicProfits;
    }

    public void setMedicProfitsText(String _text) {
        this.setMedicProfitsText(_text);
    }
    public JFormattedTextField getMedicProfitsYearHolder() {
        return this.medicProfitsYearHolder;
    }

    public JComboBox<String> getMedicProfitsMonthHolder() {
        return this.medicProfitsMonthHolder;
    }

    public void addNameHolderToView() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        this.viewMedicProfitsContainer.setLayout(new BoxLayout(this.viewMedicProfitsContainer, BoxLayout.Y_AXIS));
        this.nameHolder.setAlignmentX(CENTER_ALIGNMENT);
        this.viewMedicProfits.setAlignmentX(CENTER_ALIGNMENT);
        this.medicProfits.setAlignmentX(CENTER_ALIGNMENT);
        this.viewMedicProfits.setText("View medic profit");
        this.medicProfits.setPreferredSize(new Dimension(200, 17));

        this.viewMedicProfitsContainer.add(this.nameHolder);

        this.dateProfitsPanel.add(this.yearProfitsHolderLabel);
        this.dateProfitsPanel.add(this.medicProfitsYearHolder);
        this.dateProfitsPanel.add(this.medicProfitsMonthHolder);

        this.viewMedicProfitsContainer.add(this.dateProfitsPanel);

        this.viewMedicProfitsContainer.add(this.viewMedicProfits);
        this.viewMedicProfitsContainer.add(this.medicProfits);
        this.expertFunctionalityContainer.add(this.viewMedicProfitsContainer);
    }

    public void initProfitsTable(Vector<MedicProfitData> tableData) {
        String[] columnNames = {"Unitate medicala", "ID unitate", "Profit medic"};
        Vector<Vector<Object>> customTableData = new Vector<>();
        for (MedicProfitData medicProfitData: tableData) {
            Vector<Object> row = new Vector<>();
            row.add(medicProfitData.getUnitId());
            row.add(medicProfitData.getUnitName());
            row.add(medicProfitData.getMedicProfit());

            customTableData.add(row);
        }
        if (!this.setProfitsTable) {
            this.profitsModel = new DefaultTableModel(customTableData, new Vector<>(Arrays.asList(columnNames))) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            this.profitsTable = new JTable(this.profitsModel);
            this.setProfitsTable = true;
            this.profitsTableScrollPane = new JScrollPane(this.profitsTable);
            this.viewMedicProfitsContainer.add(this.profitsTableScrollPane);
            this.viewMedicProfitsContainer.revalidate();
            this.viewMedicProfitsContainer.repaint();
            return;
        }

        this.profitsModel.setDataVector(customTableData, new Vector<>(Arrays.asList(columnNames)));

        this.profitsTableScrollPane.revalidate();
        this.profitsTableScrollPane.repaint();
    }

    public void showErrorMessage(String _message) {
        this.errorMessage.setVisible(true);
        this.errorMessage.setText(_message);
        EveryoneController.setTimeout(this::hideErrorMessage, 2000);
    }

    public void hideErrorMessage() {
        this.errorMessage.setVisible(false);
    }

}