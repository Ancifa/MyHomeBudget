package budget.ui;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

/**
 * Created by 1 on 07.05.2017.
 */
public class DatePickerComponent {
    private LocalDate date;
    private static final String [] DAYS_OF_MONTH = {
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"
    };

    private static final String [] MONTHS = {
            "January", "February", "March", "April",
            "May", "June", "July", "August",
            "September", "October", "November", "December"
    };

    private static final String [] YEARS = {"2017", "2018"};

    public Box createComponent(Dimension dimension) {
        Box dateBox = Box.createHorizontalBox();
        date = date.now();

        JLabel dateFieldLabel = new JLabel("Date");
        dateFieldLabel.setPreferredSize(dimension);

        JComboBox dateField = new JComboBox(DAYS_OF_MONTH);
        dateField.setMaximumSize(new Dimension(15, 22));
        dateField.setSelectedIndex(date.getDayOfMonth() - 1);

        JComboBox monthField = new JComboBox(MONTHS);
        monthField.setMaximumSize(new Dimension(35, 22));
        monthField.setSelectedIndex(date.getMonthValue() - 1);

        JComboBox yearField = new JComboBox(YEARS);
        yearField.setMaximumSize(new Dimension(15, 22));
        yearField.setSelectedIndex(date.getYear() == 2017 ? 0 : 1);

        dateBox.add(dateFieldLabel);
        dateBox.add(Box.createHorizontalStrut(6));
        dateBox.add(dateField);
        dateBox.add(monthField);
        dateBox.add(yearField);
        dateBox.add(Box.createHorizontalGlue());

        return dateBox;
    }

}