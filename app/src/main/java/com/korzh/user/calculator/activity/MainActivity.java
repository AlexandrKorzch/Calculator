package com.korzh.user.calculator.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.korzh.user.calculator.R;

import java.io.IOException;
import java.util.Random;

import static com.korzh.user.calculator.general.Const.ALPHA_MAX;
import static com.korzh.user.calculator.general.Const.BLUE_MAX;
import static com.korzh.user.calculator.general.Const.COLOR_KEY;
import static com.korzh.user.calculator.general.Const.GREEN_MAX;
import static com.korzh.user.calculator.general.Const.RED_MAX;
import static com.korzh.user.calculator.general.Const.SELECT_IMAGE;
import static com.korzh.user.calculator.general.Const.URI_KEY;
import static com.korzh.user.calculator.general.Navigator.startCalculator;
import static com.korzh.user.calculator.general.Navigator.startGallery;
import static com.korzh.user.calculator.general.Navigator.startPreferences;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private View mView;
    private EditText mFieldEt;
    private Button mChoseColorBt;
    private Button mGoToCalcBt;
    private Button mGoToGalleryBt;
    private Button mGotoPrefBt;
    private ImageView mPictureIv;
    private Uri mImageUri;
    private int mColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        initListeners();
        restoreActivityState(savedInstanceState);
    }

    private void initViews() {
        mView = getLayoutInflater().inflate(R.layout.activity_save_state, null);
        mFieldEt = (EditText) mView.findViewById(R.id.et_field);
        mGoToCalcBt = (Button) mView.findViewById(R.id.btn_goto_calc);
        mGoToGalleryBt = (Button) mView.findViewById(R.id.btn_goto_galery);
        mChoseColorBt = (Button) mView.findViewById(R.id.btn_chose_color);
        mGotoPrefBt = (Button) mView.findViewById(R.id.btn_goto_pref);
        mPictureIv = (ImageView) mView.findViewById(R.id.iv_picture);
        setContentView(mView);
    }

    private void initListeners() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_chose_color: {
                        setRandomBackgroundColor();
                        break;
                    }
                    case R.id.btn_goto_calc: {
                        startCalculator(MainActivity.this, mFieldEt.getText().toString(), mColor);
                        break;
                    }
                    case R.id.btn_goto_galery: {
                        startGallery(MainActivity.this);
                        break;
                    }
                    case R.id.btn_goto_pref: {
                        startPreferences(MainActivity.this);
                    }
                }
            }
        };

        mGoToCalcBt.setOnClickListener(listener);
        mGoToGalleryBt.setOnClickListener(listener);
        mChoseColorBt.setOnClickListener(listener);
        mGotoPrefBt.setOnClickListener(listener);
    }

    private void restoreActivityState(Bundle savedInstanceState) {
        Log.d(TAG, "restoreActivityState() called with: savedInstanceState = [" + savedInstanceState + "]");
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(COLOR_KEY)) {
                mColor = savedInstanceState.getInt(COLOR_KEY);
                Log.d(TAG, "restoreActivityState: mColor - "+mColor);
                mView.setBackgroundColor(mColor);
            }
            if (savedInstanceState.containsKey(URI_KEY)) {
                mImageUri = savedInstanceState.getParcelable(URI_KEY);
                showPicture();
            }
        } else {
            setRandomBackgroundColor();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: mColor - "+mColor);
        outState.putInt(COLOR_KEY, mColor);
        outState.putParcelable(URI_KEY, mImageUri);
    }

    private void setRandomBackgroundColor() {
        Random rnd = new Random();
        mColor = Color.argb(rnd.nextInt(ALPHA_MAX + 1), rnd.nextInt(RED_MAX + 1),
                rnd.nextInt(GREEN_MAX + 1), rnd.nextInt(BLUE_MAX + 1));
        Log.d(TAG, "setRandomBackgroundColor: mColor - "+mColor);
        mView.setBackgroundColor(mColor);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == SELECT_IMAGE && data != null) {
            mImageUri = data.getData();
            showPicture();
        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, R.string.canceled, Toast.LENGTH_SHORT).show();
        }
    }

    private void showPicture() {
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), mImageUri);
            mPictureIv.setImageBitmap(bitmap);
        } catch (IOException | NullPointerException e ) {
            e.printStackTrace();
        }
    }
}
