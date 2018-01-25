package com.douk.madfeedbacktool.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.douk.madfeedbacktool.R;

public class CompletedActivity extends AppCompatActivity {

    ImageView successErrorIcon;
    TextView successErrorText, successErrorMessage;

    Bundle extras;

    boolean wasMailSuccess;

    private static final String TAG = "CompletedActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed);

        successErrorIcon = (ImageView) findViewById(R.id.success_error_icon);
        successErrorText = (TextView) findViewById(R.id.success_error_text);
        successErrorMessage = (TextView) findViewById(R.id.success_error_message);

        // get whether mail was successful from previous intent
        Bundle extras = getIntent().getExtras();

        if(extras != null) {
            wasMailSuccess = extras.getBoolean("MAIL_SUCCESS");
            Log.i(TAG, "wasMailSuccess: " + wasMailSuccess);

            // adjust the view according to success or error
            if (wasMailSuccess) {
                successErrorIcon.setImageResource(R.drawable.success);
                successErrorText.setText(R.string.success_title);
                successErrorMessage.setText(R.string.success_message);
            } else {
                successErrorIcon.setImageResource(R.drawable.error);
                successErrorText.setText(R.string.error_title);
                successErrorMessage.setText(R.string.error_message);
            }
        } else {
            wasMailSuccess = false;
            Log.i(TAG, "Extras are empty.");
        }

        Button closeBtn = (Button) findViewById(R.id.close_button);

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // finish the activity
                finish();
            }
        });
    }
}