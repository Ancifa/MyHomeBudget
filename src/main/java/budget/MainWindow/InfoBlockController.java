package budget.MainWindow;

import budget.ActionsData.Action;
import budget.ActionsData.BudgetSummary;

import java.util.Date;

/**
 * Created by 1 on 19.07.2017.
 */
public class InfoBlockController {
    private InfoBlock infoBlock;
    private BudgetSummary budgetSummary;
    private MainWindowDAO dao;

    public InfoBlockController(String userLogin) {
        getBudgetSummaryData(userLogin);
    }

    public void mountData(InfoBlock infoBlock) {
        infoBlock.getTotalBalanceValue().setText(String.valueOf(budgetSummary.getTotalBalance()));
        infoBlock.getCashBalanceValue().setText(String.valueOf(budgetSummary.getCashBalance()));
        infoBlock.getCardBalanceValue().setText(String.valueOf(budgetSummary.getCardsBalance()));
        infoBlock.getDepositBalanceValue().setText(String.valueOf(budgetSummary.getDepositsBalance()));
    }

    private void getBudgetSummaryData(String userLogin) {
        dao = new MainWindowDAO();
        budgetSummary = dao.getUserBalance(userLogin);
    }

    public void saveBudgetSummary(Action action, String userLogin) {
        setSummaryData(action);
        dao.updateUserBalance(budgetSummary, userLogin);
        budgetSummary = dao.getUserBalance(userLogin);
//        mountData(infoBlock);

    }

    private void setSummaryData(Action action) {
        calculateSummaryData(action);
        budgetSummary.setLasrEntryDate(new Date());

    }

    private void calculateSummaryData(Action action) {
        budgetSummary.setTotalBalance(Integer.parseInt(action.getSumm()));
    }
}
