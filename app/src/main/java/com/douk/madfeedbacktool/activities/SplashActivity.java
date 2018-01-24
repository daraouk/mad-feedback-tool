package com.douk.madfeedbacktool.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by douk on 1/19/18.
 */

public class SplashActivity extends AppCompatActivity {
    // splash screen display time
    /* NOTE: it's not good practice to let the user wait for no reason,
    and the splash should only show until the app is fully loaded. this is
    added ONLY for demonstration purposes */
    private final int SPLASH_DISPLAY_TIME = 500;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        /*** OPTION 1: Don't let the user wait, load MainActivity ASAP ***/
//        Intent mainIntent = new Intent(
//                SplashActivity.this, UserTypeSelectionActivity.class);
//        SplashActivity.this.startActivity(mainIntent);
//        SplashActivity.this.finish();

        /*** OPTION 2 (bad practice): Let splash show for 3 seconds, for branding purposes ***/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(
                        SplashActivity.this, UserTypeSelectionActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_TIME);
    }
}