package com.uncorkedstudios.designlibtest;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;


/**
 * Created by Kat on 7/21/15.
 */
public class SecondActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    protected NavigationView mNavigationView;

    private ImageView mToolbarImageView;

    private ViewPager mViewPager;

    private TabLayout mTabLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);

        mNavigationView = (NavigationView) findViewById(R.id.navigationview);

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(new WallpaperFragmentAdapter(getSupportFragmentManager()));

        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        if (ViewCompat.isLaidOut(mTabLayout)) {
            mTabLayout.setupWithViewPager(mViewPager);
        } else {
            mTabLayout.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom,
                        int oldLeft, int oldTop, int oldRight, int oldBottom) {

                    mTabLayout.setupWithViewPager(mViewPager);

                    mTabLayout.removeOnLayoutChangeListener(this);
                }
            });
        }

        mToolbarImageView = (ImageView) findViewById(R.id.backdrop);

    }

    class WallpaperFragmentAdapter extends FragmentStatePagerAdapter {

        public WallpaperFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return RecyclerViewFragment.newInstance();
        }

        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return "Tab " + (position + 1);

        }
    }
}
