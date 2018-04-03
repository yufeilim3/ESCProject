package com.example.escproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
	
	int mNumOfTabs;
	
	public PagerAdapter(FragmentManager fm, int mNumOfTabs){
		super(fm);
		this.mNumOfTabs = mNumOfTabs;
	}
	
	@Override
	public Fragment getItem(int position) {
		switch (position) {
			case 0:
				return new SlideFragment();
			case 1:
				return new QuizFragment();
			case 2:
				return new OtherFragment();
			default:
				return null;
		}
	}
	
	@Override
	public int getCount() {
		return mNumOfTabs;
	}
}
