package budget.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by 1 on 23.03.2017.
 */
public class ConnectionWindow extends JWindow {

    public ConnectionWindow() {
        super();

//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        setResizable(false);
        setSize(150, 70);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);

/*        Box mainBox = Box.createHorizontalBox();
        Box inBox = Box.createVerticalBox();
        JLabel label = new JLabel("Connecting...");
        inBox.add(label);
        mainBox.add(Box.createHorizontalGlue());
        mainBox.add(inBox);
        mainBox.add(Box.createHorizontalGlue());
        getContentPane().add(mainBox);*/
    }
}
