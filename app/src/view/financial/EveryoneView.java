package view.financial;

import controller.financial.EveryoneController;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.time.Year;

public class EveryoneView extends JPanel {
    private JLabel errorMessage = new JLabel();
    private final JLabel yearHolderLabel = new JLabel("Year");
    protected JFormattedTextField yearHolder;

    private JPanel datePanel;
    private JPanel inputPanel;

    protected final JComboBox monthHolder;

    protected final JLabel earnings = new JLabel();

    protected final JButton submit = new JButton();
    public EveryoneView() {
        this.setLayout(new BorderLayout());

        this.datePanel = new JPanel(new FlowLayout());

        this.inputPanel = new JPanel();
        this.inputPanel.setLayout(new BoxLayout(this.inputPanel, BoxLayout.Y_AXIS));

        try {
            MaskFormatter formatter = new MaskFormatter("####");
            formatter.setValidCharacters("0123456789");

            this.yearHolder = new JFormattedTextField();
            this.yearHolder.setFormatterFactory(new DefaultFormatterFactory(formatter));
            this.yearHolder.setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT);
            this.yearHolder.setPreferredSize(new Dimension(50, 18));
            this.yearHolder.setHorizontalAlignment(JTextField.CENTER);
            this.yearHolder.setText(Integer.toString(Year.now().getValue()));

            datePanel.add(this.yearHolderLabel);
            datePanel.add(this.yearHolder);
        } catch(ParseException e) {
            this.showErrorMessage("Please enter a valid year");
            e.printStackTrace();
        }

        String[] months = {"Ianuarie", "Februarie", "Martie", "Aprilie", "Mai", "Iunie", "Iulie", "August", "Septembrie", "Octombrie", "Noiembrie", "Decembrie"};
        this.monthHolder = new JComboBox(months);

        this.monthHolder.setToolTipText("Pick a month");
        datePanel.add(BorderLayout.CENTER, this.monthHolder);
        this.inputPanel.add(this.datePanel);

        this.submit.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.submit.setText("View earnings");
        this.submit.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        this.inputPanel.add(this.submit);

        this.earnings.setText("Pick a date to view your salary");
        this.earnings.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        this.inputPanel.add(this.earnings);
        this.showEarnings();
        this.add(BorderLayout.CENTER, this.inputPanel);
    }

    public JComboBox getMonthHolder() {
        return this.monthHolder;
    }

    public JFormattedTextField getYearHolder() {
        return yearHolder;
    }

    public JButton getSubmitHolder() {
        return this.submit;
    }

    public JLabel getEarnings() {
        return earnings;
    }

    public JButton getSubmit() {
        return submit;
    }

    public void showErrorMessage(String _message) {
        this.errorMessage.setVisible(true);
        this.errorMessage.setText(_message);
        EveryoneController.setTimeout(this::hideErrorMessage, 2000);
    }

    public void hideErrorMessage() {
        this.errorMessage.setVisible(false);
    }

    public void showEarnings() {
        this.earnings.setVisible(true);
    }

    public void hideEarnings() {
        this.earnings.setVisible(false);
    }
}