package budget.ui;

import javax.swing.*;

/**
 * Created by 1 on 07.02.2017.
 */
class UiUtils extends JFrame{

    void initWindowMainSettings() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public static String makePasswordToString(JPasswordField passwordField) {
        String password;
        char passwordText[] = passwordField.getPassword();
        password = String.valueOf(passwordText);

        return password;
    }
}
