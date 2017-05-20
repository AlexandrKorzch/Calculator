package com.korzh.user.calculator.general;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.korzh.user.calculator.activity.MainActivity;
import com.korzh.user.calculator.activity.ReadSettingsActivity;
import com.korzh.user.calculator.activity.SettingsActivity;

import static com.korzh.user.calculator.general.Const.ACTION_CALCULATE;
import static com.korzh.user.calculator.general.Const.CHOOSER_TITLE;
import static com.korzh.user.calculator.general.Const.COLOR_KEY;
import static com.korzh.user.calculator.general.Const.DIGIT_KEY;
import static com.korzh.user.calculator.general.Const.IMAGE;
import static com.korzh.user.calculator.general.Const.SELECT_IMAGE;

/**
 * Created by alex on 5/20/17.
 */

public class Navigator {

    public static void startCalculator(Context context, String text, int color) {
        Intent intent = new Intent();
        intent.setAction(ACTION_CALCULATE);
        intent.putExtra(DIGIT_KEY, text);
        intent.putExtra(COLOR_KEY, color);
        context.startActivity(intent);
    }

    public static void startGallery(Activity activity) {
        Intent intent = new Intent();
        intent.setType(IMAGE);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activity.startActivityForResult(Intent.createChooser(intent, CHOOSER_TITLE), SELECT_IMAGE);
    }

    public static void startPreferences(Context context) {
        Intent intent = new Intent(context, SettingsActivity.class);
        context.startActivity(intent);
    }

    public static void startReadSettings(Context context) {
        Intent starter = new Intent(context, ReadSettingsActivity.class);
        context.startActivity(starter);
    }

    public static void startMain(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }
}
