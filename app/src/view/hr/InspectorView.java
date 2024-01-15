package view.hr;

import model.hr.GenericTimetable;
import model.hr.SpecificTimetable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.time.Year;
import java.util.Arrays;
import java.util.Vector;

public class InspectorView extends JPanel {
    private JPanel userInput = new JPanel(new FlowLayout());
    private JComboBox<String> nameHolder;
    private JComboBox<String> rankHolder;
    private final JLabel yearLabel = new JLabel("Year");
    private JFormattedTextField yearHolder;
    private JComboBox<String> monthHolder;
    private JButton submitHolder;

    private Boolean isGenericTimetableSet = false;
    private JScrollPane genericTimetableScrollPane;
    private DefaultTableModel genericTimetableModel;
    private JTable genericTimetable;
    private Boolean isSpecificTimetableSet = false;
    private JScrollPane specificTimetableScrollPane;
    private DefaultTableModel specificTimetableModel;
    private JTable specificTimetable;
    public InspectorView() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void initNameHolder(Vector<String> _names) {
        this.nameHolder = new JComboBox<>(_names);
        this.nameHolder.setSelectedItem(_names.getFirst());
    }

    public void initRankHolder(Vector<String> _ranks) {
        this.rankHolder = new JComboBox<>(_ranks);
        this.rankHolder.setSelectedItem(_ranks.getFirst());
    }

    private void initYearHolder() {
        try {
            MaskFormatter formatter = new MaskFormatter("####");
            formatter.setValidCharacters("0123456789");

            this.yearHolder = new JFormattedTextField();
            this.yearHolder.setFormatterFactory(new DefaultFormatterFactory(formatter));
            this.yearHolder.setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT);
            this.yearHolder.setPreferredSize(new Dimension(50, 22));
            this.yearHolder.setHorizontalAlignment(JTextField.CENTER);
            this.yearHolder.setText(Integer.toString(Year.now().getValue()));

        } catch(ParseException e) {
            e.printStackTrace();
        }
    }

    private void initMonthHolder() {
        String[] months = {"Ianuarie", "Februarie", "Martie", "Aprilie", "Mai", "Iunie", "Iulie", "August", "Septembrie", "Octombrie", "Noiembrie", "Decembrie"};
        this.monthHolder = new JComboBox(months);
        this.monthHolder.setToolTipText("Pick a month");
    }

    private void initSubmitHolder() {
        this.submitHolder = new JButton();
        this.submitHolder.setPreferredSize(new Dimension(200, 22));
        this.submitHolder.setText("View schedule");
    }

    // style and add the components of the user input panel
    public void initUserInputPanel() {
        this.applyComboBoxStyles(this.nameHolder);
        this.userInput.add(this.nameHolder);

        this.applyComboBoxStyles(this.rankHolder);
        this.userInput.add(this.rankHolder);

        this.initYearHolder();
        this.userInput.add(this.yearLabel);
        this.userInput.add(this.yearHolder);

        this.initMonthHolder();
        this.applyComboBoxStyles(this.monthHolder);
        this.userInput.add(this.monthHolder);

        this.initSubmitHolder();
        this.userInput.add(this.submitHolder);
    }

    public void addUserInputToView() {
        this.add(this.userInput);
    }

    // styles a combo box
    private void applyComboBoxStyles(JComboBox<String> comboBox) {
        comboBox.setAlignmentX(FlowLayout.LEFT);
        comboBox.setPreferredSize(new Dimension(180, 22));
        comboBox.setBackground(Color.WHITE);
        comboBox.setForeground(Color.BLACK);
    }

    public JComboBox<String> getNameHolder() {
        return this.nameHolder;
    }

    public JComboBox<String> getRankHolder() {
        return this.rankHolder;
    }

    public JFormattedTextField getYearHolder() {
        return this.yearHolder;
    }

    public JComboBox<String> getMonthHolder() {
        return this.monthHolder;
    }

    public JButton getSubmitHolder() {
        return this.submitHolder;
    }

    public void initGenericTimetableTable(Vector<GenericTimetable> genericTimetable) {
        String[] columnNames = {"Zi", "Ora incepere", "Ora terminare", "Unitate medicala"};
        Vector<Vector<Object>> customTableData = new Vector<>();

        for (GenericTimetable genericTimetableEntry: genericTimetable) {
            Vector<Object> row = new Vector<>();

            row.add(genericTimetableEntry.getDay());
            row.add(genericTimetableEntry.getStartTime());
            row.add(genericTimetableEntry.getEndTime());
            row.add(genericTimetableEntry.getMedicalUnitName());

            customTableData.add(row);
        }

        if (!this.isGenericTimetableSet) {
            this.genericTimetableModel = new DefaultTableModel(customTableData, new Vector<>(Arrays.asList(columnNames))) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            this.genericTimetable = new JTable(this.genericTimetableModel);
            this.isGenericTimetableSet = true;
            this.genericTimetableScrollPane = new JScrollPane(this.genericTimetable);
            this.add(this.genericTimetableScrollPane);
            this.revalidate();
            this.repaint();
            return;
        }
        this.genericTimetableModel.setDataVector(customTableData, new Vector<>(Arrays.asList(columnNames)));
        this.revalidate();
        this.repaint();
    }

    public void initSpecificTimetableTable(Vector<SpecificTimetable> specificTimetable) {
        String[] columnNames = {"Data", "Ora incepere", "Ora terminare", "Unitate medicala"};
        Vector<Vector<Object>> customTableData = new Vector<>();

        for (SpecificTimetable specificTimetableEntry: specificTimetable) {
            Vector<Object> row = new Vector<>();

            row.add(specificTimetableEntry.getDate());
            row.add(specificTimetableEntry.getStartTime());
            row.add(specificTimetableEntry.getEndTime());
            row.add(specificTimetableEntry.getMedicalUnitName());

            customTableData.add(row);
        }

        if (!this.isSpecificTimetableSet) {
            this.specificTimetableModel = new DefaultTableModel(customTableData, new Vector<>(Arrays.asList(columnNames))) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            this.specificTimetable = new JTable(this.specificTimetableModel);
            this.isSpecificTimetableSet = true;
            this.specificTimetableScrollPane = new JScrollPane(this.specificTimetable);
            this.add(this.specificTimetableScrollPane);
            this.revalidate();
            this.repaint();
            return;
        }
        this.specificTimetableModel.setDataVector(customTableData, new Vector<>(Arrays.asList(columnNames)));
        this.revalidate();
        this.repaint();
    }
}
