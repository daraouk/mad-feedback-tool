package com.douk.madfeedbacktool.activities;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.douk.madfeedbacktool.R;
import com.douk.madfeedbacktool.adapters.FeedbackPagerAdapter;
import com.douk.madfeedbacktool.fragments.CreativityFragment;
import com.douk.madfeedbacktool.fragments.QualityFragment;
import com.douk.madfeedbacktool.fragments.SectionOneFragment;
import com.douk.madfeedbacktool.fragments.SectionThreeFragment;
import com.douk.madfeedbacktool.fragments.SectionTwoFragment;
import com.douk.madfeedbacktool.fragments.SpeedFragment;
import com.douk.madfeedbacktool.fragments.StrategyFragment;
import com.douk.madfeedbacktool.fragments.ValueFragment;

public class FeedbackActivity extends AppCompatActivity {

    private FeedbackPagerAdapter mFeedbackPagerAdapter;
    private ViewPager mViewPager;
    CoordinatorLayout mCoordinator;

    ImageView zero, one, two, three, four;
    ImageView[] indicators;

    int page = 0; // to track page position
    static final String TAG = "FeedbackActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

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
                getString(R.string.category_1_title)
        );
        mFeedbackPagerAdapter.addSection(
                new ValueFragment(),
                getString(R.string.category_1_title)
        );
        mFeedbackPagerAdapter.addSection(
                new CreativityFragment(),
                getString(R.string.category_1_title)
        );
        mFeedbackPagerAdapter.addSection(
                new StrategyFragment(),
                getString(R.string.category_1_title)
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
}
