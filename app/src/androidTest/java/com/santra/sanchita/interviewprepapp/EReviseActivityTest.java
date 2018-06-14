package com.santra.sanchita.interviewprepapp;

import android.content.pm.ActivityInfo;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.santra.sanchita.interviewprepapp.ui.revise.ReviseActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by sanchita on 14/6/18.
 */

@RunWith(AndroidJUnit4.class)
public class EReviseActivityTest {

    public EReviseActivityTest() {
        super();
    }

    @Rule
    public ActivityTestRule<ReviseActivity> reviseActivityActivityTestRule =
            new ActivityTestRule<>(ReviseActivity.class);

    @Before
    public void setUp() {
    }

    @Test
    public void reviseActivityDisplay() throws Exception {
        LowLevelActions.rotateScreen(reviseActivityActivityTestRule.getActivity());
    }
}
