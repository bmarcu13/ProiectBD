package view.financial;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;

public class MedicView extends JPanel {
    private boolean setTable = false;
    EveryoneView everyoneView;
    private final JComboBox monthHolder;
    private final MedicProfitsView medicProfitsView;
    private final JPanel viewProfits = new JPanel();

    DefaultTableModel model;
    JTable table;
    JScrollPane tableScrollPane;
    public MedicView(EveryoneView _everyoneView) {
        this.everyoneView = _everyoneView;
        this.medicProfitsView = new MedicProfitsView();

        this.add(this.everyoneView);

        String[] months = {"Ianuarie", "Februarie", "Martie", "Aprilie", "Mai", "Iunie", "Iulie", "August", "Septembrie", "Octombrie", "Noiembrie", "Decembrie"};
        this.monthHolder = new JComboBox(months);

        this.monthHolder.setToolTipText("Pick a month");

        this.viewProfits.add(this.medicProfitsView);
        this.add(this.viewProfits);
    }

    public void initTable(Object[][] tableData) {
        if (this.setTable) {
            this.viewProfits.remove(this.tableScrollPane);
        }
        this.setTable = true;
        String[] columnNames = {"Unitate medicala", "ID unitate", "Venit generat", "Salariu", "Profit generat"};

        this.model = new DefaultTableModel(tableData, columnNames);
        this.table = new JTable(this.model);

        System.out.println("Here " + Arrays.deepToString(tableData));

        this.tableScrollPane = new JScrollPane(table);
        this.tableScrollPane.setPreferredSize(new Dimension(500, 300));
        this.tableScrollPane.setBackground(Color.blue);

        this.viewProfits.add(this.tableScrollPane);

    }

    public MedicProfitsView getMedicProfitsView() {
        return this.medicProfitsView;
    }
}