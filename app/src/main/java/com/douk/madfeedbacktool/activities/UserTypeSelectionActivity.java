package com.douk.madfeedbacktool.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.douk.madfeedbacktool.R;
import com.douk.madfeedbacktool.utils.SendMailTask;
import com.douk.madfeedbacktool.utils.SharedPreferencesHelper;

public class UserTypeSelectionActivity extends AppCompatActivity {
    public static final String PREF_USER_FIRST_TIME = "user_first_time";
    boolean isUserFirstTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // if this is the users first time launching the app,
        // show the onboarding tutorial
        isUserFirstTime = Boolean.valueOf(SharedPreferencesHelper
                .readSharedSetting(
                        UserTypeSelectionActivity.this, PREF_USER_FIRST_TIME, "true"));

        Intent userTypeSelectionIntent = new Intent(UserTypeSelectionActivity.this,
                OnboardingActivity.class);
        userTypeSelectionIntent.putExtra(PREF_USER_FIRST_TIME, isUserFirstTime);

        //if (isUserFirstTime)
            startActivity(userTypeSelectionIntent);

        // set content view
        setContentView(R.layout.activity_user_type_selection);

        // move to next activity on client btn click
        Button clientBtn = (Button) findViewById(R.id.client_button);

        clientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent feedbackIntent = new Intent(UserTypeSelectionActivity.this,
                        FeedbackActivity.class);
                startActivity(feedbackIntent);
            }
        });

    }
}
