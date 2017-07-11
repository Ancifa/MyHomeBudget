package budget.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by 1 on 08.03.2017.
 */
public class RegistrationDAO {

    public void addUserToDataBase(String logIn, String password) {
        Date userSignDate = new Date();
        Timestamp timestamp = new Timestamp(userSignDate.getTime());
        OracleConnection oracleConnection = new OracleConnection();

        Connection connection;
        connection = oracleConnection.makeConnectionAndWarnIfNull();
        if (connection == null) return;

        String insertString = "INSERT INTO USER1.USERS" +
                "(USER_NAME, USER_PASSWORD, USER_SIGN_DATE, USER_ROLE, LAST_LOGIN_DATE) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement prst = null;
        try {
            prst = connection.prepareStatement(insertString);
            prst.setString(1, logIn);
            prst.setString(2, password);
            prst.setTimestamp(3, timestamp);
            prst.setString(4, "user");
            prst.setTimestamp(5, timestamp);

            prst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (prst != null) prst.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
