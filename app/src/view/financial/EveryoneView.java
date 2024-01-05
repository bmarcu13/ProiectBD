package view.financial;

import controller.financial.EveryoneController;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class EveryoneView extends JPanel {
    private JLabel errorMessage = new JLabel();
    private final JLabel cnpHolderLabel = new JLabel("CNP");
    private JFormattedTextField cnpHolder;
    private final JLabel yearHolderLabel = new JLabel("Year");
    private JFormattedTextField yearHolder;

    private JPanel datePanel;
    private JPanel cnpPanel;
    private JPanel inputPanel;

    private JComboBox monthHolder;

    private final JLabel earnings = new JLabel();

    private final JButton submit = new JButton();
    public EveryoneView() {
        this.setLayout(new BorderLayout());

        this.datePanel = new JPanel(new FlowLayout());
        this.cnpPanel = new JPanel(new FlowLayout());
        
        this.inputPanel = new JPanel();
        this.inputPanel.setLayout(new BoxLayout(this.inputPanel, BoxLayout.Y_AXIS));

        try {
            MaskFormatter formatter = new MaskFormatter("####");
            formatter.setValidCharacters("0123456789");
            formatter.setPlaceholderCharacter('_');

            this.yearHolder = new JFormattedTextField();
            this.yearHolder.setFormatterFactory(new DefaultFormatterFactory(formatter));
            this.yearHolder.setFocusLostBehavior(JFormattedTextField.REVERT);
            this.yearHolder.setPreferredSize(new Dimension(50, 18));
            this.yearHolder.setHorizontalAlignment(JTextField.CENTER);

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

        try {
            MaskFormatter formatter = new MaskFormatter("#############");
            formatter.setValidCharacters("0123456789");
            formatter.setPlaceholderCharacter('_');

            this.cnpHolder = new JFormattedTextField();
            this.cnpHolder.setFormatterFactory(new DefaultFormatterFactory(formatter));
            this.cnpHolder.setFocusLostBehavior(JFormattedTextField.COMMIT);
            this.cnpHolder.setPreferredSize(new Dimension(100, 20));
            this.cnpHolder.setHorizontalAlignment(JTextField.CENTER);
            inputPanel.add(Box.createVerticalGlue());

            this.cnpPanel.add(this.cnpHolderLabel);
            this.cnpPanel.add(this.cnpHolder);
            this.inputPanel.add(this.cnpPanel);
        } catch(ParseException e) {
            this.showErrorMessage("Please enter a valid cnp");
            e.printStackTrace();
        }

        this.submit.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.submit.setText("View earnings");
        this.submit.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        this.inputPanel.add(this.submit);

        this.earnings.setText("Just testing");
        this.earnings.setAlignmentX(JPanel.CENTER_ALIGNMENT);
//        this.earnings.setHorizontalAlignment(JTextField.CENTER);
        this.inputPanel.add(this.earnings);
        this.showEarnings();
        this.add(BorderLayout.CENTER, this.inputPanel);
    }

    public JComboBox getMonthHolder() {
        return this.monthHolder;
    }

    public JFormattedTextField getCnpHolder() {
        return cnpHolder;
    }

    public JFormattedTextField getYearHolder() {
        return yearHolder;
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