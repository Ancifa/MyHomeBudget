package budget.MainWindow;

import budget.ActionsData.BudgetSummary;

/**
 * Created by 1 on 19.07.2017.
 */
public class InfoBlockController {
    private InfoBlock infoBlock;
    private BudgetSummary budgetSummary;

    public void mountData() {
        infoBlock.getTotalBalanceValue().setText(String.valueOf(budgetSummary.getTotalBalance()));
    }

}
