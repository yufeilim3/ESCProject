package com.example.escproject;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SignUpActivityTest {
	
	@Rule
	public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);
	
	@Test
	public void signUpActivityTest() {
		// Added a sleep statement to match the app's execution delay.
		// The recommended way to handle such scenarios is to use Espresso idling resources:
		// https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		ViewInteraction appCompatTextView = onView(
				allOf(withId(R.id.link_signup), withText("No account yet? Sign up"),
						childAtPosition(
								childAtPosition(
										withClassName(is("android.widget.ScrollView")),
										0),
								5)));
		appCompatTextView.perform(scrollTo(), click());
		
		// Added a sleep statement to match the app's execution delay.
		// The recommended way to handle such scenarios is to use Espresso idling resources:
		// https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		pressBack();
		
		ViewInteraction textInputEditText = onView(
				allOf(withId(R.id.input_name),
						childAtPosition(
								childAtPosition(
										withClassName(is("android.support.design.widget.TextInputLayout")),
										0),
								0)));
		textInputEditText.perform(scrollTo(), replaceText("Yin"), closeSoftKeyboard());
		
		ViewInteraction textInputEditText2 = onView(
				allOf(withId(R.id.input_email),
						childAtPosition(
								childAtPosition(
										withClassName(is("android.support.design.widget.TextInputLayout")),
										0),
								0)));
		textInputEditText2.perform(scrollTo(), replaceText("943605447@qq.com"), closeSoftKeyboard());
		
		ViewInteraction textInputEditText3 = onView(
				allOf(withId(R.id.input_password),
						childAtPosition(
								childAtPosition(
										withClassName(is("android.support.design.widget.TextInputLayout")),
										0),
								0)));
		textInputEditText3.perform(scrollTo(), replaceText("83032502"), closeSoftKeyboard());
		
		ViewInteraction textInputEditText4 = onView(
				allOf(withId(R.id.input_reenterPassword),
						childAtPosition(
								childAtPosition(
										withClassName(is("android.support.design.widget.TextInputLayout")),
										0),
								0)));
		textInputEditText4.perform(scrollTo(), replaceText("83032502"), closeSoftKeyboard());
		
		ViewInteraction appCompatRadioButton = onView(
				allOf(withId(R.id.radio_student), withText("Student"),
						childAtPosition(
								allOf(withId(R.id.rgroup_signup),
										childAtPosition(
												withClassName(is("android.widget.LinearLayout")),
												5)),
								0)));
		appCompatRadioButton.perform(scrollTo(), click());
		
		ViewInteraction appCompatButton = onView(
				allOf(withId(R.id.btn_signup), withText("Sign Up"),
						childAtPosition(
								childAtPosition(
										withClassName(is("android.widget.ScrollView")),
										0),
								6)));
		appCompatButton.perform(scrollTo(), click());
		
	}
	
	private static Matcher<View> childAtPosition(
			final Matcher<View> parentMatcher, final int position) {
		
		return new TypeSafeMatcher<View>() {
			@Override
			public void describeTo(Description description) {
				description.appendText("Child at position " + position + " in parent ");
				parentMatcher.describeTo(description);
			}
			
			@Override
			public boolean matchesSafely(View view) {
				ViewParent parent = view.getParent();
				return parent instanceof ViewGroup && parentMatcher.matches(parent)
						&& view.equals(((ViewGroup) parent).getChildAt(position));
			}
		};
	}
}
