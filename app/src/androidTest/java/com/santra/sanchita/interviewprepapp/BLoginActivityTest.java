package com.santra.sanchita.interviewprepapp;

import android.content.pm.ActivityInfo;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.santra.sanchita.interviewprepapp.ui.login.LoginActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by sanchita on 14/6/18.
 */

@RunWith(AndroidJUnit4.class)
public class BLoginActivityTest {

    public BLoginActivityTest() {
        super();
    }

    @Rule
    public ActivityTestRule<LoginActivity> loginActivityActivityTestRule =
            new ActivityTestRule<>(LoginActivity.class);

    @Before
    public void setUp() {
    }

    @Test
    public void loginActivityDisplay() throws Exception {
        onView(withId(R.id.signInButton)).check(matches(isCompletelyDisplayed()));

        LowLevelActions.rotateScreen(loginActivityActivityTestRule.getActivity());

        onView(withId(R.id.signInButton)).check(matches(isCompletelyDisplayed()));

    }
}
