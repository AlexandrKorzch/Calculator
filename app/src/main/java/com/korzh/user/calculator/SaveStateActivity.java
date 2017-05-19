package com.korzh.user.calculator;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.Random;

import static com.korzh.user.calculator.Const.COLOR_KEY;
import static com.korzh.user.calculator.Const.DIGIT_KEY;
import static com.korzh.user.calculator.Const.TEXT_KEY;

public class SaveStateActivity extends AppCompatActivity {


    private static final String TAG = "SaveStateActivity";

    private View mView;
    private EditText mFieldEt;
    private int mColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mView = getLayoutInflater().inflate(R.layout.activity_save_state, null);
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });

        setRandomColor();

        mFieldEt = (EditText)mView.findViewById(R.id.et_field);

        setContentView(mView);

        if (savedInstanceState != null && savedInstanceState.containsKey(COLOR_KEY)) {
            mColor = savedInstanceState.getInt(COLOR_KEY);
            setColor(mColor);
        }
    }

    private void setRandomColor() {
        Random rnd = new Random();
        mColor = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        setColor(mColor);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(COLOR_KEY, mColor);
    }


    private void setColor(int color) {
        mView.setBackgroundColor(color);
    }


    public void start() {
        Intent starter = new Intent();
        starter.setAction("intent.action.CALCULATE");
        starter.putExtra(DIGIT_KEY, mFieldEt.getText().toString());
        starter.putExtra(COLOR_KEY, mColor);
        startActivity(starter);
    }


}
