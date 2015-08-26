package cz.tomasdvorak.mealvoucher;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText myTextBox = (EditText) findViewById(R.id.price);
        myTextBox.requestFocus();


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        myTextBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                VoucherCountResult result = VoucherCounter.compute(String.valueOf(s));
                ((TextView) findViewById(R.id.underCount)).setText("" + result.getUnderCount());
                ((TextView) findViewById(R.id.underPrice)).setText("" + String.format("%.2f", result.getUnderPrice()));
                ((TextView) findViewById(R.id.underVoucherSum)).setText("" + String.format("%.2f", result.getUnderVoucherSum()));
                ((TextView) findViewById(R.id.overCount)).setText("" + result.getOverCount());
                ((TextView) findViewById(R.id.overPrice)).setText("" + String.format("%.2f", Math.abs(result.getOverPrice())));
                ((TextView) findViewById(R.id.overVoucherSum)).setText("" + String.format("%.2f", result.getOverVoucherSum()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
