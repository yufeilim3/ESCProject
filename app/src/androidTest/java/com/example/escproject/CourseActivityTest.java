package com.example.escproject;

import android.app.Instrumentation;
import android.content.Intent;
import android.os.SystemClock;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by 94360 on 2018/4/3.
 */

@MediumTest
@RunWith(AndroidJUnit4.class)
public class CourseActivityTest {
	@Rule
	public ActivityTestRule<CourseActivity> rule  = new  ActivityTestRule<CourseActivity>(CourseActivity.class) {
		@Override
		protected Intent getActivityIntent() {
			InstrumentationRegistry.getTargetContext();
			Intent intent = new Intent(Intent.ACTION_MAIN);
			return intent;
		}
	};
	
	@Test
	public void ensureButtonPresent() throws Exception {
		Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(AddCourseActivity.class.getName(), null, false);
		CourseActivity activity = rule.getActivity();
		Button btn = activity.findViewById(R.id.add);
		assertThat(btn,notNullValue());
		assertThat(btn, instanceOf(Button.class));
		assertEquals(btn.callOnClick(), true);
		AddCourseActivity addCourseActivity = (AddCourseActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
		assertThat(addCourseActivity, notNullValue());
	}
	
	@Test
	public void ensureListViewIsPresent() throws Exception {
		CourseActivity activity = rule.getActivity();
		Thread.sleep(3000);
		View viewById = activity.findViewById(R.id.recyclerViewAnime);
		assertThat(viewById,notNullValue());
		assertThat(viewById, instanceOf(RecyclerView.class));
		RecyclerView list = (RecyclerView) viewById;
		assertThat(list.getAdapter().getItemCount(), greaterThan(0));
		long downTime = SystemClock.uptimeMillis();
		long eventTime = SystemClock.uptimeMillis() + 1000;
		float x = list.getChildAt(0).getX()+list.getChildAt(0).getWidth()/2;
		float y = list.getChildAt(0).getY()+list.getChildAt(0).getHeight()/2;
		int metaState = 0;
		MotionEvent motionEvent = MotionEvent.obtain(
				downTime,
				eventTime,
				MotionEvent.ACTION_UP,
				x,
				y,
				metaState
		);
		assertTrue(list.getChildAt(0).dispatchTouchEvent(motionEvent));
		Thread.sleep(100);
		assertThat(MyCourseActivity.state, notNullValue());
	}
}
