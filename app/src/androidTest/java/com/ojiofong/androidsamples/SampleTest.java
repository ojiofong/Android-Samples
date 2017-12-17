package com.ojiofong.androidsamples;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.ojiofong.androidsamples.ui.BoundServiceActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by ojiofong on 6/21/17.
 *
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SampleTest {

    String testString;

    @Rule
    public ActivityTestRule<BoundServiceActivity> activityTestRule = new ActivityTestRule<>(BoundServiceActivity.class);


    @Before
    public void init(){
        testString = "Start service";
    }

    @Test
    public void doSomething(){

        onView(withId(R.id.start_service))
                .check(matches(withText(testString)));

    }

}
