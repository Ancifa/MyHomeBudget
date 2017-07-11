package budget.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 1 on 31.01.2017.
 */
public class StartWindow extends JFrame implements SwingConstants {
    private JButton logInButton;
    private JButton registrationButton;

    private StartWindow startWindow;
    private LogInWindow logInWindow;
    private RegistrationWindow registrationWindow;

    public StartWindow(String title) throws HeadlessException {
        super(title);
        setSize(400, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        buildLayout();
        setStartWindow(this);
    }

    private void buildLayout() {

        ImageIcon icon = new ImageIcon(getClass().getResource("/img/house_30x30.png"));
        JLabel text = getMainWindowLabel("My Home Budget", icon);
        logInButton = getMainWindowButton("   Log In  ", "For existing users");
        registrationButton = getMainWindowButton("Register", "For new users");

        setLogInButtonListener();
        setRegistrationButtonListener();

        Box box = Box.createVerticalBox();
        box.add(Box.createVerticalStrut(50));
        box.add(text);
        text.setAlignmentX(Box.CENTER_ALIGNMENT);
        box.add(Box.createVerticalStrut(50));
        box.add(logInButton);
        logInButton.setAlignmentX(Box.CENTER_ALIGNMENT);
        box.add(Box.createVerticalStrut(30));
        box.add(registrationButton);
        registrationButton.setAlignmentX(Box.CENTER_ALIGNMENT);
        box.add(Box.createVerticalGlue());
        getContentPane().add(box);
    }

    private void setLogInButtonListener() {
        logInButton.addActionListener(e -> {
            if (logInWindow == null) {
                logInWindow = new LogInWindow("Log In");
            }
            logInWindow.setVisible(true);
            logInWindow.setLocationRelativeTo(null);
            this.setVisible(false);
            logInWindow.setStartWindow(this);
                }
        );
    }

    private void setRegistrationButtonListener() {
        registrationButton.addActionListener(e -> {
            if (registrationWindow != null) {
                registrationWindow = null;
            }
            registrationWindow = new RegistrationWindow("RegistrationDAO");
            registrationWindow.setVisible(true);
            registrationWindow.setLocationRelativeTo(null);
            this.setVisible(false);
            registrationWindow.setStartWindow(this);
        }
        );
    }

    private JButton getMainWindowButton(String buttonText, String toolTipText) {
        JButton button = new JButton(buttonText);
        button.setToolTipText(toolTipText);

        return button;
    }

    private JLabel getMainWindowLabel(String text, ImageIcon icon) {
        JLabel label = new JLabel(text, icon, CENTER);
        label.setVerticalTextPosition(BOTTOM);
        label.setHorizontalTextPosition(CENTER);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));

        return label;
    }

    public StartWindow getStartWindow() {
        return startWindow;
    }

    public void setStartWindow(StartWindow startWindow) {
        this.startWindow = startWindow;
    }
}
