package org.zky.zky;

import android.app.FragmentTransaction;
import android.content.ComponentName;
import android.content.Intent;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.support.annotation.Nullable;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import org.zky.zky.utils.GetText;
import org.zky.zky.utils.PreferenceUtils;

public class SettingsActivity extends AppCompatActivity {
    private static final String TAG = "SettingsActivity";

    private static MyPreferenceFragment fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

    }

    public static class MyPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_general);
            ListPreference preference = (ListPreference) findPreference(GetText.getString(R.string.key_theme));
            preference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    Log.e(TAG, "onPreferenceChange: newvalue:" + newValue.toString());
                        PreferenceUtils.setTheme(getActivity(), (String) newValue);
                    ((SettingsActivity) getActivity()).recreateActivity();
                    return false;
                }
            });
        }

    }

    public void recreateActivity() {
        final Intent intent = IntentCompat.makeMainActivity(new ComponentName(this, MainActivity.class));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
