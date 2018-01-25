package com.douk.madfeedbacktool.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.douk.madfeedbacktool.R;
import com.douk.madfeedbacktool.adapters.FeedbackPagerAdapter;
import com.douk.madfeedbacktool.fragments.CreativityFragment;
import com.douk.madfeedbacktool.fragments.QualityFragment;
import com.douk.madfeedbacktool.fragments.SpeedFragment;
import com.douk.madfeedbacktool.fragments.StrategyFragment;
import com.douk.madfeedbacktool.fragments.ValueFragment;

public class FeedbackActivity extends AppCompatActivity {

    private FeedbackPagerAdapter mFeedbackPagerAdapter;
    private ViewPager mViewPager;
    CoordinatorLayout mCoordinator;

    SharedPreferences userTypePrefs;
    SharedPreferences.Editor editor;

    ImageView zero, one, two, three, four;
    ImageView[] indicators;

    int page = 0; // to track page position
    private String userSelectedType;
    private static String USER_TYPE_TITLE = "USER_SELECTED_TYPE";

    private static final String TAG = "FeedbackActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        // set up sharedpreferences
        userTypePrefs = this.getPreferences(Context.MODE_PRIVATE);
        editor = userTypePrefs.edit();

        // get selected user type from intent
        Bundle extras = getIntent().getExtras();

        // store to sharedpreferences
        if(extras == null) {
            userSelectedType = null;
            editor.putString(USER_TYPE_TITLE, userSelectedType);
            Log.i(TAG + "/" + USER_TYPE_TITLE, userSelectedType);
        } else {
            userSelectedType = extras.getString("USER_SELECTED_TYPE");
            editor.putString(USER_TYPE_TITLE, userSelectedType);
            Log.i(TAG + "/" + USER_TYPE_TITLE, userSelectedType);
        }

        editor.commit();

        // create the adapter that will return a fragment for each of the five
        // feedback categories of the activity
        mFeedbackPagerAdapter = new FeedbackPagerAdapter(getSupportFragmentManager());

        // add section fragments to adapter
        mFeedbackPagerAdapter.addSection(
                new QualityFragment(),
                getString(R.string.category_1_title)
        );
        mFeedbackPagerAdapter.addSection(
                new SpeedFragment(),
                getString(R.string.category_2_title)
        );
        mFeedbackPagerAdapter.addSection(
                new ValueFragment(),
                getString(R.string.category_3_title)
        );
        mFeedbackPagerAdapter.addSection(
                new CreativityFragment(),
                getString(R.string.category_4_title)
        );
        mFeedbackPagerAdapter.addSection(
                new StrategyFragment(),
                getString(R.string.category_5_title)
        );

        // initialize views
        zero = (ImageView) findViewById(R.id.intro_indicator_0);
        one = (ImageView) findViewById(R.id.intro_indicator_1);
        two = (ImageView) findViewById(R.id.intro_indicator_2);
        three = (ImageView) findViewById(R.id.intro_indicator_3);
        four = (ImageView) findViewById(R.id.intro_indicator_4);
        mCoordinator = (CoordinatorLayout) findViewById(R.id.feedback_evaluation);

        // set up indicators
        indicators = new ImageView[]{zero, one, two, three, four};

        // set up the ViewPager with the categories adapter
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mFeedbackPagerAdapter);

        // set current items
        setCurrentItem(page, false);
        updateIndicators(page);

        // on page change, update indicator to the current page
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // empty
            }

            @Override
            public void onPageSelected(int position) {
                page = position;
                updateIndicators(page);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // empty
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

    /* setCurrentItem() */
    public void setCurrentItem (int item, boolean smoothScroll) {
        mViewPager.setCurrentItem(item, smoothScroll);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
