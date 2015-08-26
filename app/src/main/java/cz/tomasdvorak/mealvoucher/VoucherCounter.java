package cz.tomasdvorak.mealvoucher;

import java.text.NumberFormat;

import cz.tomasdvorak.mealvoucher.dto.CurrencyEntry;

public class VoucherCounter {

    private final CurrencyEntry currency;
    private final double voucherValue;

    public VoucherCounter(CurrencyEntry currency, double voucherValue) {
        this.currency = currency;
        this.voucherValue = voucherValue;
    }

    public VoucherCountResult compute(String priceText) {

        try {
            double price = Double.parseDouble(priceText);

            double divided = price / voucherValue;

            int underCount = (int) Math.floor(divided);
            int overCount = (int) Math.ceil(divided);

            double underPrice = price - underCount * voucherValue;
            double overPrice = price - overCount * voucherValue;

            return new VoucherCountResult(currency.getFormat(), underCount, underPrice, underCount * voucherValue, overCount, overPrice, overCount * voucherValue);
        } catch (NumberFormatException ignored) {
            return new VoucherCountResult(NumberFormat.getNumberInstance(), 0, 0, 0, 0, 0, 0);
        }
    }
}
