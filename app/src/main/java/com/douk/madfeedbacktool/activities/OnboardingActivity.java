package com.douk.madfeedbacktool.activities;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.douk.madfeedbacktool.R;
import com.douk.madfeedbacktool.adapters.OnboardingPagerAdapter;
import com.douk.madfeedbacktool.fragments.SectionOneFragment;
import com.douk.madfeedbacktool.fragments.SectionThreeFragment;
import com.douk.madfeedbacktool.fragments.SectionTwoFragment;
import com.douk.madfeedbacktool.utils.SharedPreferencesHelper;

public class OnboardingActivity extends AppCompatActivity {

    private OnboardingPagerAdapter mOnboardingPagerAdapter;
    private ViewPager mViewPager;
    CoordinatorLayout mCoordinator;

    ImageButton mNextBtn;
    Button mSkipBtn, mFinishBtn;
    ImageView zero, one, two;
    ImageView[] indicators;

    int page = 0; // to track page position
    static final String TAG = "OnboardingActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        // create the adapter that will return a fragment for each of the three
        // primary sections of the activity
        mOnboardingPagerAdapter = new OnboardingPagerAdapter(getSupportFragmentManager());

        // add section fragments to adapter
        mOnboardingPagerAdapter.addSection(
                new SectionOneFragment(),
                getString(R.string.section_1_title),
                getString(R.string.section_1_desc)
        );
        mOnboardingPagerAdapter.addSection(
                new SectionTwoFragment(),
                getString(R.string.section_2_title),
                getString(R.string.section_2_desc)
        );
        mOnboardingPagerAdapter.addSection(
                new SectionThreeFragment(),
                getString(R.string.section_3_title),
                getString(R.string.section_3_desc)
        );

        // initialize views
        mNextBtn = (ImageButton) findViewById(R.id.intro_btn_next);
        mSkipBtn = (Button) findViewById(R.id.intro_btn_skip);
        mFinishBtn = (Button) findViewById(R.id.intro_btn_finish);
        zero = (ImageView) findViewById(R.id.intro_indicator_0);
        one = (ImageView) findViewById(R.id.intro_indicator_1);
        two = (ImageView) findViewById(R.id.intro_indicator_2);
        mCoordinator = (CoordinatorLayout) findViewById(R.id.main_content);

        // set up indicators
        indicators = new ImageView[]{zero, one, two};

        // set up the ViewPager with the sections adapter
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mOnboardingPagerAdapter);

        mViewPager.setCurrentItem(page);
        updateIndicators(page);

        // set up background color change on page change
        final int color_cyan = ContextCompat.getColor(this, R.color.cyan);
        final int color_orange = ContextCompat.getColor(this, R.color.orange);
        final int color_green = ContextCompat.getColor(this, R.color.green);

        final int[] colorList = new int[]{color_cyan, color_orange, color_green};

        final ArgbEvaluator evaluator = new ArgbEvaluator();

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // update background color on page scroll
                int colorUpdate = (Integer) evaluator
                        .evaluate(positionOffset, colorList[position],
                                colorList[position == 2 ? position : position + 1]);
                mViewPager.setBackgroundColor(colorUpdate);
            }

            @Override
            public void onPageSelected(int position) {
                page = position;
                updateIndicators(page);

                switch (position) {
                    case 0:
                        mViewPager.setBackgroundColor(color_cyan);
                        break;
                    case 1:
                        mViewPager.setBackgroundColor(color_orange);
                        break;
                    case 2:
                        mViewPager.setBackgroundColor(color_green);
                        break;
                }

                mNextBtn.setVisibility(position == 2 ? View.GONE : View.VISIBLE);
                mFinishBtn.setVisibility(position == 2 ? View.VISIBLE : View.GONE);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // empty
            }
        });

        // set onclick listeners
        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page += 1;
                mViewPager.setCurrentItem(page, true);
            }
        });

        mSkipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mFinishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //  update 1st time pref
                SharedPreferencesHelper
                        .saveSharedSetting(OnboardingActivity.this,
                                UserTypeSelectionActivity.PREF_USER_FIRST_TIME, "false");
            }
        });
    }

    /* updateIndicators() */
    void updateIndicators(int position) {
        for (int i = 0; i < indicators.length; i++) {
            indicators[i].setBackgroundResource(
                    i == position ? R.drawable.indicator_selected : R.drawable.indicator_unselected
            );
        }
    }
}
