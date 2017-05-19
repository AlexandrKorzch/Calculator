package com.korzh.user.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import bsh.EvalError;
import bsh.Interpreter;

import static com.korzh.user.calculator.Const.DIGIT_KEY;
import static com.korzh.user.calculator.Const.RESULT;
import static com.korzh.user.calculator.Const.TEXT_KEY;

public class CalculatorActivity extends AppCompatActivity {

    private static final String TAG = "CalculatorActivity";
    private TextView mFieldTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFieldTv = (TextView) findViewById(R.id.tv_field);
        if (savedInstanceState != null && savedInstanceState.containsKey(TEXT_KEY)) {
            mFieldTv.setText(savedInstanceState.getString(TEXT_KEY));
        }
        getDigit();
    }

    private void getDigit() {
        Intent intent = getIntent();
        Bundle intentBundle = intent.getExtras();
        if(intentBundle != null){
            String digit = intentBundle.getString(DIGIT_KEY);
            mFieldTv.setText(digit);
        }
    }

    public void buttonClick(View view) {
        switch (view.getId()) {
            case R.id.btn_equals: {
                calculate();
                break;
            }
            case R.id.btn_del: {
                clear();
                break;
            }
            default: {
                write((Button) view);
            }
        }
    }

    private void write(Button view) {
        mFieldTv.append(view.getText().toString());
    }

    private void clear() {
        try {
            mFieldTv.setText(mFieldTv.getText().toString().substring(0, mFieldTv.length() - 1));
        } catch (StringIndexOutOfBoundsException ex) {
            Toast.makeText(this, R.string.field_is_empty, Toast.LENGTH_SHORT).show();
        }
    }

    private void calculate() {
        Interpreter interpreter = new Interpreter();
        try {
            interpreter.eval(RESULT + " = " + mFieldTv.getText().toString());
            mFieldTv.setText(String.valueOf(interpreter.get(RESULT)));
        } catch (EvalError evalError) {
            Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TEXT_KEY, mFieldTv.getText().toString());
    }
}
