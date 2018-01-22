package com.douk.madfeedbacktool.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.douk.madfeedbacktool.R;
import com.douk.madfeedbacktool.util.SharedPreferencesHelper;

public class MainActivity extends AppCompatActivity {
    public static final String PREF_USER_FIRST_TIME = "user_first_time";
    boolean isUserFirstTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // if this is the users first time launching the app,
        // show the onboarding tutorial
        isUserFirstTime = Boolean.valueOf(SharedPreferencesHelper
                .readSharedSetting(MainActivity.this, PREF_USER_FIRST_TIME, "true"));

        Intent introIntent = new Intent(MainActivity.this, OnboardingActivity.class);
        introIntent.putExtra(PREF_USER_FIRST_TIME, isUserFirstTime);

        if (isUserFirstTime)
            startActivity(introIntent);

        setContentView(R.layout.activity_main);
    }
}
