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
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
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
public class SystemTest {
	
	@Rule
	public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);
	
	@Test
	public void systemTest() {
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
		textInputEditText.perform(scrollTo(), replaceText("hih"), closeSoftKeyboard());
		
		ViewInteraction textInputEditText2 = onView(
				allOf(withId(R.id.input_email), withText("hih"),
						childAtPosition(
								childAtPosition(
										withClassName(is("android.support.design.widget.TextInputLayout")),
										0),
								0)));
		textInputEditText2.perform(scrollTo(), click());
		
		// Added a sleep statement to match the app's execution delay.
		// The recommended way to handle such scenarios is to use Espresso idling resources:
		// https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		ViewInteraction textInputEditText3 = onView(
				allOf(withId(R.id.input_email), withText("hih"),
						childAtPosition(
								childAtPosition(
										withClassName(is("android.support.design.widget.TextInputLayout")),
										0),
								0)));
		textInputEditText3.perform(scrollTo(), replaceText("hitomi9y@gmail.com"));
		
		ViewInteraction textInputEditText4 = onView(
				allOf(withId(R.id.input_email), withText("hitomi9y@gmail.com"),
						childAtPosition(
								childAtPosition(
										withClassName(is("android.support.design.widget.TextInputLayout")),
										0),
								0),
						isDisplayed()));
		textInputEditText4.perform(closeSoftKeyboard());
		
		ViewInteraction textInputEditText5 = onView(
				allOf(withId(R.id.input_password),
						childAtPosition(
								childAtPosition(
										withClassName(is("android.support.design.widget.TextInputLayout")),
										0),
								0)));
		textInputEditText5.perform(scrollTo(), replaceText("83032502"), closeSoftKeyboard());
		
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
				allOf(withId(R.id.btn_question), withText("Questions"),
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
		
		ViewInteraction appCompatEditText = onView(
				allOf(withId(R.id.edit_feedback),
						childAtPosition(
								childAtPosition(
										withId(android.R.id.content),
										0),
								1),
						isDisplayed()));
		appCompatEditText.perform(replaceText("Hello world"), closeSoftKeyboard());
		
		ViewInteraction appCompatButton3 = onView(
				allOf(withId(R.id.btn_add), withText("Add"),
						childAtPosition(
								childAtPosition(
										withId(android.R.id.content),
										0),
								0),
						isDisplayed()));
		appCompatButton3.perform(click());
		
		pressBack();
		
		pressBack();
		
		// Added a sleep statement to match the app's execution delay.
		// The recommended way to handle such scenarios is to use Espresso idling resources:
		// https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		ViewInteraction appCompatButton4 = onView(
				allOf(withId(R.id.btn_feedback), withText("Feedbacks"),
						childAtPosition(
								childAtPosition(
										withId(android.R.id.content),
										0),
								2),
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
		
		ViewInteraction appCompatEditText2 = onView(
				allOf(withId(R.id.edit_feedback),
						childAtPosition(
								childAtPosition(
										withId(android.R.id.content),
										0),
								1),
						isDisplayed()));
		appCompatEditText2.perform(replaceText("Hi"), closeSoftKeyboard());
		
		ViewInteraction appCompatButton5 = onView(
				allOf(withId(R.id.btn_add), withText("Add"),
						childAtPosition(
								childAtPosition(
										withId(android.R.id.content),
										0),
								0),
						isDisplayed()));
		appCompatButton5.perform(click());
		
		pressBack();
		
		pressBack();
		
		// Added a sleep statement to match the app's execution delay.
		// The recommended way to handle such scenarios is to use Espresso idling resources:
		// https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		pressBack();
		
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
		
		ViewInteraction appCompatButton6 = onView(
				allOf(withId(R.id.btn_submit), withText("2.0/2.0"),
						childAtPosition(
								childAtPosition(
										withId(android.R.id.content),
										0),
								1),
						isDisplayed()));
		appCompatButton6.perform(click());
		
		// Added a sleep statement to match the app's execution delay.
		// The recommended way to handle such scenarios is to use Espresso idling resources:
		// https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		ViewInteraction tabView2 = onView(
				allOf(childAtPosition(
						childAtPosition(
								withId(R.id.tab_layout),
								0),
						1),
						isDisplayed()));
		tabView2.perform(click());
		
		// Added a sleep statement to match the app's execution delay.
		// The recommended way to handle such scenarios is to use Espresso idling resources:
		// https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		ViewInteraction appCompatEditText3 = onView(
				allOf(withId(R.id.edit_answer),
						childAtPosition(
								childAtPosition(
										withId(R.id.recyclerViewQuestion),
										0),
								1),
						isDisplayed()));
		appCompatEditText3.perform(click());
		
		ViewInteraction appCompatEditText4 = onView(
				allOf(withId(R.id.edit_answer),
						childAtPosition(
								childAtPosition(
										withId(R.id.recyclerViewQuestion),
										0),
								1),
						isDisplayed()));
		appCompatEditText4.perform(replaceText("a"), closeSoftKeyboard());
		
		ViewInteraction appCompatEditText5 = onView(
				allOf(withId(R.id.edit_answer),
						childAtPosition(
								childAtPosition(
										withId(R.id.recyclerViewQuestion),
										1),
								1),
						isDisplayed()));
		appCompatEditText5.perform(replaceText("b"), closeSoftKeyboard());
		
		ViewInteraction appCompatButton7 = onView(
				allOf(withId(R.id.btn_submit), withText("Submit"),
						childAtPosition(
								childAtPosition(
										withId(android.R.id.content),
										0),
								1),
						isDisplayed()));
		appCompatButton7.perform(click());
		
		// Added a sleep statement to match the app's execution delay.
		// The recommended way to handle such scenarios is to use Espresso idling resources:
		// https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		ViewInteraction tabView3 = onView(
				allOf(childAtPosition(
						childAtPosition(
								withId(R.id.tab_layout),
								0),
						1),
						isDisplayed()));
		tabView3.perform(click());
		
		// Added a sleep statement to match the app's execution delay.
		// The recommended way to handle such scenarios is to use Espresso idling resources:
		// https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		ViewInteraction appCompatButton8 = onView(
				allOf(withId(R.id.btn_submit), withText("3.5/3.5"),
						childAtPosition(
								childAtPosition(
										withId(android.R.id.content),
										0),
								1),
						isDisplayed()));
		appCompatButton8.perform(click());
		
		// Added a sleep statement to match the app's execution delay.
		// The recommended way to handle such scenarios is to use Espresso idling resources:
		// https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
		try {
			Thread.sleep(3587189);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
		
		ViewInteraction appCompatTextView = onView(
				allOf(withId(R.id.title), withText("Logout"),
						childAtPosition(
								childAtPosition(
										withClassName(is("android.support.v7.view.menu.ListMenuItemView")),
										0),
								0),
						isDisplayed()));
		appCompatTextView.perform(click());
		
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
