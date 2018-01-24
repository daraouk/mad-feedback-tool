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
import com.douk.madfeedbacktool.activities.FeedbackActivity;

public class ValueFragment extends Fragment {

    public ValueFragment() {
        // empty constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_feedback_value, container, false);

        Button nextBtn = (Button) v.findViewById(R.id.category_submit_button);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((FeedbackActivity) getActivity()).setCurrentItem(3, true);
            }
        });

        return v;
    }

}