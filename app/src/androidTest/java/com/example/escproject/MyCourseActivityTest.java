package com.example.escproject;

import android.app.Instrumentation;
import android.content.Intent;
import android.os.SystemClock;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;
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

public class MyCourseActivityTest {
	@Rule
	public ActivityTestRule<MyCourseActivity> rule  = new  ActivityTestRule<>(MyCourseActivity.class);
	
	public void abTest() {
		CourseActivity.userType = "Student";
		MyCourseActivity activity = rule.getActivity();
		MyCourseActivity.state = new Course("50003","Math","F01");
		//TabLayout tabLayout = (TabLayout) activity.findViewById(R.id.tab_layout);
		//assertThat(tabLayout.getTabCount(),greaterThan(2));
	}
}
