package com.korzh.user.calculator.fragment.preferences;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.view.MenuItem;

import com.korzh.user.calculator.R;
import com.korzh.user.calculator.activity.SettingsActivity;

import static com.korzh.user.calculator.activity.SettingsActivity.bindPreferenceSummaryToValue;

/**
 * Created by alex on 5/20/17.
 */

public class NotificationPreferenceFragment  extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_notification);
        setHasOptionsMenu(true);
        bindPreferenceSummaryToValue(findPreference(getString(R.string.pref_rington)));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            startActivity(new Intent(getActivity(), SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}