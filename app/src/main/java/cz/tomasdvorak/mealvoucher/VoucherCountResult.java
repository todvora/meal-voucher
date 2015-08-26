package cz.tomasdvorak.mealvoucher;

import java.text.NumberFormat;

public class VoucherCountResult {
    private NumberFormat numberFormat;
    private int underCount;
    private int overCount;
    private double underVoucherSum;
    private double underPrice;
    private double overPrice;
    private double overVoucherSum;

    public VoucherCountResult(NumberFormat numberFormat, int underCount, double underPrice, double underVoucherSum, int overCount, double overPrice, double overVoucherSum) {
        this.numberFormat = numberFormat;
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

    public String getUnderPrice() {
        return numberFormat.format(underPrice);
    }

    public String getOverPrice() {
        return numberFormat.format(Math.abs(overPrice));
    }

    public String getUnderVoucherSum() {
        return numberFormat.format(underVoucherSum);
    }

    public String getOverVoucherSum() {
        return numberFormat.format(overVoucherSum);
    }
}
