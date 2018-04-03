package com.example.escproject;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class InstrPagerAdapter extends FragmentStatePagerAdapter {
	
	int mNumOfTabs;
	
	public InstrPagerAdapter(FragmentManager fm, int mNumOfTabs){
		super(fm);
		this.mNumOfTabs = mNumOfTabs;
	}
	
	@Override
	public Fragment getItem(int position) {
		switch (position) {
			case 0:
				return new InstrSlideFragment();
			case 1:
				return new InstrQuizFragment();
			case 2:
				return new InstrOtherFragment();
			default:
				return null;
		}
	}
	
	@Override
	public int getCount() {
		return mNumOfTabs;
	}
}
