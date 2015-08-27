package cz.tomasdvorak.mealvoucher;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import cz.tomasdvorak.mealvoucher.components.TextWatcherAdapter;
import cz.tomasdvorak.mealvoucher.dto.Currencies;
import cz.tomasdvorak.mealvoucher.dto.CurrencyEntry;

public class MainActivity extends AppCompatActivity {

    public static final String VOUCHER_CURRENCY_PREFERENCE = "voucherCurrency";
    public static final String VOUCHER_VALUE_PREFERENCE = "voucherValue";

    private SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener;
    private VoucherCounter voucherCounter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setActivityTexts();
        setVoucherCounter();


        final EditText myTextBox = (EditText) findViewById(R.id.price);
        myTextBox.requestFocus();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        onSharedPreferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                setActivityTexts();
                setVoucherCounter();
                recompute(myTextBox.getText());
            }
        };
        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);

        myTextBox.addTextChangedListener(new TextWatcherAdapter() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                recompute(s);
            }
        });
    }

    private void recompute(CharSequence s) {
        VoucherCountResult result = voucherCounter.compute(String.valueOf(s));
        ((TextView) findViewById(R.id.underCount)).setText("" + result.getUnderCount());
        ((TextView) findViewById(R.id.underPrice)).setText(result.getUnderPrice());
        ((TextView) findViewById(R.id.underVoucherSum)).setText(result.getUnderVoucherSum());
        ((TextView) findViewById(R.id.overCount)).setText("" + result.getOverCount());
        ((TextView) findViewById(R.id.overPrice)).setText(result.getOverPrice());
        ((TextView) findViewById(R.id.overVoucherSum)).setText(result.getOverVoucherSum());
    }

    private void setVoucherCounter() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String currencyCode = prefs.getString(VOUCHER_CURRENCY_PREFERENCE, "EUR");
        String voucherValue = prefs.getString(VOUCHER_VALUE_PREFERENCE, "2.2");
        CurrencyEntry currency = Currencies.INSTANCE.getCurrency(currencyCode);
        this.voucherCounter = new VoucherCounter(currency, Double.parseDouble(voucherValue));
    }

    private void setActivityTexts() {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String currencyCode = prefs.getString(VOUCHER_CURRENCY_PREFERENCE, "EUR");

        String text = getString(R.string.promptEnterPrice);
        ((TextView) findViewById(R.id.promptEnterPrice)).setText(String.format(text, currencyCode));

        String voucherValue = prefs.getString(VOUCHER_VALUE_PREFERENCE, "2.2");
        CurrencyEntry currency = Currencies.INSTANCE.getCurrency(currencyCode);
        String formattedValue = currency.getFormat().format(Double.valueOf(voucherValue));
        String labelString = getString(R.string.oneVoucherValueIs);
        ((TextView) findViewById(R.id.oneVoucherValueLabel)).setText(String.format(labelString, formattedValue));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(this, VouchersPreferencesActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
