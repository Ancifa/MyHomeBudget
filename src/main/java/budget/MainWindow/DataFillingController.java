package budget.MainWindow;

import budget.ActionsData.Action;
import budget.ui.UiUtils;

import javax.swing.*;
import java.sql.Date;
import java.sql.SQLException;

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
        if ("".equals(dataFillingBlock.getSummField().getText())) {
            JOptionPane.showMessageDialog(null, "Summ value should not be empty!");
            return;
        }
        action = new Action();

        action.setDate(Date.valueOf(dataFillingBlock.getDatePicker().getDateFromPicker()));
        action.setType(dataFillingBlock.getActionTypeField().getSelectedItem().toString());
        action.setCategory(dataFillingBlock.getExpenceCategoryField().getSelectedItem().toString());
        action.setCurrency(dataFillingBlock.getActionCurrencyField().getSelectedItem().toString());
        action.setMethod(dataFillingBlock.getActionMethodField().getSelectedItem().toString());
        action.setSumm(dataFillingBlock.getSummField().getText().replaceAll(",", ""));
        action.setDescription(dataFillingBlock.getActionDescriptionArea().getText());
    }

    public void saveToDatabase(String userLogin) {
        InfoBlock infoBlock = dataFillingBlock.getMainWindowView().getInfoBlock();
        InfoBlockController infoBlockController = new InfoBlockController(infoBlock, userLogin);
        dao = new MainWindowDAO();
        if (action == null) {
            JOptionPane.showMessageDialog(null, "Action record is empty!");
            return;
        }
            try {
                dao.insertActionToDatabase(action, userLogin);
                infoBlockController.saveBudgetSummary(action, userLogin);
                java.util.Date date = infoBlockController.getBudgetSummary().getLasrEntryDate();
                dataFillingBlock.setLastEntryValue(UiUtils.getDateString(new java.sql.Date(date.getTime()).toLocalDate()));
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
    }
}
