package budget.MainWindow;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Created by 1 on 18.04.2017.
 */
public class InfoBlock extends JFrame {
    private JLabel totalBalanceValue;
    private JLabel cashBalanceValue;
    private JLabel cardBalanceValue;
    private JLabel depositBalanceValue;

    private InfoBlockController controller;

    public Box createInfoBoxLayout(InfoBlockController controller) {
        this.controller = controller;
        Box box = buildLayout();
        controller.mountData(this);

        return box;
    }

    private Box buildLayout() {

        Box mainBox = Box.createVerticalBox();

        JLabel totalBalanceLabel = new JLabel("Total balance:     ");
        totalBalanceValue = new JLabel("-");

        JLabel cashBalanceLabel = new JLabel("In cash:");
        cashBalanceLabel.setPreferredSize(totalBalanceLabel.getPreferredSize());
        cashBalanceValue = new JLabel("-");

        JLabel cardBalanceLabel = new JLabel("On cards:");
        cardBalanceLabel.setPreferredSize(totalBalanceLabel.getPreferredSize());
        cardBalanceValue = new JLabel("-");

        JLabel depositBalanceLabel = new JLabel("On deposit:");
        depositBalanceLabel.setPreferredSize(totalBalanceLabel.getPreferredSize());
        depositBalanceValue = new JLabel("-");

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

    public JLabel getTotalBalanceValue() {
        return totalBalanceValue;
    }

    public void setTotalBalanceValue(JLabel totalBalanceValue) {
        this.totalBalanceValue = totalBalanceValue;
    }

    public JLabel getCashBalanceValue() {
        return cashBalanceValue;
    }

    public void setCashBalanceValue(JLabel cashBalanceValue) {
        this.cashBalanceValue = cashBalanceValue;
    }

    public JLabel getCardBalanceValue() {
        return cardBalanceValue;
    }

    public void setCardBalanceValue(JLabel cardBalanceValue) {
        this.cardBalanceValue = cardBalanceValue;
    }

    public JLabel getDepositBalanceValue() {
        return depositBalanceValue;
    }

    public void setDepositBalanceValue(JLabel depositBalanceValue) {
        this.depositBalanceValue = depositBalanceValue;
    }
}
