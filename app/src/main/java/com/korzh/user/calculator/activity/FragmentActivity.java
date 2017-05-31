package com.korzh.user.calculator.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.korzh.user.calculator.R;
import com.korzh.user.calculator.fragment.FirstFragment;
import com.korzh.user.calculator.fragment.SecondFragment;
import com.korzh.user.calculator.fragment.ThirdFragment;

public class FragmentActivity extends AppCompatActivity {

    private static final String TAG = "Fragment";
    private static final int PAGE_COUNT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        final ViewPager pager = (ViewPager)findViewById(R.id.pager);
        MyFragmentPagerAdapter pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);

        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;

                while(i<4){
                    if(i == 3){
                        i = -1;
                    }
                    try {
                        Thread.sleep(1500);
                        i++;
                        final int finalI = i;
                        pager.post(new Runnable() {
                            @Override
                            public void run() {
                                pager.setCurrentItem(finalI);
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();

    }


    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch(position){

                case 0:{
                    return FirstFragment.newInstance();
                }
                case 1:{
                    return SecondFragment.newInstance();
                }
                case 2:{
                    return ThirdFragment.newInstance();
                }
            }
            return null;
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }
    }
}
