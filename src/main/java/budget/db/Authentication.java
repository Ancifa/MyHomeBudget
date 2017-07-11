package budget.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by 1 on 25.02.2017.
 */
public class Authentication {

    private static boolean authenticationResult = false;

    public static boolean isLoginAndPasswordOk(String logIn, String password) {
        ArrayList<String> dataBaseResultList = checkLoginAndPasswordFromDataBase(logIn);
        if (dataBaseResultList != null && dataBaseResultList.size() != 0) {
            String dataBaseLogin = dataBaseResultList.get(0);
            String dataBasePassword = dataBaseResultList.get(1);
            if (logIn.equals(dataBaseLogin)) {
                if (password.equals(dataBasePassword)) {
                    return authenticationResult = true;
                }
            }
        }
        return authenticationResult;
    }

    private static ArrayList<String> checkLoginAndPasswordFromDataBase(String logIn) {
        ArrayList<String> requestResultList = new ArrayList<>();
        OracleConnection oracleConnection = new OracleConnection();

        Connection connection = oracleConnection.makeConnectionAndWarnIfNull();
        if (connection == null) return null;

        Statement statement = null;
        ResultSet result = null;

        try {
            statement = connection.createStatement();
            result = statement.executeQuery("select * from user1.users where user_name = '" + logIn + "'");

            while (result.next()) {
                if (logIn.equals(result.getString(2))) {
                    requestResultList.add(result.getString(2));
                    requestResultList.add(result.getString(3));
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return requestResultList;
    }

    public static void updateUserLoginDate(String userName) {
        Date userLoginDate = new Date();
        Timestamp timestamp = new Timestamp(userLoginDate.getTime());
        OracleConnection oracleConnection = new OracleConnection();

        Connection connection;
        connection = oracleConnection.makeConnectionAndWarnIfNull();
        if (connection == null) return;

        String insertString = "UPDATE USER1.USERS SET LAST_LOGIN_DATE = ?" +
                " WHERE USER_NAME = ?";
        PreparedStatement prst = null;
        try {
            prst = connection.prepareStatement(insertString);
            prst.setTimestamp(1, timestamp);
            prst.setString(2, userName);
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
