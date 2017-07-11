package budget.MainWindow;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 1 on 18.04.2017.
 */
public class InfoBlock extends JFrame {

    public Box createInfoBoxLayout() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date today = new Date();

        Box mainBox = Box.createVerticalBox();

        JLabel totalBalanceLabel = new JLabel("Total balance:     ");
        JLabel totalBalanceValue = new JLabel("0.00 RUB");

//        JLabel dateLabel = new JLabel("Today is:");
//        dateLabel.setPreferredSize(totalBalanceLabel.getPreferredSize());
//        JLabel dateValue = new JLabel(dateFormat.format(today));

        JLabel cashBalanceLabel = new JLabel("In cash:");
        cashBalanceLabel.setPreferredSize(totalBalanceLabel.getPreferredSize());
        JLabel cashBalanceValue = new JLabel("0.00 RUB");

        JLabel cardBalanceLabel = new JLabel("On cards:");
        cardBalanceLabel.setPreferredSize(totalBalanceLabel.getPreferredSize());
        JLabel cardBalanceValue = new JLabel("0.00 RUB");

        JLabel depositBalanceLabel = new JLabel("On deposit:");
        depositBalanceLabel.setPreferredSize(totalBalanceLabel.getPreferredSize());
        JLabel depositBalanceValue = new JLabel("0.00 RUB");

//        Box dateRow = Box.createHorizontalBox();
        Box firstRow = Box.createHorizontalBox();
        Box secondRow = Box.createHorizontalBox();
        Box thirdRow = Box.createHorizontalBox();
        Box fourthRow = Box.createHorizontalBox();

//        dateRow.add(dateLabel);
//        dateRow.add(dateValue);
//        dateRow.add(Box.createHorizontalGlue());
        firstRow.add(totalBalanceLabel);
        firstRow.add(totalBalanceValue);
        firstRow.add(Box.createHorizontalGlue());
        secondRow.add(cashBalanceLabel);
        secondRow.add(cashBalanceValue);
        secondRow.add(Box.createHorizontalGlue());
        thirdRow.add(cardBalanceLabel);
        thirdRow.add(cardBalanceValue);
        thirdRow.add(Box.createHorizontalGlue());
        fourthRow.add(depositBalanceLabel);
        fourthRow.add(depositBalanceValue);
        fourthRow.add(Box.createHorizontalGlue());

        mainBox.setBorder(new EmptyBorder(12, 12, 12, 12));
//        mainBox.add(dateRow);
//        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(firstRow);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(secondRow);
        mainBox.add(Box.createVerticalStrut(6));
        mainBox.add(thirdRow);
        mainBox.add(Box.createVerticalStrut(6));
        mainBox.add(fourthRow);

        return mainBox;
    }
}