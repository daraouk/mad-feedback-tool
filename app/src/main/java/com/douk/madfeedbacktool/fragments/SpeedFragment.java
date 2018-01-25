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
import com.douk.madfeedbacktool.activities.FeedbackActivity;

public class SpeedFragment extends Fragment {

    RatingBar ratingBar;
    EditText commentsText;

    SharedPreferences userFeedback;
    SharedPreferences.Editor feedbackEditor;

    private static String RATING_TITLE = "SPEED_RATING";
    private static String COMMENTS_TITLE = "SPEED_COMMENTS";

    public SpeedFragment() {
        // empty constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_feedback_speed, container, false);

        ratingBar = (RatingBar) v.findViewById(R.id.rating_bar);
        commentsText = (EditText) v.findViewById(R.id.category_comments_textbox);

        Button nextBtn = (Button) v.findViewById(R.id.category_submit_button);

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

                // move to next category
                ((FeedbackActivity) getActivity()).setCurrentItem(2, true);
            }
        });

        return v;
    }

}