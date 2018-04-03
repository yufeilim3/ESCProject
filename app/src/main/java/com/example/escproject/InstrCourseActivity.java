package com.example.escproject;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class InstrCourseActivity extends AppCompatActivity {
	
	private FirebaseAuth auth;
	static Course state;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_instr_course);
		
		auth = FirebaseAuth.getInstance();
		if (auth.getCurrentUser()==null){
			Intent intent = new Intent(this, LoginActivity.class);
			startActivity(intent);
			finish();
		}
		
		TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
		tabLayout.addTab(tabLayout.newTab().setText("Slide"));
		tabLayout.addTab(tabLayout.newTab().setText("Quiz"));
		tabLayout.addTab(tabLayout.newTab().setText("Other"));
		tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
		final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
		final InstrPagerAdapter adapter = new InstrPagerAdapter
				(getSupportFragmentManager(), tabLayout.getTabCount());
		viewPager.setAdapter(adapter);
		viewPager.addOnPageChangeListener(new
				TabLayout.TabLayoutOnPageChangeListener(tabLayout));
		tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				viewPager.setCurrentItem(tab.getPosition());
			}
			
			@Override
			public void onTabUnselected(TabLayout.Tab tab) {
			}
			
			@Override
			public void onTabReselected(TabLayout.Tab tab) {
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_course, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch(id){
			case R.id.logout:
				auth.signOut();
				Toast.makeText(getApplicationContext(),"Logout Successful", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(InstrCourseActivity.this,LoginActivity.class);
				startActivity(intent);
				finish();
				break;
		}
		return true;
	}
}
