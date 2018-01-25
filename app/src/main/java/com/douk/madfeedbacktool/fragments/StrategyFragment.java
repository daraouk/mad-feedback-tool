package com.douk.madfeedbacktool.fragments;

/**
 * Created by douk on 1/22/18.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.douk.madfeedbacktool.R;
import com.douk.madfeedbacktool.utils.SendMailTask;

public class StrategyFragment extends Fragment {

    RatingBar ratingBar;
    EditText commentsText;

    SharedPreferences userFeedback;
    SharedPreferences.Editor feedbackEditor;

    private static String RATING_TITLE = "STRATEGY_RATING";
    private static String COMMENTS_TITLE = "STRATEGY_COMMENTS";

    // hold ALL of the users feedback from the previous fragments
    String userType;
    String qualityRating, speedRating, valueRating, creativityRating, strategyRating;
    String qualityComments, speedComments, valueComments, creativityComments, strategyComments;

    // email details
    String emailSubject = "You have new feedback!";
    String emailBody = "";

    public StrategyFragment() {
        // empty constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_feedback_strategy, container, false);

        ratingBar = (RatingBar) v.findViewById(R.id.rating_bar);
        commentsText = (EditText) v.findViewById(R.id.category_comments_textbox);

        Button nextBtn = (Button) v.findViewById(R.id.category_submit_button);

        // complete feedback form and determine success/failure
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // use sharedpreferences to save user data
                userFeedback = getActivity().getPreferences(Context.MODE_PRIVATE);
                feedbackEditor = userFeedback.edit();

                feedbackEditor.putFloat(RATING_TITLE, ratingBar.getRating());
                Log.i(RATING_TITLE, String.valueOf(ratingBar.getRating()));

                feedbackEditor.putString(COMMENTS_TITLE, commentsText.getText().toString());
                Log.i(COMMENTS_TITLE, commentsText.getText().toString());

                feedbackEditor.commit();

                // combine all of the user feedback and format it into a string
                emailBody = combineUserFeedback();

                // send the email in the background
                new SendMailTask(getActivity()).execute(emailSubject, emailBody);
            }
        });

        return v;
    }

    private String combineUserFeedback() {
        userType = userFeedback.getString("USER_SELECTED_TYPE", "empty");

        qualityRating = String.valueOf(userFeedback.getFloat("QUALITY_RATING", 0));
        qualityComments = userFeedback.getString("QUALITY_COMMENTS", "empty");

        speedRating = String.valueOf(userFeedback.getFloat("SPEED_RATING", 0));
        speedComments = userFeedback.getString("SPEED_COMMENTS", "empty");

        valueRating = String.valueOf(userFeedback.getFloat("VALUE_RATING", 0));
        valueComments = userFeedback.getString("VALUE_COMMENTS", "empty");

        creativityRating = String.valueOf(userFeedback.getFloat("CREATIVITY_RATING", 0));
        creativityComments = userFeedback.getString("CREATIVITY_COMMENTS", "empty");

        strategyRating = String.valueOf(userFeedback.getFloat("STRATEGY_RATING", 0));
        strategyComments = userFeedback.getString("STRATEGY_COMMENTS", "empty");

        String combined =
                "<html><body>" +
                "Dear Mad Team,<br><br>" +
                "You've just received some new feedback from a(n) " + userType + "!<br><br>" +
                "<b>Quality:</b> " + qualityRating + "<br>" +
                        "Comments: " + qualityComments + "<br><br>" +
                "<b>Speed:</b> " + speedRating + "<br>" +
                        "Comments: " + speedComments + "<br><br>" +
                "<b>Value:</b> " + valueRating + "<br>" +
                        "Comments: " + valueComments + "<br><br>" +
                "<b>Creativity:</b> " + creativityRating + "<br>" +
                        "Comments: " + creativityComments + "<br><br>" +
                "<b>Strategy:</b> " + strategyRating + "<br>" +
                        "Comments: " + strategyComments + "<br><br><br>" +
                "Sent By:<br>" +
                "Mad Feedback App" +
                "</body></html>";

        return combined;
    }
}