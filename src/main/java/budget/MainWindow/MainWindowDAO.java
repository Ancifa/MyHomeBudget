package budget.MainWindow;

import budget.ActionsData.Action;
import budget.Administration.User;
import budget.db.DbConstants;
import budget.db.OracleConnection;

import java.sql.*;
import java.util.*;
import java.util.Date;

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

    public void insertActionToDatabase(Action action, String userLogin) throws SQLException {
        OracleConnection oracleConnection = new OracleConnection();
        dbConstants = new DbConstants();

        Date date = action.getDate();
        Timestamp timestamp = new Timestamp(date.getTime());

        Connection connection;
        connection = oracleConnection.makeConnectionAndWarnIfNull();
        if (connection == null) return;

        String insertString = "insert into user1.users_actions " +
                "(user_id, action_type, action_description, action_summ, " +
                "action_method, action_currency, action_date, ACTION_CATEGORY) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement prst = null;
        try {
            prst = connection.prepareStatement(insertString);
            prst.setInt(1, getUserId(connection, userLogin));
            prst.setInt(2, getActionId(dbConstants.getActionTypesTable(), action.getType()));
            prst.setString(3, action.getDescription());
            prst.setInt(4, Integer.parseInt(action.getSumm()));
            prst.setInt(5, getActionId(dbConstants.getActionMethodsTable(), action.getMethod()));
            prst.setInt(6, getActionId(dbConstants.getCurrenciesTable(), action.getCurrency()));
            prst.setTimestamp(7, timestamp);
            prst.setInt(8, getActionId(dbConstants.getExpencesTable(), action.getCategory()));

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

    private int getUserId(Connection connection, String userLogin) {
        int userId = 0;
        Statement statement = null;
        ResultSet result = null;
        try {
            statement = connection.createStatement();
            result = statement.executeQuery(
                    "SELECT USER_ID FROM USER1.USERS WHERE USER_NAME = '" + userLogin + "'");
            result.next();
            userId = result.getInt("USER_ID");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
                if (result != null) result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return userId;
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
                        "' ORDER BY ACTION_DATE DESC, ACTION_ID DESC");
        while (result.next()) {
            action = new Action();
            action.setDate(result.getDate("ACTION_DATE"));
            action.setType(getTypeName(result));
            action.setSumm(String.valueOf(result.getDouble("ACTION_SUMM")));
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

    private int getActionId(Hashtable<Integer, String> table, String type) {
        int id = 0;
        for (Map.Entry entry : table.entrySet()) {
            if (type.equals(entry.getValue().toString().toUpperCase())) {
                id = Integer.parseInt(entry.getKey().toString());
                break;
            }
        }

        return id;
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
