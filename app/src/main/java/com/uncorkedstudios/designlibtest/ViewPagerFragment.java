package com.uncorkedstudios.designlibtest;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Kat on 7/30/15.
 */
public class ViewPagerFragment extends Fragment {


    private static final String TAG = "ViewPagerFragment";

    private ViewPager mViewPager;

    private Listener mListener;


    public static ViewPagerFragment newInstance() {
        return new ViewPagerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_viewpager, container, false);

        mViewPager = (ViewPager) root.findViewById(R.id.view_pager);
        mViewPager.setAdapter(new RecyclerViewPagerAdapter());

        return root;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mListener = (Listener) activity;
        } catch (ClassCastException e) {
            Log.e(TAG, "Activity must implement Listener", e);
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();

        mListener = null;
    }

    @Override
    public void onResume() {
        super.onResume();

        mListener.setupTabs(mViewPager);
    }

    private class RecyclerViewPagerAdapter extends PagerAdapter {

        private int numLists = 2;

        @Override
        public CharSequence getPageTitle(int position) {
            return "Tab " + position;
        }

        @Override
        public int getCount() {
            return numLists;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            RecyclerView recyclerView = new RecyclerView(ViewPagerFragment.this.getActivity());
            recyclerView.setLayoutManager(
                    new LinearLayoutManager(ViewPagerFragment.this.getActivity()));
            recyclerView.setAdapter(new CardAdapter());
            container.addView(recyclerView);
            return recyclerView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView((View) object);
        }
    }

    interface Listener {

        void setupTabs(ViewPager viewPager);
    }
}
