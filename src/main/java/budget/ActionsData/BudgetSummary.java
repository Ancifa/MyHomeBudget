package budget.ActionsData;

import budget.Administration.User;

import java.util.Date;

/**
 * Created by 1 on 19.07.2017.
 */
public class BudgetSummary {
    private User user;
    private int totalBalance;
    private int cashBalance;
    private int cardsBalance;
    private int depositsBalance;
    private Date lasrEntryDate;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(int totalBalance) {
        this.totalBalance = totalBalance;
    }

    public int getCashBalance() {
        return cashBalance;
    }

    public void setCashBalance(int cashBalance) {
        this.cashBalance = cashBalance;
    }

    public int getCardsBalance() {
        return cardsBalance;
    }

    public void setCardsBalance(int cardsBalance) {
        this.cardsBalance = cardsBalance;
    }

    public int getDepositsBalance() {
        return depositsBalance;
    }

    public void setDepositsBalance(int depositsBalance) {
        this.depositsBalance = depositsBalance;
    }

    public Date getLasrEntryDate() {
        return lasrEntryDate;
    }

    public void setLasrEntryDate(Date lasrEntryDate) {
        this.lasrEntryDate = lasrEntryDate;
    }
}
