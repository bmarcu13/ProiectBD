package view.financial;

import javax.swing.*;
import java.awt.*;

public class MedicView extends JPanel {
    EveryoneView everyoneView;
    private final JComboBox monthHolder;
    private final MedicProfitsView medicProfitsView;
    private final JPanel viewProfits = new JPanel();
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
        String[] columnNames = {"Unitate medicala", "ID unitate", "Venit generat", "Salariu", "Profit generat"};

        JTable table = new JTable(tableData, columnNames);

        JScrollPane tableScrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

    }

    public MedicProfitsView getMedicProfitsView() {
        return this.medicProfitsView;
    }
}