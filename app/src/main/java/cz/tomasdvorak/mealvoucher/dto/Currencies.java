package cz.tomasdvorak.mealvoucher.dto;


import java.text.NumberFormat;
import java.util.Collections;
import java.util.Currency;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public enum Currencies {
    INSTANCE;

    private final List<CurrencyEntry> entries;

    Currencies() {
        List<CurrencyEntry> entries = new LinkedList<>();
        Locale[] locs = Locale.getAvailableLocales();
        for (Locale locale : locs) {
            try {
                Currency currency = Currency.getInstance(locale);
                NumberFormat format = NumberFormat.getCurrencyInstance(locale);
                CurrencyEntry currencyEntry = new CurrencyEntry(currency, format);
                if (!entries.contains(currencyEntry)) {
                    entries.add(currencyEntry);
                }
            } catch (Exception exc) {
                // Locale not found
            }
        }
        Collections.sort(entries);
        this.entries = entries;
    }

    public CharSequence[] getEntries() {
        List<CharSequence> result = new LinkedList<>();
        for(CurrencyEntry entry : entries) {
            result.add(entry.toString());
        }
        return result.toArray(new CharSequence[result.size()]);
    }

    public CharSequence[] getEntryValues() {
        List<CharSequence> result = new LinkedList<>();
        for(CurrencyEntry entry : entries) {
            result.add(entry.getCode());
        }
        return result.toArray(new CharSequence[result.size()]);
    }

    public CurrencyEntry getCurrency(String currencyCode) {
        for(CurrencyEntry entry : entries) {
            if(entry.getCode().equals(currencyCode)) {
                return entry;
            }
        }
        return null;
    }


}
