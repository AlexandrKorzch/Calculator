package com.korzh.user.calculator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "Intent";
    public static final int REQUEST_CODE = 356;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.w(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button clickBt = (Button) findViewById(R.id.btn_click);
        clickBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewActivity(SecondActivity.this);
            }
        });
    }

    public static void startNewActivity(Activity context) {
        Intent starter = new Intent(context, ThirdActivity.class);
        context.startActivityForResult(starter, REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult() called with: " +
                "requestCode = [" + requestCode + "], " +
                "resultCode = [" + resultCode + "], " +
                "data = [" + data + "]");

        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            Log.d(TAG, "onActivityResult: success");
        }
    }

    @Override
    protected void onRestart() {
        Log.w(TAG, "onRestart: ");
        super.onRestart();
    }

    @Override
    protected void onStart() {
        Log.w(TAG, "onStart: ");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.w(TAG, "onResume: ");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.w(TAG, "onPause: ");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.w(TAG, "onStop: ");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.w(TAG, "onDestroy: ");
        super.onDestroy();
    }
    
    
    
}
