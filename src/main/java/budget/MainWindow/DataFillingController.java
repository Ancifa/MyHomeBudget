package budget.MainWindow;

import budget.ActionsData.Action;

import javax.swing.*;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * Created by 1 on 15.07.2017.
 */
public class DataFillingController {
    private Action action;
    private DataFillingBlock dataFillingBlock;
    private MainWindowDAO dao;

    public DataFillingController(DataFillingBlock dataFillingBlock) {
        this.dataFillingBlock = dataFillingBlock;
    }

    public void gatherData() {
        action = new Action();

        action.setDate(Date.valueOf(LocalDateTime.now().toLocalDate()));
        action.setType(dataFillingBlock.getActionTypeField().getSelectedItem().toString());
        action.setCategory(dataFillingBlock.getExpenceCategoryField().getSelectedItem().toString());
        action.setCurrency(dataFillingBlock.getActionCurrencyField().getSelectedItem().toString());
        action.setMethod(dataFillingBlock.getActionMethodField().getSelectedItem().toString());
        action.setSumm(dataFillingBlock.getSummField().getText());
        action.setDescription(dataFillingBlock.getActionDescriptionArea().getText());
    }

    public void saveToDatabase(String userLogin) {
        dao = new MainWindowDAO();
        if (action == null) {
            JOptionPane.showMessageDialog(null, "Action record is empty!");
            return;
        }
            try {
                dao.insertActionToDatabase(action, userLogin);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
    }
}
