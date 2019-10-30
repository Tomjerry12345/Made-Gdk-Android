package com.dicoding.daftarfilm.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.dicoding.daftarfilm.fragment.FragmentFavoriteFilm;
import com.dicoding.daftarfilm.fragment.FragmentFavoriteTv;

public class FragmentFavoriteAdapter extends FragmentPagerAdapter {

    public FragmentFavoriteAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        if (i == 0) {
            return new FragmentFavoriteFilm();
        }
        return new FragmentFavoriteTv();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        if (position == 0) {
            return "Favorite Film";
        }
        return "Favorite Tv";
    }

    @Override
    public int getCount() {
        return 2;
    }
}


