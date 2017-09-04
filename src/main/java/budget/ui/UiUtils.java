package budget.ui;

import javax.swing.*;
import java.time.LocalDate;

/**
 * Created by 1 on 07.02.2017.
 */
public class UiUtils extends JFrame{

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

    public static String getDateString(LocalDate date) {
        return String.valueOf(date.getDayOfMonth()) + " "
                + String.valueOf(date.getMonth()).toLowerCase() + " "
                + String.valueOf(date.getYear()) + " ("
                + String.valueOf(date.getDayOfWeek()).toLowerCase() + ")";
    }
}
