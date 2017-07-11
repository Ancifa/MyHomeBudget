package budget.MainWindow;

import budget.ActionsData.Action;
import budget.Administration.User;
import budget.db.DbConstants;
import budget.db.OracleConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by 1 on 06.01.2017.
 */
public class MainWindowDAO {
    private OracleConnection oracleConnection;
    private DbConstants dbConstants;


    public List<User> getUsersListFromDatabase() throws SQLException {
        List<User> usersList = new ArrayList<>();
        oracleConnection = new OracleConnection();

        Connection connection = oracleConnection.makeConnectionAndWarnIfNull();
        if (connection == null) return null;

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM USER1.USERS ORDER BY USER_ID");
        while (result.next()) {
            User userData = new User();
            userData.setUserId(result.getLong("USER_ID"));
            userData.setUserLogin(result.getString("USER_NAME"));
            userData.setUserPassword(result.getString("USER_PASSWORD"));
            userData.setSignDate(result.getDate("USER_SIGN_DATE"));
            userData.setUserRole(result.getString("USER_ROLE"));
            userData.setLastLoginDate(result.getDate("LAST_LOGIN_DATE"));
            usersList.add(userData);
        }

        statement.close();
        result.close();
        connection.close();

        return usersList;
    }

    public List<Action> getActionsListFromDatabase(String userLogin) throws SQLException {
        dbConstants = new DbConstants();
        List<Action> actionsList = new ArrayList<>();
        Action action;
        oracleConnection = new OracleConnection();

        Connection connection = oracleConnection.makeConnectionAndWarnIfNull();
        if (connection == null) return null;

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(
                "SELECT * FROM USER1.USERS_ACTIONS, USER1.USERS " +
                        "WHERE USERS_ACTIONS.USER_ID = USERS.USER_ID AND USERS.USER_NAME = '" + userLogin +
                        "' ORDER BY ACTION_ID");
        while (result.next()) {
            action = new Action();
            action.setDate(result.getDate("ACTION_DATE"));
            action.setType(getTypeName(result));
            action.setSumm(result.getString("ACTION_SUMM"));
            action.setCurrency(getCurrencyName(result));
            action.setCategory(getCategoryName(result));
            action.setDescription(result.getString("ACTION_DESCRIPTION"));
            action.setMethod(getMethodName(result));

            actionsList.add(action);
        }

        statement.close();
        result.close();
        connection.close();

        return actionsList;
    }

    private String getTypeName(ResultSet result) throws SQLException {
        Hashtable<Integer, String> set = dbConstants.getActionTypesTable();

        return set.get(Integer.parseInt(result.getString("ACTION_TYPE")));
    }

    private String getCurrencyName(ResultSet result) throws SQLException {
        Hashtable<Integer, String> set = dbConstants.getCurrenciesTable();

        return set.get(Integer.parseInt(result.getString("ACTION_CURRENCY")));
    }

    private String getCategoryName(ResultSet result) throws SQLException {
        Hashtable<Integer, String> set = dbConstants.getExpencesTable();

        return set.get(Integer.parseInt(result.getString("ACTION_CATEGORY")));
    }

    private String getMethodName(ResultSet result) throws SQLException {
        Hashtable<Integer, String> set = dbConstants.getActionMethodsTable();

        return set.get(Integer.parseInt(result.getString("ACTION_METHOD")));
    }
}
