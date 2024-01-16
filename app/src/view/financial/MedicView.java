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
    private final BoxLayout viewProfitsLayout = new BoxLayout(this.viewProfits, BoxLayout.Y_AXIS);

    DefaultTableModel model;
    JTable table;
    JScrollPane tableScrollPane;
    public MedicView(EveryoneView _everyoneView) {
        this.everyoneView = _everyoneView;
        this.medicProfitsView = new MedicProfitsView();

        this.viewProfits.setLayout(this.viewProfitsLayout);

        this.add(this.everyoneView);

        String[] months = {"Ianuarie", "Februarie", "Martie", "Aprilie", "Mai", "Iunie", "Iulie", "August", "Septembrie", "Octombrie", "Noiembrie", "Decembrie"};
        this.monthHolder = new JComboBox(months);

        this.monthHolder.setToolTipText("Pick a month");

        this.viewProfits.add(this.medicProfitsView);
        this.add(this.viewProfits);
    }

    public void initTable(Object[][] tableData) {
        String[] columnNames = {"Unitate medicala", "ID unitate", "Venit generat", "Salariu", "Comision", "Profit generat"};
        if (!this.setTable) {
            this.model = new DefaultTableModel(tableData, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            this.table = new JTable(this.model);
            this.setTable = true;
            this.tableScrollPane = new JScrollPane(table);
            this.tableScrollPane.setPreferredSize(new Dimension(500, 300));
            this.viewProfits.add(this.tableScrollPane);
            this.viewProfits.revalidate();
            this.viewProfits.repaint();
        }

        this.model.setDataVector(tableData, columnNames);
        System.out.println("Here " + Arrays.deepToString(tableData));

        this.tableScrollPane.revalidate();
        this.tableScrollPane.repaint();
    }

    public MedicProfitsView getMedicProfitsView() {
        return this.medicProfitsView;
    }
}