package model.financial;

public class MedicalUnitProfitData {
    private final int id;
    private final String name;
    private final int income;
    private final int expenses;
    private final int profit;

    public MedicalUnitProfitData(int _id, String _name, int _income, int _expenses, int _profit) {
        this.id = _id;
        this.name = _name;
        this.income = _income;
        this.expenses = _expenses;
        this.profit = _profit;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getIncome() {
        return this.income;
    }

    public int getExpenses() {
        return this.expenses;
    }

    public int getProfit() {
        return this.profit;
    }


}
