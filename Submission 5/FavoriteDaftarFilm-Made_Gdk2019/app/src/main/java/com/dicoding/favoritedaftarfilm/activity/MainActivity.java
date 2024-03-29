package com.dicoding.favoritedaftarfilm.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dicoding.favoritedaftarfilm.R;
import com.dicoding.favoritedaftarfilm.adapter.ViewPageAdapter;
import com.dicoding.favoritedaftarfilm.fragment.MovieFragment;
import com.dicoding.favoritedaftarfilm.fragment.TvFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager viewPager = findViewById(R.id.viewpager);

        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager());
        viewPageAdapter.addFragment(new MovieFragment(), getString(R.string.title_movies));
        viewPageAdapter.addFragment(new TvFragment(), getString(R.string.title_tv));
        viewPager.setAdapter(viewPageAdapter);

        tabLayout.setupWithViewPager(viewPager);
    }
}

