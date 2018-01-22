package com.douk.madfeedbacktool.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

/**
 * Created by douk on 1/19/18.
 */

public class SplashActivity extends AppCompatActivity {
    // splash screen display time
    /* NOTE: it's not good practice to let the user wait for no reason,
    and the splash should only show until the app is fully loaded. this is
    added ONLY for demonstration purposes */
    private final int SPLASH_DISPLAY_TIME = 5000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* display splash screen for a certain length of time, then start activity */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_TIME);
    }
}