package budget.db;

import java.util.Hashtable;

/**
 * Created by 1 on 07.05.2017.
 */
public class DbConstants {

    public enum ActionType {
        INCOME(1, "Income"),
        OUTCOME(2, "Outcome"),
        TRANSFER_TO(3, "Transfer to"),
        TRANSFER_FFROM(4, "Trancfer from");

        private final int id;
        private final String description;

        ActionType(final int id, final String description) {
            this.id = id;
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public String getDescription() {
            return description;
        }
    }

    public ActionType[] getActionTypes() {
        return ActionType.values();
    }

    public Hashtable<Integer, String> getActionTypesTable() {
        Hashtable<Integer, String> table = new Hashtable<>();
        table.put(1, ActionType.INCOME.getDescription());
        table.put(2, ActionType.OUTCOME.getDescription());
        table.put(3, ActionType.TRANSFER_TO.getDescription());
        table.put(4, ActionType.TRANSFER_FFROM.getDescription());

        return table;
    }

    public enum ActionMethod {
        CASH(1, "Cash"),
        CREDIT_CARD(2, "Credit Card");

        private final int id;
        private final String description;

        ActionMethod(final int id, final String description) {
            this.id = id;
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public String getDescription() {
            return description;
        }
    }

    public ActionMethod[] getActionMethods() {
        return ActionMethod.values();
    }

    public Hashtable<Integer, String> getActionMethodsTable() {
        Hashtable<Integer, String> table = new Hashtable<>();
        table.put(1, ActionMethod.CASH.getDescription());
        table.put(2, ActionMethod.CREDIT_CARD.getDescription());

        return table;
    }

    public enum Currency {
        RUBLE(1, "Ruble"),
        US_DOLLAR(2, "US Dollar"),
        EURO(3, "Euro");

        private final int id;
        private final String description;

        Currency(final int id, final String description) {
            this.id = id;
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public String getDescription() {
            return description;
        }
    }

    public Currency[] getCurrencies() {
        return Currency.values();
    }

    public Hashtable<Integer, String> getCurrenciesTable() {
        Hashtable<Integer, String> table = new Hashtable<>();
        table.put(1, Currency.RUBLE.getDescription());
        table.put(2, Currency.US_DOLLAR.getDescription());
        table.put(3, Currency.EURO.getDescription());

        return table;
    }

    public enum ExpenceCategory {
        FOOD(1, "Food"),
        DINING(2, "Dining"),
        HOUSEHOLD(3, "Household"),
        UTILITIES(4, "Utilities"),
        COMMUTE(5, "Commute"),
        TAXI(6, "Taxi"),
        APPLIENCIES(7, "Appliencies"),
        HEALTH(8, "Health"),
        ENTERTAINMENT(9, "Entertainment"),
        DRINKS(10, "Drinks"),
        TRAVEL(11, "Travel"),
        CREDITS(12, "Credits"),
        COMMITMENTS(13, "Commitments"),
        CONTRIBUTIONS(14, "Contributions"),
        FEES(15, "Fees"),
        SALARY(16, "Salary"),
        OTHER(17, "Other");

        private final int id;
        private final String description;

        ExpenceCategory(final int id, final String description) {
            this.id = id;
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public String getDescription() {
            return description;
        }
    }

    public ExpenceCategory[] getExpenceCategories() {
        return ExpenceCategory.values();
    }

    public Hashtable<Integer, String> getExpencesTable() {
        Hashtable<Integer, String> table = new Hashtable<>();
        table.put(1, ExpenceCategory.FOOD.getDescription());
        table.put(2, ExpenceCategory.DINING.getDescription());
        table.put(3, ExpenceCategory.HOUSEHOLD.getDescription());
        table.put(4, ExpenceCategory.UTILITIES.getDescription());
        table.put(5, ExpenceCategory.COMMUTE.getDescription());
        table.put(6, ExpenceCategory.TAXI.getDescription());
        table.put(7, ExpenceCategory.APPLIENCIES.getDescription());
        table.put(8, ExpenceCategory.HEALTH.getDescription());
        table.put(9, ExpenceCategory.ENTERTAINMENT.getDescription());
        table.put(10, ExpenceCategory.DRINKS.getDescription());
        table.put(11, ExpenceCategory.TRAVEL.getDescription());
        table.put(12, ExpenceCategory.CREDITS.getDescription());
        table.put(13, ExpenceCategory.COMMITMENTS.getDescription());
        table.put(14, ExpenceCategory.CONTRIBUTIONS.getDescription());
        table.put(15, ExpenceCategory.FEES.getDescription());
        table.put(16, ExpenceCategory.SALARY.getDescription());
        table.put(17, ExpenceCategory.OTHER.getDescription());

        return table;
    }
}
