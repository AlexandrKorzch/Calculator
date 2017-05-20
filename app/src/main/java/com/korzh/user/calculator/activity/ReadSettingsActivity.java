package com.korzh.user.calculator.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.korzh.user.calculator.R;

import static com.korzh.user.calculator.R.id.tv_pref_result;
import static com.korzh.user.calculator.R.layout.activity_read_settings;
import static com.korzh.user.calculator.general.Const.NO_RESULT;
import static com.korzh.user.calculator.general.Const.NO_RESULT_BOOL;
import static com.korzh.user.calculator.general.Navigator.startMain;

public class ReadSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_read_settings);

        ((TextView) findViewById(tv_pref_result)).setText(getResultString());
    }

    private String getResultString() {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        return String.format("%s - %s\n%s - %s\n%s - %s\n%s - %s\n%s - %s\n%s - %s\n%s - %s\n",
                getString(R.string.pref_title_social_recommendations),
                sharedPreferences.getBoolean(getString(R.string.general_switch), NO_RESULT_BOOL),
                getString(R.string.pref_title_display_name),
                sharedPreferences.getString(getString(R.string.pref_name), NO_RESULT),
                getString(R.string.pref_title_add_friends_to_messages),
                sharedPreferences.getString(getString(R.string.pref_list), NO_RESULT),
                getString(R.string.pref_title_new_message_notifications),
                sharedPreferences.getBoolean(getString(R.string.pref_notification), NO_RESULT_BOOL),
                getString(R.string.pref_title_ringtone),
                sharedPreferences.getString(getString(R.string.pref_rington), NO_RESULT),
                getString(R.string.pref_title_vibrate),
                sharedPreferences.getBoolean(getString(R.string.pref_vibrate), NO_RESULT_BOOL),
                getString(R.string.pref_title_sync_frequency),
                sharedPreferences.getString(getString(R.string.pref_sync_frequency), NO_RESULT));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startMain(this);
        finish();
    }

}
