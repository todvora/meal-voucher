package cz.tomasdvorak.mealvoucher.dto;

import android.support.annotation.NonNull;

import java.text.NumberFormat;
import java.util.Currency;

public class CurrencyEntry implements Comparable {
    private final Currency currency;
    private final NumberFormat numberFormat;

    public CurrencyEntry(Currency currency, NumberFormat numberFormat) {
        this.currency = currency;
        this.numberFormat = numberFormat;
    }

    public String getCode() {
        return currency.getCurrencyCode();
    }

    public String getSymbol() {
        return currency.getSymbol();
    }

    public NumberFormat getFormat() {
        return numberFormat;
    }

    @Override
    public int compareTo(@NonNull Object another) {
        return getCode().compareTo(((CurrencyEntry) another).getCode());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyEntry that = (CurrencyEntry) o;
        return currency.getCurrencyCode().equals(that.currency.getCurrencyCode());
    }

    @Override
    public int hashCode() {
        return currency.hashCode();
    }

    @Override
    public String toString() {
        if(getCode().equals(getSymbol())) {
            return getCode();
        } else {
            return getCode()+ "("+getSymbol()+")";
        }
    }
}