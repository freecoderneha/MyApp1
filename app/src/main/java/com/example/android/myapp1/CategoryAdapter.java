package com.example.android.myapp1;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class CategoryAdapter extends FragmentPagerAdapter {
    /** Context of the app */
    private Context mContext;
    public CategoryAdapter(Context context,FragmentManager fm) {
        super(fm);
        mContext=context;

    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new FeedFragment();
        } else if (position == 1){
            return new ComplainFragment();
        } else if(position==2)
            return new BookingFragment();
        else {
            return new ServicesFragment();
        }
    }


    @Override
    public int getCount() {
        return 4;
    }

}