package com.example.escproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by 94360 on 2018/4/3.
 */

public class QuizPagerAdapter extends FragmentStatePagerAdapter {
	int mNumOfTabs;
	
	public QuizPagerAdapter(FragmentManager fm, int mNumOfTabs){
		super(fm);
		this.mNumOfTabs = mNumOfTabs;
	}
	
	@Override
	public Fragment getItem(int position) {
		switch (position) {
			case 0:
				return new InstrModifyQuizFragment();
			case 1:
				return new InstrCheckQuizFragment();
			default:
				return null;
		}
	}
	
	@Override
	public int getCount() {
		return mNumOfTabs;
	}
}
