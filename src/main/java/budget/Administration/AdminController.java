package budget.Administration;

import java.sql.SQLException;

/**
 * Created by 1 on 04.04.2017.
 */
public class AdminController {
    private AdminDAO adminDAO = new AdminDAO();
    private AdminUser adminUser = new AdminUser();

    public boolean isAdmin(String userLogin) {
        String userRole;
        try {
            userRole = adminDAO.getUserRoleFromDB(userLogin);
            return "admin".equals(userRole);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean isLoginExists(String logIn) {
        return logIn.equals(adminDAO.getLoginFromDataBase(logIn));
    }
}
