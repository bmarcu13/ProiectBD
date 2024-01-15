package model.financial;

public class MedicProfitData {
    private int unitId;
    private String unitName;
    private int medicProfit;

    public MedicProfitData(int _unitId, String _unitName, int _medicProfit) {
        this.unitId = _unitId;
        this.unitName = _unitName;
        this.medicProfit = _medicProfit;
    }

    public int getUnitId() {
        return unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public int getMedicProfit() {
        return medicProfit;
    }
}
