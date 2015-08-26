package cz.tomasdvorak.mealvoucher;

public class VoucherCountResult {
    private int underCount;
    private int overCount;
    private double underVoucherSum;
    private double underPrice;
    private double overPrice;
    private double overVoucherSum;

    public VoucherCountResult(int underCount, double underPrice, double underVoucherSum, int overCount, double overPrice, double overVoucherSum) {
        this.underCount = underCount;
        this.overCount = overCount;
        this.underVoucherSum = underVoucherSum;
        this.underPrice = underPrice;
        this.overPrice = overPrice;
        this.overVoucherSum = overVoucherSum;
    }

    public int getUnderCount() {
        return underCount;
    }

    public int getOverCount() {
        return overCount;
    }

    public double getUnderPrice() {
        return underPrice;
    }

    public double getOverPrice() {
        return overPrice;
    }

    public double getUnderVoucherSum() {
        return underVoucherSum;
    }

    public double getOverVoucherSum() {
        return overVoucherSum;
    }
}
