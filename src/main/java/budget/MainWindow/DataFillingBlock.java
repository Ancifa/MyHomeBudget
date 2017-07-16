package budget.MainWindow;

import budget.db.DbConstants;
import budget.ui.DatePickerComponent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by 1 on 21.04.2017.
 */
public class DataFillingBlock extends JFrame {
    private JLabel lastEntryValue;
    private DatePickerComponent datePicker;
    private JComboBox actionTypeField;
    private JComboBox expenceCategoryField;
    private JComboBox actionCurrencyField;
    private JComboBox actionMethodField;
    private JTextField summField;
    private JTextArea actionDescriptionArea;
    private JButton inputButton;

    private String userLogin;
    private DataFillingController dataFillingController;

    public DataFillingBlock(String userLogin) {
        this.userLogin = userLogin;
    }

    public Box createDataFillingBlock() {
        dataFillingController = new DataFillingController(this);
        DbConstants dbConstants = new DbConstants();

        Box mainBox = Box.createVerticalBox();

        Box lastEntryBox = Box.createHorizontalBox();
        Box dateBox = Box.createHorizontalBox();
        Box actionTypeBox = Box.createHorizontalBox();
        Box actionDescriptionBox = Box.createHorizontalBox();
        Box expenceCategoryBox = Box.createHorizontalBox();
        Box actionSummBox = Box.createHorizontalBox();
        Box actionMethodBox = Box.createHorizontalBox();
        Box actionCurrencyBox = Box.createHorizontalBox();
        Box buttonsBox = Box.createHorizontalBox();

        JLabel expenceCategoryLabel = new JLabel("Expence category");
        DbConstants.ExpenceCategory[] expenceCategories = dbConstants.getExpenceCategories();
        expenceCategoryField = new JComboBox(expenceCategories);
        expenceCategoryField.setSelectedIndex(0);
        expenceCategoryField.setMaximumSize(new Dimension(30, 22));
        expenceCategoryBox.add(expenceCategoryLabel);
        expenceCategoryBox.add(Box.createHorizontalStrut(6));
        expenceCategoryBox.add(expenceCategoryField);
        expenceCategoryBox.add(Box.createHorizontalGlue());

        JLabel lastEntryLabel = new JLabel("Last entry date:");
        lastEntryLabel.setPreferredSize(expenceCategoryLabel.getPreferredSize());
        lastEntryValue = new JLabel();
        lastEntryValue.setText("-");
//        lastEntryValue.setText(format.format(date));
        lastEntryBox.add(lastEntryLabel);
        lastEntryBox.add(Box.createHorizontalStrut(6));
        lastEntryBox.add(lastEntryValue);
        lastEntryBox.add(Box.createHorizontalGlue());

        JLabel actionDescriptionLabel = new JLabel("Description");
        actionDescriptionLabel.setPreferredSize(expenceCategoryLabel.getPreferredSize());
        actionDescriptionArea = new JTextArea(1, 15);
        actionDescriptionArea.setLineWrap(true);
        actionDescriptionArea.setWrapStyleWord(true);
        actionDescriptionArea.setBorder(new LineBorder(Color.GRAY, 1));
        actionDescriptionArea.setMaximumSize(new Dimension(30, 55));
        actionDescriptionBox.add(actionDescriptionLabel);
        actionDescriptionBox.add(Box.createHorizontalStrut(6));
        actionDescriptionBox.add(actionDescriptionArea);
        actionDescriptionBox.add(Box.createHorizontalGlue());

        datePicker = new DatePickerComponent();
        dateBox.add(datePicker.createComponent(expenceCategoryLabel.getPreferredSize()));
//        dateBox.add(Box.createRigidArea(new Dimension(36, 1)));

        JLabel actionTypeLabel = new JLabel("Type");
        actionTypeLabel.setPreferredSize(expenceCategoryLabel.getPreferredSize());
        DbConstants.ActionType[] actionTypes = dbConstants.getActionTypes();
        actionTypeField = new JComboBox(actionTypes);
        actionTypeField.setSelectedIndex(1);
        actionTypeField.setMaximumSize(new Dimension(30, 22));
        actionTypeBox.add(actionTypeLabel);
        actionTypeBox.add(Box.createHorizontalStrut(6));
        actionTypeBox.add(actionTypeField);
        actionTypeBox.add(Box.createHorizontalGlue());

        JLabel summFieldLabel = new JLabel("Summ");
        summFieldLabel.setPreferredSize(expenceCategoryLabel.getPreferredSize());
        summField = new JTextField(15);
        summField.setMaximumSize(new Dimension(30, 22));
        actionSummBox.add(summFieldLabel);
        actionSummBox.add(Box.createHorizontalStrut(6));
        actionSummBox.add(summField);
        actionSummBox.add(Box.createHorizontalGlue());

        JLabel actionMethodLabel = new JLabel("Method");
        actionMethodLabel.setPreferredSize(expenceCategoryLabel.getPreferredSize());
        DbConstants.ActionMethod[] actionMethods = dbConstants.getActionMethods();
        actionMethodField = new JComboBox(actionMethods);
        actionMethodField.setSelectedIndex(0);
        actionMethodField.setMaximumSize(new Dimension(30, 22));
        actionMethodBox.add(actionMethodLabel);
        actionMethodBox.add(Box.createHorizontalStrut(6));
        actionMethodBox.add(actionMethodField);
        actionMethodBox.add(Box.createHorizontalGlue());

        JLabel actionCurrencyLabel = new JLabel("Currency");
        actionCurrencyLabel.setPreferredSize(expenceCategoryLabel.getPreferredSize());
        DbConstants.Currency[] currencies = dbConstants.getCurrencies();
        actionCurrencyField = new JComboBox(currencies);
        actionCurrencyField.setSelectedIndex(0);
        actionCurrencyField.setMaximumSize(new Dimension(30, 22));
        actionCurrencyBox.add(actionCurrencyLabel);
        actionCurrencyBox.add(Box.createHorizontalStrut(6));
        actionCurrencyBox.add(actionCurrencyField);
        actionCurrencyBox.add(Box.createHorizontalGlue());

        JButton clearAllButton = new JButton("Clear all");

        inputButton = new JButton("Input");
        setInputButtonListener();

        JButton closeButton = new JButton("Close");
//        buttonsBox.add(clearAllButton);
//        buttonsBox.add(Box.createHorizontalStrut(12));
//        buttonsBox.add(Box.createHorizontalStrut(36));
//        buttonsBox.add(closeButton);
        buttonsBox.add(Box.createHorizontalGlue());
        buttonsBox.add(inputButton);
        buttonsBox.add(Box.createRigidArea(new Dimension(23, 1)));

        mainBox.setBorder(new EmptyBorder(12, 12, 12, 12));
        mainBox.add(lastEntryBox);
        mainBox.add(Box.createVerticalStrut(3));
        mainBox.add(dateBox);
        mainBox.add(Box.createVerticalStrut(3));
        mainBox.add(actionTypeBox);
        mainBox.add(Box.createVerticalStrut(6));
        mainBox.add(expenceCategoryBox);
        mainBox.add(Box.createVerticalStrut(6));
        mainBox.add(actionCurrencyBox);
        mainBox.add(Box.createVerticalStrut(6));
        mainBox.add(actionMethodBox);
        mainBox.add(Box.createVerticalStrut(6));
        mainBox.add(actionSummBox);
        mainBox.add(Box.createVerticalStrut(6));
        mainBox.add(actionDescriptionBox);
        mainBox.add(Box.createVerticalStrut(24));
        mainBox.add(buttonsBox);
        mainBox.add(Box.createRigidArea(new Dimension(1, 150)));
        mainBox.add(Box.createVerticalGlue());

        return mainBox;
    }

    private void setInputButtonListener() {
        inputButton.addActionListener(e -> {
            dataFillingController.gatherData();
            dataFillingController.saveToDatabase(userLogin);
            summField.setText("");
            actionDescriptionArea.setText("");
                }
        );
    }

    public JLabel getLastEntryValue() {
        return lastEntryValue;
    }

    public DatePickerComponent getDatePicker() {
        return datePicker;
    }

    public JComboBox getActionTypeField() {
        return actionTypeField;
    }

    public JComboBox getExpenceCategoryField() {
        return expenceCategoryField;
    }

    public JComboBox getActionCurrencyField() {
        return actionCurrencyField;
    }

    public JComboBox getActionMethodField() {
        return actionMethodField;
    }

    public JTextField getSummField() {
        return summField;
    }

    public JTextArea getActionDescriptionArea() {
        return actionDescriptionArea;
    }
}
