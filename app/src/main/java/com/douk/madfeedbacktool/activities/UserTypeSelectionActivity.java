package com.douk.madfeedbacktool.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.douk.madfeedbacktool.R;
import com.douk.madfeedbacktool.utils.SharedPreferencesHelper;

public class UserTypeSelectionActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String PREF_USER_FIRST_TIME = "user_first_time";
    boolean isUserFirstTime;

    private static final String TAG = "UserTypeSelection";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // check if this is the users first time using the app
        isUserFirstTime = Boolean.valueOf(SharedPreferencesHelper
                .readSharedSetting(
                        UserTypeSelectionActivity.this, PREF_USER_FIRST_TIME, "true"));

        // ** for testing
        // isUserFirstTime = true;

        // if yes, launch onboarding activity
        if (isUserFirstTime) {
            Log.i(TAG, "isUserFirstTime: true");
            Intent onboardingIntent = new Intent(UserTypeSelectionActivity.this,
                    OnboardingActivity.class);
            onboardingIntent.putExtra(PREF_USER_FIRST_TIME, isUserFirstTime);
            startActivity(onboardingIntent);
        } else {
            Log.i(TAG, "isUserFirstTime: false");
        }

        // set content view
        setContentView(R.layout.activity_user_type_selection);

        // move to next activity on client btn click
        Button clientBtn = (Button) findViewById(R.id.client_button);
        Button employeeBtn = (Button) findViewById(R.id.employee_button);

        clientBtn.setOnClickListener(this);
        employeeBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent feedbackIntent = new Intent(this, FeedbackActivity.class);

        /* check which button the user clicked and put extras for intent */
        switch(view.getId())
        {
            case R.id.client_button:
                Log.i(TAG, "Client button selected.");
                feedbackIntent.putExtra("USER_SELECTED_TYPE", "Client");
                break;
            case R.id.employee_button:
                Log.i(TAG, "Employee button selected.");
                feedbackIntent.putExtra("USER_SELECTED_TYPE", "Employee");
                break;
        }

        startActivity(feedbackIntent);
        finish();
    }
}

