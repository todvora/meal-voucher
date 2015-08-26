package cz.tomasdvorak.mealvoucher;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;

import cz.tomasdvorak.mealvoucher.dto.Currencies;

public class VouchersPreferencesActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new VouchersPreferenceFragment()).commit();
    }

    public static class VouchersPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);

            PreferenceScreen mPreferenceScreen = getPreferenceScreen();

            ListPreference listPref = (ListPreference) mPreferenceScreen.findPreference("voucherCurrency");
            if (listPref != null) {
                listPref.setEntries(Currencies.INSTANCE.getEntries());
                listPref.setEntryValues(Currencies.INSTANCE.getEntryValues());
            }
        }
    }
}
