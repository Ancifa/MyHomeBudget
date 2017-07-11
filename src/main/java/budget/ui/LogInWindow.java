package budget.ui;

import budget.MainWindow.MainWindowController;
import budget.MainWindow.MainWindowView;
import budget.db.Authentication;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by 1 on 07.02.2017.
 */
public class LogInWindow extends JFrame {
    private JTextField loginField;
    private JPasswordField passwordField;
    private JButton okButton;
    private JButton cancelButton;

    private StartWindow startWindow;

    public LogInWindow(String title) throws HeadlessException {
        super(title);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        buildLayout();
    }

    private void buildLayout() {
        Box mainBox = Box.createVerticalBox();
        Box loginBox = Box.createHorizontalBox();
        Box passwordBox = Box.createHorizontalBox();
        Box buttonBox = Box.createHorizontalBox();

        JLabel loginLabel = new JLabel("Login:");
        JLabel passwordLabel = new JLabel("Password:");
        loginLabel.setPreferredSize(passwordLabel.getPreferredSize());

        loginField = new JTextField(15);
        passwordField = new JPasswordField(15);

        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");

        setOkButtinListener();
        setCancelButtonListener();

        loginBox.add(loginLabel);
        loginBox.add(Box.createHorizontalStrut(6));
        loginBox.add(loginField);

        passwordBox.add(passwordLabel);
        passwordBox.add(Box.createHorizontalStrut(6));
        passwordBox.add(passwordField);

        buttonBox.add(Box.createHorizontalGlue());
        buttonBox.add(okButton);
        buttonBox.add(Box.createHorizontalStrut(12));
        buttonBox.add(cancelButton);

        mainBox.setBorder(new EmptyBorder(12, 12, 12, 12));
        mainBox.add(loginBox);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(passwordBox);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(buttonBox);

        getContentPane().add(mainBox);
        pack();
    }

    private void setOkButtinListener() {
        okButton.addActionListener(e -> {
            String loginText = loginField.getText();
            String passwordText = UiUtils.makePasswordToString(passwordField);
            if (loginText.equals("") || loginText.equals(" ")) {
                performWrongLoginActions();
            } else if (Authentication.isLoginAndPasswordOk(loginText, passwordText)) {
                performCorrectLoginActions(loginText);
            } else {
                performWrongLoginActions();
            }
        });
    }

    private void setCancelButtonListener() {
        cancelButton.addActionListener(e -> {
            loginField.setText("");
            passwordField.setText("");
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
        JOptionPane.showMessageDialog(null, loginText + ", you are logged in successfully.");
        Authentication.updateUserLoginDate(loginText);
    }

    private void performWrongLoginActions() {
        loginField.setText("");
        passwordField.setText("");
        loginField.requestFocus();
        JOptionPane.showMessageDialog(null, "Wrong LogIn or password. Try once more.");
    }

    public void setStartWindow(StartWindow startWindow) {
        this.startWindow = startWindow;
    }
}
