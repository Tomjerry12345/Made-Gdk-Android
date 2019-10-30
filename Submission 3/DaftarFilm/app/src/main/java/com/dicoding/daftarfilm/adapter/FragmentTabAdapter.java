package com.dicoding.daftarfilm.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.dicoding.daftarfilm.fragment.FragmentFilm;
import com.dicoding.daftarfilm.fragment.FragmentTv;

public class FragmentTabAdapter extends FragmentPagerAdapter {

    public FragmentTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        if (i == 0) {
            return new FragmentFilm();
        }
        return new FragmentTv();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        if (position == 0) {
            return "Film";
        }
        return "Tv";
    }

    @Override
    public int getCount() {
        return 2;
    }
}

