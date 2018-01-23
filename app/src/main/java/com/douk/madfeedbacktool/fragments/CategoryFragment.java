package com.douk.madfeedbacktool.fragments;

/**
 * Created by douk on 1/22/18.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.douk.madfeedbacktool.R;

public class CategoryFragment extends Fragment {
    /* the fragment argument representing the category number for this fragment */
    private static final String ARG_CATEGORY_NUMBER = "category_number";

    public CategoryFragment() {
        // required empty constructor
    }

    /* returns a new instance of this fragment for the given category number */
    public static CategoryFragment newInstance(int categoryNumber) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_CATEGORY_NUMBER, categoryNumber);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_feedback_quality, container, false);

        return rootView;
    }
}