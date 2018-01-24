package com.douk.madfeedbacktool.fragments;

/**
 * Created by douk on 1/22/18.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.douk.madfeedbacktool.R;
import com.douk.madfeedbacktool.activities.CompletedActivity;
import com.douk.madfeedbacktool.activities.OnboardingActivity;
import com.douk.madfeedbacktool.activities.UserTypeSelectionActivity;

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

        // complete feedback form and determine success/failure
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent completedIntent = new Intent(
                        getContext(), CompletedActivity.class);
                startActivity(completedIntent);

                getActivity().finish();
            }
        });

        return v;
    }

}