package com.douk.madfeedbacktool.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.douk.madfeedbacktool.activities.CompletedActivity;

public class SendMailTask extends AsyncTask {

    private ProgressDialog statusDialog;
    private Activity sendMailActivity;
    boolean isMailSentSuccessfully;

    private static final String TAG = "SendMailTask";

    public SendMailTask(Activity activity) { sendMailActivity = activity; }

    protected void onPreExecute() {
        statusDialog = new ProgressDialog(sendMailActivity);
        statusDialog.setMessage("Getting ready...");
        statusDialog.setIndeterminate(false);
        statusDialog.setCancelable(false);
        statusDialog.show();
    }

    @Override
    protected Object doInBackground(Object... args) {
        try {
            Log.i(TAG, "About to instantiate SendMailHelper...");
            publishProgress("Processing input....");
            SendEmailHelper androidEmail = new SendEmailHelper(
                    args[0].toString(),
                    args[1].toString());
            publishProgress("Preparing mail message....");
            androidEmail.createEmailMessage();
            publishProgress("Sending email....");
            androidEmail.sendEmail();
            publishProgress("Email Sent.");
            Log.i(TAG, "Mail Sent.");
            isMailSentSuccessfully = true;
        } catch (Exception e) {
            publishProgress(e.getMessage());
            Log.e(TAG, e.getMessage(), e);
            isMailSentSuccessfully = false;
        }
        return null;
    }

    @Override
    public void onProgressUpdate(Object... values) {
        statusDialog.setMessage(values[0].toString());
    }

    @Override
    public void onPostExecute(Object result) {
        statusDialog.dismiss();

        if (isMailSentSuccessfully) {
            Log.i(TAG, "Delivery successful.");
            Intent completedIntent = new Intent(sendMailActivity, CompletedActivity.class);
            sendMailActivity.startActivity(completedIntent);
        } else {
            Log.i(TAG, "Delivery failed.");
            Intent completedIntent = new Intent(sendMailActivity, CompletedActivity.class);
            sendMailActivity.startActivity(completedIntent);
        }
    }
}