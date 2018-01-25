package com.douk.madfeedbacktool.utils;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmailHelper {

    final String emailPort = "587";// gmail's smtp port
    final String smtpAuth = "true";
    final String starttls = "true";
    final String emailHost = "smtp.gmail.com";

    String fromEmail = "feedback.madcambodia@gmail.com";
    String fromPassword = "Master@mad1";
    List<String> toEmailList = new ArrayList<>();
    String emailSubject;
    String emailBody;

    Properties emailProperties;
    Session mailSession;
    MimeMessage emailMessage;

    private static final String TAG = "SendEmailHelper";

    public SendEmailHelper(String emailSubject, String emailBody) {
        this.emailSubject = emailSubject;
        this.emailBody = emailBody;
        toEmailList.addAll(Arrays.asList(
                "ouk.dara@outlook.com",
                "kit@workwithmad.com"
        ));
//        /***** ENABLE FOR LIVE *****/
//        toEmailList.addAll(Arrays.asList(
//                "ouk.dara@outlook.com",
//                "erika@workwithmad.com",
//                "manny@workwithmad.com",
//                "parker@workwithmad.com",
//                "kit@workwithmad.com",
//                "uysimty@workwithmad.com"
//        ));

        emailProperties = System.getProperties();
        emailProperties.put("mail.smtp.port", emailPort);
        emailProperties.put("mail.smtp.auth", smtpAuth);
        emailProperties.put("mail.smtp.starttls.enable", starttls);
        Log.i(TAG, "Mail server properties set.");
    }

    public MimeMessage createEmailMessage() throws AddressException,
            MessagingException, UnsupportedEncodingException {

        mailSession = Session.getDefaultInstance(emailProperties, null);
        emailMessage = new MimeMessage(mailSession);

        emailMessage.setFrom(new InternetAddress(fromEmail, fromEmail));
        for (String toEmail : toEmailList) {
            Log.i(TAG,"toEmail: "+toEmail);
            emailMessage.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(toEmail));
        }

        emailMessage.setSubject(emailSubject);
        emailMessage.setContent(emailBody, "text/html");// for a html email
        // emailMessage.setText(emailBody);// for a text email
        Log.i(TAG, "Email Message created.");
        return emailMessage;
    }

    public void sendEmail() throws AddressException, MessagingException {
        Transport transport = mailSession.getTransport("smtp");
        transport.connect(emailHost, fromEmail, fromPassword);
        Log.i(TAG,"allrecipients: " + emailMessage.getAllRecipients());
        transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
        transport.close();
        Log.i(TAG, "Email sent successfully.");
    }
}