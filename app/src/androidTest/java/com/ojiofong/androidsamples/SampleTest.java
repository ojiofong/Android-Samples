package com.ojiofong.androidsamples;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.ojiofong.androidsamples.ui.BoundServiceActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

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
