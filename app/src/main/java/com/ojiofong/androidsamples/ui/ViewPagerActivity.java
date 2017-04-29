package com.ojiofong.androidsamples.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ojiofong.androidsamples.R;

public class ViewPagerActivity extends AppCompatActivity {
    private static final String TAG = ViewPagerActivity.class.getSimpleName();

    ViewPager viewPager;
    MyPagerAdaper myPagerAdaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        setTitle(TAG);
        setupViewPager();
    }

    private void setupViewPager() {
        String[] pages = {"page 1", "page 2", "page 3", "page 4", "page 5"};
        myPagerAdaper = new MyPagerAdaper(getSupportFragmentManager(), pages);
        viewPager = (ViewPager) findViewById(R.id.viewpager1);
        viewPager.setAdapter(myPagerAdaper);

        // Optionally setup with tabLayout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout1);
        tabLayout.setupWithViewPager(viewPager);
    }

    public static class MyPagerAdaper extends FragmentPagerAdapter {
        String[] pages = {};

        public MyPagerAdaper(FragmentManager fm) {
            super(fm);
        }

        public MyPagerAdaper(FragmentManager fm, String[] pages) {
            super(fm);
            this.pages = pages;
        }


        @Override
        public Fragment getItem(int position) {
            return MyFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return pages.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return pages[position];
        }
    }

    public static class MyFragment extends Fragment {
        public MyFragment() {
        }

        public static MyFragment newInstance(int position) {
            MyFragment frag = new MyFragment();
            Bundle args = new Bundle();
            args.putInt("pos", position);
            frag.setArguments(args);
            return frag;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.activity_sensor, container, false);
            int pos = getArguments().getInt("pos");
            int color = pos % 2 == 0 ? R.color.colorAccent : R.color.colorPrimary;
            view.setBackgroundColor(ContextCompat.getColor(getActivity(), color));
            return view;
        }
    }

}
