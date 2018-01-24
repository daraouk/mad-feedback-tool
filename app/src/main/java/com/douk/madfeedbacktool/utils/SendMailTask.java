package com.douk.madfeedbacktool.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.douk.madfeedbacktool.activities.CompletedActivity;

import java.util.List;

public class SendMailTask extends AsyncTask {

    private Activity sendMailActivity;

    public SendMailTask(Activity activity) {
        sendMailActivity = activity;
    }

    @Override
    protected Object doInBackground(Object... args) {
        try {
            Log.i("SendMailTask", "About to instantiate GMail...");
            publishProgress("Processing input....");
            SendEmailHelper androidEmail = new SendEmailHelper(
                    args[0].toString(),
                    args[1].toString());
            publishProgress("Preparing mail message....");
            androidEmail.createEmailMessage();
            publishProgress("Sending email....");
            androidEmail.sendEmail();
            publishProgress("Email Sent.");
            Log.i("SendMailTask", "Mail Sent.");
        } catch (Exception e) {
            publishProgress(e.getMessage());
            Log.e("SendMailTask", e.getMessage(), e);
        }
        return null;
    }
}