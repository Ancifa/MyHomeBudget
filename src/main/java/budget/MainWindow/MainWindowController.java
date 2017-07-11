package budget.MainWindow;

import budget.ActionsData.Action;
import budget.Administration.AdminController;
import budget.Administration.User;

import java.util.List;

/**
 * Created by 1 on 17.04.2017.
 */
public class MainWindowController {
    private String loginValue;
    private AdminController adminController;

    public String usersListBuilder(List<User> usersList) {
        adminController = new AdminController();
        int count = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("   No\t")
                .append("Login\t")
                .append("Password\t")
                .append("Sign Date\t")
                .append("User Role\t")
                .append("Last Login\n\n");
        for (User user : usersList) {
            if ("admin".equals(user.getUserRole()) && !adminController.isAdmin(loginValue)) {
                continue;
            }
            stringBuilder.append("   ");
            stringBuilder.append(user.getUserId() + "\t");
            stringBuilder.append(user.getUserLogin() + "\t");
            stringBuilder.append(user.getUserPassword() + "\t");
            stringBuilder.append(user.getSignDate() + "\t");
            stringBuilder.append(user.getUserRole() + "\t");
            stringBuilder.append(user.getLastLoginDate());
            stringBuilder.append("\n");
            count++;
        }
        stringBuilder.append("   --------------------------------------\n");
        stringBuilder.append("    Total number of users: " + count);

        return String.valueOf(stringBuilder);
    }

    public String actionsListBuilder(List<Action> actionsList) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("   Date\t")
                .append("Type\t")
                .append("Summ\t")
                .append("Currency\t")
                .append("Method\t")
                .append("Category\t")
                .append("Description\n\n");
        for (Action action : actionsList) {
            stringBuilder.append("   ");
            stringBuilder.append(action.getDate() + "\t");
            stringBuilder.append(action.getType() + "\t");
            stringBuilder.append(action.getSumm() + "\t");
            stringBuilder.append(action.getCurrency() + "\t");
            stringBuilder.append(action.getMethod() + "\t");
            stringBuilder.append(action.getCategory() + "\t");
            stringBuilder.append(action.getDescription());
            stringBuilder.append("\n");
        }

        return String.valueOf(stringBuilder);
    }

    public String getLoginValue() {
        return loginValue;
    }

    public void setLoginValue(String loginValue) {
        this.loginValue = loginValue;
    }
}
