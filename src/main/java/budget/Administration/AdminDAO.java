package budget.Administration;

import budget.db.OracleConnection;

import java.sql.*;

/**
 * Created by 1 on 04.04.2017.
 */
public class AdminDAO {
    OracleConnection oracleConnection;

    public String getUserRoleFromDB(String userLogin) throws SQLException {
        String userRole = "user";
        oracleConnection = new OracleConnection();

        Connection connection;
        connection = oracleConnection.makeConnectionAndWarnIfNull();
        if (connection != null) {
            String insertString = "SELECT user_role FROM USER1.USERS WHERE USER_NAME = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(insertString);
            preparedStatement.setString(1, userLogin);
            ResultSet result = preparedStatement.executeQuery();

            result.next();
            userRole = result.getString(1);

            result.close();
            preparedStatement.close();
            connection.close();
        }

        return userRole;
    }

    public String getLoginFromDataBase(String logIn) {
        OracleConnection oracleConnection = new OracleConnection();
        String dataBaseLogin = "";

        Connection connection = oracleConnection.makeConnectionAndWarnIfNull();
        if (connection == null) return null;

        Statement statement = null;
        ResultSet result = null;

        try {
            statement = connection.createStatement();
            result = statement.executeQuery("select * from user1.users where user_name='" + logIn + "'");

            while (result.next()) {
                if (logIn.equals(result.getString(2))) {
                    dataBaseLogin = result.getString(2);
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

        return dataBaseLogin;
    }
}
