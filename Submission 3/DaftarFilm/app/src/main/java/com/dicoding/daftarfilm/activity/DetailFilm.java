package com.dicoding.daftarfilm.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


import java.util.ArrayList;

import com.dicoding.daftarfilm.R;
import com.dicoding.daftarfilm.model.film.Result;
import android.view.Window;
import android.support.v7.widget.AppCompatRatingBar;


public class DetailFilm extends AppCompatActivity {

    private TextView tvTitle , tvRelease , tvVoteAverage , tvVoteCount , tvPopularity, tvOverview;
	private AppCompatRatingBar ratingBar;
    private ImageView image1 , image2;

    ArrayList<Result> list;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
		getSupportActionBar().hide();
        setContentView(R.layout.activity_detail_film);
        initView();
		
        list = getIntent().getParcelableArrayListExtra("list");
        position = getIntent().getIntExtra("position",-1);


        Glide.with(this)
			.load("https://image.tmdb.org/t/p/w500"+list.get(position).getBackdropPath())
			
			.into(image1);
			
		Glide.with(this)
			.load("https://image.tmdb.org/t/p/w500"+list.get(position).getPosterPath())

			.into(image2);
			
			
		tvTitle.setText(list.get(position).getTitle());
		tvRelease.setText(list.get(position).getReleaseDate());
		ratingBar.setRating(list.get(position).getPopularity());
		tvVoteAverage.setText(String.valueOf(list.get(position).getVoteAverage()));
		tvVoteCount.setText(String.valueOf(list.get(position).getVoteCount()));
		tvPopularity.setText(String.valueOf(list.get(position).getPopularity()));
		tvOverview.setText(list.get(position).getOverview());
    }

    private void initView() {
        image1 = (ImageView) findViewById(R.id.image);
		image2 = (ImageView) findViewById(R.id.image1);
		ratingBar = (AppCompatRatingBar) findViewById(R.id.rating_bar);
        tvTitle = (TextView) findViewById(R.id.title);
        tvRelease = (TextView) findViewById(R.id.release);
		tvVoteAverage = (TextView)findViewById(R.id.vote_average1);
		tvVoteCount = (TextView)findViewById(R.id.vote_count1);
		tvPopularity = (TextView)findViewById(R.id.popularity);
		tvOverview = (TextView) findViewById(R.id.overview1);
    }
}
