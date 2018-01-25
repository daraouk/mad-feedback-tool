package com.douk.madfeedbacktool.fragments;

/**
 * Created by douk on 1/22/18.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.douk.madfeedbacktool.R;
import com.douk.madfeedbacktool.utils.SendMailTask;

public class StrategyFragment extends Fragment {

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
        Button nextBtn = (Button) v.findViewById(R.id.category_submit_button);

        final String emailSubject = "You have new feedback!";
        final String emailBody = "The feedback from the user will go here.";

        // complete feedback form and determine success/failure
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // send the email in the background
                new SendMailTask(getActivity()).execute(emailSubject, emailBody);
            }
        });

        return v;
    }

}