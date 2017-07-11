package budget.db;

import javax.swing.*;
import java.sql.*;
import java.util.Locale;

/**
 * Created by 1 on 06.01.2017.
 */
public class OracleConnection {
    private static final String JDBC_ORACLE_STRING = "jdbc:oracle:thin:@";
    private static final String DB_LOCAL_HOST_ADDRESS = JDBC_ORACLE_STRING + "localhost:1521:xe";
    private static final String DB_HOST_IP_ADDRESS = JDBC_ORACLE_STRING + "192.168.0.15:1521:xe";

    public static final String DB_CONNECTION_PROBLEM_MESSAGE = "Something wrong with DB connection";

    private String dbLogin = "system";
    private String dbPass = "xfosus";

    private Connection oracleConnection() throws SQLException, ClassNotFoundException {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Locale.setDefault(Locale.ENGLISH);

            return DriverManager.getConnection(DB_LOCAL_HOST_ADDRESS, dbLogin, dbPass);
    }

    public Connection makeConnectionAndWarnIfNull() {
//        ConnectionWindow connectionWindow = new ConnectionWindow("Connection");
//        connectionWindow.setVisible(true);

        Connection connection = null;
        try {
            connection = oracleConnection();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, DB_CONNECTION_PROBLEM_MESSAGE +
                    "\n" + e.getMessage());
            System.exit(0);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

//        connectionWindow.setVisible(false);
        return connection;
    }


    private void example() throws SQLException {

        java.util.Date userSignDate = new java.util.Date();
        Timestamp timestamp = new Timestamp(userSignDate.getTime());

        Connection connection;
        connection = makeConnectionAndWarnIfNull();
        if (connection != null) {

            Statement statement = connection.createStatement();

/*        String insertString = "INSERT INTO USER1.USERS (USER_NAME, USER_PASSWORD, USER_SIGN_DATE) VALUES (?, ?, ?)";
        PreparedStatement prst = con.prepareStatement(insertString);
        prst.setString(1, userName);
        prst.setString(2, userPassword);
        prst.setTimestamp(3, timestamp);

        prst.executeUpdate();*/

/*            statement.executeUpdate("INSERT INTO USER1.USERS (USER_NAME, USER_PASSWORD, USER_SIGN_DATE)" +
                    "VALUES ('"+userName+"', '"+userPassword+"', '"+timestamp+"')");
                    "VALUES ('"+userName+"', '"+userPassword+"', to_date('"+dateString+"', 'YYYY-MM-DD HH24:MI:SS'))");
                    "VALUES ('"+userName+"', '"+userPassword+"', to_date('2017-01-24', 'YYYY-MM-DD HH24:MI:SS'))");*/

            ResultSet result = statement.executeQuery("select * from user1.users ORDER BY USER_ID");

            while (result.next()) {
                System.out.println(result.getInt(1) + " " + result.getString(2) +
                        " " + result.getString(3) + " " + result.getString(4));
            }
            statement.close();
            result.close();
            connection.close();
        } else {
            JOptionPane.showMessageDialog(null, DB_CONNECTION_PROBLEM_MESSAGE);
        }
    }
}
