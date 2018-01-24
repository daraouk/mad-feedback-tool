package com.douk.madfeedbacktool.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.douk.madfeedbacktool.R;

public class CompletedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed);

        Button closeBtn = (Button) findViewById(R.id.close_button);

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // finish the activity
                finish();

                // NOTE: Android architecture discourages actually "exiting" the app
                //System.exit(0);
            }
        });
    }
}