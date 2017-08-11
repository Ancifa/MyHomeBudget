package budget.MainWindow;

import budget.Administration.User;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by 1 on 25.02.2017.
 */
public class MainWindowView extends JFrame implements SwingConstants{
    private MainWindowController controller;
    private String userLogin;

    private Box verticalBox;
    private JTextArea textArea;
    private Box centralBox;
    private DataFillingBlock dataFillingBlock;

    private final static String LOG_IN_TEXT = "You are logged in as ";
    private List<User> usersList;
    private List<budget.ActionsData.Action> actionsList;
    private JMenuItem itemOne;
    private JMenuItem itemTwo;

    public MainWindowView(MainWindowController controller) throws HeadlessException {
        super("My Home Budget | Main Window");
        this.controller = controller;
        userLogin = controller.getLoginValue();
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void buildMainVindowLayout() {
        verticalBox = Box.createVerticalBox();
        verticalBox.add(Box.createVerticalStrut(5));
        createMenu();
        verticalBox.add(Box.createVerticalStrut(5));
        createCentralArea();
        verticalBox.add(Box.createVerticalGlue());
        verticalBox.add(Box.createVerticalStrut(3));
        createBottomBlock();
        verticalBox.add(Box.createVerticalStrut(3));
        getContentPane().add(verticalBox);
        pack();
    }

    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();
        itemOne = new JMenuItem("Show users");
        itemTwo = new JMenuItem("Show actions");

        JMenu menuOne = new JMenu("View");
        menuOne.add(itemOne);
        menuOne.add(itemTwo);
        menuOne.addSeparator();
        menuOne.add(new JMenuItem("ItemThree"));

        setMenuItemOneListener();
        setMenuItemTwoListener();

        JMenu menuTwo = new JMenu("MenuTwo");
        menuTwo.add(new JMenuItem("ItemOne"));
        menuTwo.add(new JMenuItem("ItemTwo"));

        menuBar.add(menuOne);
        menuBar.add(menuTwo);
        menuBar.add(Box.createHorizontalGlue());
        verticalBox.add(menuBar);
    }

    private void setMenuItemOneListener() {
        itemOne.addActionListener(e -> {
            try {
                textArea.setText("");
                MainWindowDAO oracleStatement = new MainWindowDAO();
                usersList = oracleStatement.getUsersListFromDatabase();
                if (usersList != null) {
                    textArea.setText(controller.usersListBuilder(usersList));
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
    }

    private void setMenuItemTwoListener() {
        itemTwo.addActionListener(e -> {
            try {
                setResultText();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
    }

    public void setResultText() throws SQLException {
        textArea.setText("");
        MainWindowDAO oracleStatement = new MainWindowDAO();
        actionsList = oracleStatement.getActionsListFromDatabase(userLogin);
        if (actionsList != null) {
            textArea.setText(controller.actionsListBuilder(actionsList));
        }
    }

    private void createBottomBlock() {
        JLabel currentDate = new JLabel(controller.getDateString(LocalDate.now()));
        JLabel loginInfo = new JLabel(LOG_IN_TEXT + controller.getLoginValue());
        Box horizontalBox = Box.createHorizontalBox();
        horizontalBox.add(Box.createHorizontalStrut(5));
        horizontalBox.add(currentDate);
        horizontalBox.add(Box.createHorizontalStrut(12));
        horizontalBox.add(loginInfo);
        horizontalBox.add(Box.createHorizontalGlue());
        horizontalBox.setAlignmentX(Box.LEFT_ALIGNMENT);
        verticalBox.add(horizontalBox);
    }

    private void createCentralArea() {
        InfoBlockController infoBlockController = new InfoBlockController(userLogin);
        dataFillingBlock = new DataFillingBlock(this, userLogin);

        Box infoBox = Box.createHorizontalBox();
        centralBox = Box.createHorizontalBox();

        infoBox.setBorder(new CompoundBorder(new EmptyBorder(1, 0, 1, 2), new LineBorder(Color.GRAY, 1)));
        centralBox.setBorder(new CompoundBorder(new EmptyBorder(1, 0, 1, 2), new LineBorder(Color.GRAY, 1)));

        InfoBlock infoBlock = new InfoBlock();
        infoBox.add(infoBlock.createInfoBoxLayout(infoBlockController));
        infoBox.setAlignmentX(Box.LEFT_ALIGNMENT);

        textArea = new JTextArea(20, 40);
        textArea.setEditable(false);
        textArea.setBorder(new CompoundBorder(new EmptyBorder(0, 0, 0, 0), new LineBorder(Color.GRAY, 1)));

        centralBox.add(dataFillingBlock.createDataFillingBlock());
        centralBox.add(textArea);
        centralBox.setAlignmentX(Box.LEFT_ALIGNMENT);

        verticalBox.add(infoBox);
        verticalBox.add(centralBox);
    }

    public String getUserLogin() {
        return userLogin;
    }
}
