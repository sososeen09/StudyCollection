package com.longge.studycollection.lazy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.longge.studycollection.R;

import java.util.ArrayList;
import java.util.List;

public class LazyActivity extends AppCompatActivity {

    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lazy);
        mViewPager = (ViewPager) findViewById(R.id.vp_lazy);
        List<Fragment> fragments = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            fragments.add(LazyFragment.getInstance(i));
        }
        FgPagerAdapter fgPagerAdapter = new FgPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(fgPagerAdapter);

    }

    class FgPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragments;

        public FgPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragments = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

}
