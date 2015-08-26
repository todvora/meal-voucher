package cz.tomasdvorak.mealvoucher;

public class VoucherCounter {

    private static final double VOUCHER_VALUE = 2.2d;

    public static VoucherCountResult compute(String priceText) {

        try {
            double price = Double.parseDouble(priceText);

            double divided = price / VOUCHER_VALUE;

            int underCount = (int) Math.floor(divided);
            int overCount = (int) Math.ceil(divided);

            double underPrice = price - underCount * VOUCHER_VALUE;
            double overPrice = price - overCount * VOUCHER_VALUE;

            return new VoucherCountResult(underCount, underPrice, underCount * VOUCHER_VALUE, overCount, overPrice, overCount * VOUCHER_VALUE);
        } catch (NumberFormatException ignored) {
            return new VoucherCountResult(0, 0, 0, 0, 0, 0);
        }
    }
}
