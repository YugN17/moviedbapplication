package com.soulkitchen.serifenuruysal.movieapp.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.soulkitchen.serifenuruysal.movieapp.R;
import com.soulkitchen.serifenuruysal.movieapp.fragment.MovieFragment;

/**
 * Created by S.Nur Uysal on 21.11.2018.
 */
public  class TabsPagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 3;
    private Context mContext;

    public TabsPagerAdapter(FragmentManager fragmentManager, Context context) {
        super(fragmentManager);
        this.mContext=context;
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return MovieFragment.newInstance(MovieFragment.UPCOMING);
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return MovieFragment.newInstance(MovieFragment.POPULAR);
            case 2: // Fragment # 1 - This will show SecondFragment
                return MovieFragment.newInstance(MovieFragment.NOWPLAYING);
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return  mContext.getResources().getString(R.string.title_upcoming_name);
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return  mContext.getResources().getString(R.string.title_popular_name);
            case 2: // Fragment # 1 - This will show SecondFragment
                return  mContext.getResources().getString(R.string.title_nowplaying_name);
            default:
                return null;
        }
    }

}
