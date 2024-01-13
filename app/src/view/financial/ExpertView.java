package view.financial;

import controller.financial.EveryoneController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.util.Arrays;

public class ExpertView extends JPanel {
    private EveryoneView everyoneView;

    private boolean setTable = false;
    private final JPanel viewUnitProfits = new JPanel();
    private final JPanel inputPanel = new JPanel();
    private final JPanel datePanel = new JPanel(new FlowLayout());
    private final JComboBox<String> monthHolder;
    private final JLabel yearHolderLabel = new JLabel("Year");
    private final JFormattedTextField yearHolder = new JFormattedTextField();
    private final JButton submit = new JButton();
    private final JLabel errorMessage = new JLabel();

    private final BoxLayout viewUnitProfitsLayout = new BoxLayout(this.viewUnitProfits, BoxLayout.Y_AXIS);

    DefaultTableModel model;
    JTable table;
    JScrollPane tableScrollPane;
    public ExpertView(EveryoneView _everyoneView) {
        this.everyoneView = _everyoneView;
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
            formatter.setPlaceholderCharacter('_');

            this.yearHolder.setFormatterFactory(new DefaultFormatterFactory(formatter));
            this.yearHolder.setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT);
            this.yearHolder.setPreferredSize(new Dimension(50, 18));
            this.yearHolder.setHorizontalAlignment(JTextField.CENTER);

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

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        this.add(this.inputPanel, gbc);
    }

    public void initTable(Object[][] tableData) {
        String[] columnNames = {"Unitate medicala", "ID unitate", "Venituri", "Cheltuieli", "Profit"};
        if (!this.setTable) {
            this.model = new DefaultTableModel(tableData, columnNames);
            this.table = new JTable(this.model);
            this.setTable = true;
            this.tableScrollPane = new JScrollPane(table);
            this.tableScrollPane.setPreferredSize(new Dimension(500, 300));
            this.viewUnitProfits.add(this.tableScrollPane);
            this.viewUnitProfits.revalidate();
            this.viewUnitProfits.repaint();
        }

        this.model.setDataVector(tableData, columnNames);
        System.out.println("Here " + Arrays.deepToString(tableData));

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

    public void showErrorMessage(String _message) {
        this.errorMessage.setVisible(true);
        this.errorMessage.setText(_message);
        EveryoneController.setTimeout(this::hideErrorMessage, 2000);
    }

    public void hideErrorMessage() {
        this.errorMessage.setVisible(false);
    }

}