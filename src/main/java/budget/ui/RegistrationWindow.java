package budget.ui;

import budget.Administration.AdminController;
import budget.MainWindow.MainWindowController;
import budget.MainWindow.MainWindowView;
import budget.db.RegistrationDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by 1 on 08.03.2017.
 */
public class RegistrationWindow extends JFrame {
    private JLabel checkLogInLabel;
    private JTextField logInField;
    private JPasswordField passwordField;
    private JPasswordField checkPasswordField;
    private JButton checkLoginButton;
    private JButton okButton;
    private JButton cancelButton;

    private StartWindow startWindow;

    public RegistrationWindow(String title) throws HeadlessException {
        super(title);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        buildLayout();
    }

    private void buildLayout() {
        Box mainBox = Box.createVerticalBox();
        Box loginBox = Box.createHorizontalBox();
        Box checkLoginBox = Box.createHorizontalBox();
        Box passwordBox = Box.createHorizontalBox();
        Box checkPasswordBox = Box.createHorizontalBox();
        Box buttonBox = Box.createHorizontalBox();

        JLabel logInLabel = new JLabel("Create your login:");
        checkLogInLabel = new JLabel("Check your login:");
        JLabel passwordLabel = new JLabel("Create your password:");
        JLabel checkPasswordLabel = new JLabel("Check your password:");
        logInLabel.setPreferredSize(passwordLabel.getPreferredSize());
        checkPasswordLabel.setPreferredSize(passwordLabel.getPreferredSize());

        logInField = new JTextField(15);
        passwordField = new JPasswordField(15);
        passwordField.setEditable(false);
        checkPasswordField = new JPasswordField(15);
        checkPasswordField.setEditable(false);

        checkLoginButton = new JButton("Check");
        okButton = new JButton("OK");
        okButton.setEnabled(false);
        cancelButton = new JButton("Cancel");

        setCheckLoginButtonListener();
        setOkButtonListener();
        setCancelButtonListener();

        loginBox.add(logInLabel);
        loginBox.add(Box.createHorizontalStrut(6));
        loginBox.add(logInField);

        checkLoginBox.add(Box.createHorizontalGlue());
        checkLoginBox.add(checkLogInLabel);
        checkLoginBox.add(Box.createHorizontalStrut(12));
        checkLoginBox.add(checkLoginButton);

        passwordBox.add(passwordLabel);
        passwordBox.add(Box.createHorizontalStrut(6));
        passwordBox.add(passwordField);

        checkPasswordBox.add(checkPasswordLabel);
        checkPasswordBox.add(Box.createHorizontalStrut(6));
        checkPasswordBox.add(checkPasswordField);

        buttonBox.add(Box.createHorizontalGlue());
        buttonBox.add(okButton);
        buttonBox.add(Box.createHorizontalStrut(12));
        buttonBox.add(cancelButton);

        mainBox.setBorder(new EmptyBorder(12, 12, 12, 12));
        mainBox.add(loginBox);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(checkLoginBox);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(passwordBox);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(checkPasswordBox);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(buttonBox);

        getContentPane().add(mainBox);
        pack();
    }

    private void setOkButtonListener() {
        okButton.addActionListener(e -> {
            String password = UiUtils.makePasswordToString(passwordField);
            String checkPassword = UiUtils.makePasswordToString(checkPasswordField);
            if ("".equals(password) || password.contains(" ")) {
                performIncorrectPasswordActions();
            } else if (!password.equals(checkPassword)) {
                JOptionPane.showMessageDialog(null, "Password and check password fields don't match.");
            } else {
                RegistrationDAO registrationDAO = new RegistrationDAO();
                registrationDAO.addUserToDataBase(logInField.getText(), password);
                JOptionPane.showMessageDialog(null, logInField.getText() + ", you are registered successfully.");
                performCorrectLoginActions(logInField.getText());
            }
        });
    }

    private void setCheckLoginButtonListener() {
        checkLoginButton.addActionListener(e -> {
            AdminController adminController = new AdminController();
            if ("".equals(logInField.getText()) || logInField.getText().contains(" ")) {
                performIncorrectLoginActions();
            } else if (!adminController.isLoginExists(logInField.getText())) {
                performActionsWhenLoginNotExists();
            } else {
                JOptionPane.showMessageDialog(null,
                        "\"" + logInField.getText() + "\" login is already in use. Try another one.");
            }
        });
    }

    private void performActionsWhenLoginNotExists() {
        logInField.setEditable(false);
        checkLoginButton.setVisible(false);
        checkLogInLabel.setForeground(Color.GREEN);
        checkLogInLabel.setText("\"" + logInField.getText() + "\" login is OK");
        passwordField.setEditable(true);
        checkPasswordField.setEditable(true);
        okButton.setEnabled(true);
        passwordField.requestFocus();
    }

    private void performIncorrectLoginActions() {
        logInField.setText("");
        logInField.requestFocus();
        JOptionPane.showMessageDialog(null, "Login can't be blank or starts or contains space. Try once more.");
    }

    private void performIncorrectPasswordActions() {
        passwordField.setText("");
        checkPasswordField.setText("");
        passwordField.requestFocus();
        JOptionPane.showMessageDialog(null, "Password can't be blank or starts or contains space. Try once more.");
    }

    private void setCancelButtonListener() {
        cancelButton.addActionListener(e -> {
            logInField.setText("");
            passwordField.setText("");
            checkPasswordField.setText("");
            this.dispose();
            startWindow.setVisible(true);
        });
    }

    private void performCorrectLoginActions(String loginText) {
        this.dispose();
        MainWindowController controller = new MainWindowController();
        controller.setLoginValue(loginText);
        MainWindowView mainWindowView = new MainWindowView(controller);
        mainWindowView.buildMainVindowLayout();
        mainWindowView.setVisible(true);
    }

    public void setStartWindow(StartWindow startWindow) {
        this.startWindow = startWindow;
    }
}
