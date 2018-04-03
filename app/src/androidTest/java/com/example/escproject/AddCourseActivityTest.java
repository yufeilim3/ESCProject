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

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddCourseActivityTest {
	
	@Rule
	public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);
	
	@Test
	public void addCourseActivityTest() {
		// Added a sleep statement to match the app's execution delay.
		// The recommended way to handle such scenarios is to use Espresso idling resources:
		// https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		ViewInteraction textInputEditText = onView(
				allOf(withId(R.id.input_email),
						childAtPosition(
								childAtPosition(
										withClassName(is("android.support.design.widget.TextInputLayout")),
										0),
								0)));
		textInputEditText.perform(scrollTo(), replaceText("9436"), closeSoftKeyboard());
		
		// Added a sleep statement to match the app's execution delay.
		// The recommended way to handle such scenarios is to use Espresso idling resources:
		// https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		ViewInteraction textInputEditText2 = onView(
				allOf(withId(R.id.input_email), withText("9436"),
						childAtPosition(
								childAtPosition(
										withClassName(is("android.support.design.widget.TextInputLayout")),
										0),
								0)));
		textInputEditText2.perform(scrollTo(), replaceText("943605447@qq.com"));
		
		ViewInteraction textInputEditText3 = onView(
				allOf(withId(R.id.input_email), withText("943605447@qq.com"),
						childAtPosition(
								childAtPosition(
										withClassName(is("android.support.design.widget.TextInputLayout")),
										0),
								0),
						isDisplayed()));
		textInputEditText3.perform(closeSoftKeyboard());
		
		ViewInteraction textInputEditText4 = onView(
				allOf(withId(R.id.input_password),
						childAtPosition(
								childAtPosition(
										withClassName(is("android.support.design.widget.TextInputLayout")),
										0),
								0)));
		textInputEditText4.perform(scrollTo(), replaceText("83032502"), closeSoftKeyboard());
		
		ViewInteraction appCompatButton = onView(
				allOf(withId(R.id.btn_login), withText("Login"),
						childAtPosition(
								childAtPosition(
										withClassName(is("android.widget.ScrollView")),
										0),
								3)));
		appCompatButton.perform(scrollTo(), click());
		
		// Added a sleep statement to match the app's execution delay.
		// The recommended way to handle such scenarios is to use Espresso idling resources:
		// https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		ViewInteraction appCompatButton2 = onView(
				allOf(withId(R.id.add), withText("+"),
						childAtPosition(
								childAtPosition(
										withId(android.R.id.content),
										0),
								1),
						isDisplayed()));
		appCompatButton2.perform(click());
		
		// Added a sleep statement to match the app's execution delay.
		// The recommended way to handle such scenarios is to use Espresso idling resources:
		// https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		ViewInteraction textInputEditText5 = onView(
				allOf(withId(R.id.input_courseName),
						childAtPosition(
								childAtPosition(
										withClassName(is("android.support.design.widget.TextInputLayout")),
										0),
								0)));
		textInputEditText5.perform(scrollTo(), replaceText("Physics"), closeSoftKeyboard());
		
		ViewInteraction textInputEditText6 = onView(
				allOf(withId(R.id.input_courseID),
						childAtPosition(
								childAtPosition(
										withClassName(is("android.support.design.widget.TextInputLayout")),
										0),
								0)));
		textInputEditText6.perform(scrollTo(), replaceText("50004"), closeSoftKeyboard());
		
		ViewInteraction textInputEditText7 = onView(
				allOf(withId(R.id.input_classID),
						childAtPosition(
								childAtPosition(
										withClassName(is("android.support.design.widget.TextInputLayout")),
										0),
								0)));
		textInputEditText7.perform(scrollTo(), replaceText("F01"), closeSoftKeyboard());
		
		ViewInteraction textInputEditText8 = onView(
				allOf(withId(R.id.input_classID), withText("F01"),
						childAtPosition(
								childAtPosition(
										withClassName(is("android.support.design.widget.TextInputLayout")),
										0),
								0)));
		textInputEditText8.perform(pressImeActionButton());
		
		ViewInteraction appCompatButton3 = onView(
				allOf(withId(R.id.btn_addCourse), withText("Add Course"),
						childAtPosition(
								childAtPosition(
										withClassName(is("android.widget.ScrollView")),
										0),
								3)));
		appCompatButton3.perform(scrollTo(), click());
		
		// Added a sleep statement to match the app's execution delay.
		// The recommended way to handle such scenarios is to use Espresso idling resources:
		// https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		ViewInteraction tabView = onView(
				allOf(childAtPosition(
						childAtPosition(
								withId(R.id.tab_layout),
								0),
						1),
						isDisplayed()));
		tabView.perform(click());
		
		// Added a sleep statement to match the app's execution delay.
		// The recommended way to handle such scenarios is to use Espresso idling resources:
		// https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		ViewInteraction appCompatEditText = onView(
				allOf(withId(R.id.edit_answer),
						childAtPosition(
								childAtPosition(
										withId(R.id.recyclerViewQuestion),
										0),
								1),
						isDisplayed()));
		appCompatEditText.perform(click());
		
		ViewInteraction appCompatEditText2 = onView(
				allOf(withId(R.id.edit_answer),
						childAtPosition(
								childAtPosition(
										withId(R.id.recyclerViewQuestion),
										0),
								1),
						isDisplayed()));
		appCompatEditText2.perform(replaceText("a"), closeSoftKeyboard());
		
		ViewInteraction appCompatEditText3 = onView(
				allOf(withId(R.id.edit_answer),
						childAtPosition(
								childAtPosition(
										withId(R.id.recyclerViewQuestion),
										1),
								1),
						isDisplayed()));
		appCompatEditText3.perform(replaceText("b"), closeSoftKeyboard());
		
		ViewInteraction appCompatEditText4 = onView(
				allOf(withId(R.id.edit_answer),
						childAtPosition(
								childAtPosition(
										withId(R.id.recyclerViewQuestion),
										2),
								1),
						isDisplayed()));
		appCompatEditText4.perform(click());
		
		ViewInteraction appCompatEditText5 = onView(
				allOf(withId(R.id.edit_answer),
						childAtPosition(
								childAtPosition(
										withId(R.id.recyclerViewQuestion),
										2),
								1),
						isDisplayed()));
		appCompatEditText5.perform(replaceText("c"), closeSoftKeyboard());
		
		ViewInteraction appCompatButton4 = onView(
				allOf(withId(R.id.btn_submit), withText("Submit"),
						childAtPosition(
								childAtPosition(
										withId(android.R.id.content),
										0),
								1),
						isDisplayed()));
		appCompatButton4.perform(click());
		
		// Added a sleep statement to match the app's execution delay.
		// The recommended way to handle such scenarios is to use Espresso idling resources:
		// https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
		
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
