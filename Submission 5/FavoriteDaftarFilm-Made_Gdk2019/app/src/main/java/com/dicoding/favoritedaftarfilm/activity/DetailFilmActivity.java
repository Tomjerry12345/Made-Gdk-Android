package com.dicoding.favoritedaftarfilm.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.dicoding.favoritedaftarfilm.R;
import com.dicoding.favoritedaftarfilm.model.ModelMovie;

import java.util.ArrayList;
import android.view.Window;
import android.support.v7.widget.AppCompatRatingBar;

public class DetailFilmActivity extends AppCompatActivity {

    private TextView tvTitle , tvRelease , tvVoteAverage , tvVoteCount , tvPopularity, tvOverview;
    private AppCompatRatingBar ratingBar;
    private ImageView image1 , image2;

    public static final String EXTRA_TITLE = "extra_title";
    public static final String EXTRA_DATE = "extra_date";
    public static final String EXTRA_POPULARITY = "extra_popularity";
    public static final String EXTRA_RATING = "extra_rating";
    public static final String EXTRA_DESC = "extra_desc";
    public static final String EXTRA_BACKGROUND = "extra_background";
    public static final String EXTRA_PHOTO = "extra_photo";
    public static final String EXTRA_ID = "extra_id";
    public static final String EXTRA_VOTE_COUNT = "extra_vote_count";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_detail_film);

        initView();

        final String title = getIntent().getStringExtra(EXTRA_TITLE);
        final Float popularity = getIntent().getFloatExtra(EXTRA_POPULARITY , 0);
        final float rating = getIntent().getFloatExtra(EXTRA_RATING, 0);
        final String desc = getIntent().getStringExtra(EXTRA_DESC);
        final String background = getIntent().getStringExtra(EXTRA_BACKGROUND);
        final String photo = getIntent().getStringExtra(EXTRA_PHOTO);
        final String releaseDate = getIntent().getStringExtra(EXTRA_DATE);
        final float voteCount = getIntent().getFloatExtra(EXTRA_VOTE_COUNT, 0);

        final float ratingValue = rating / 10 * 5;


        Glide.with(this)
                .load(background)

                .into(image1);

        Glide.with(this)
                .load(photo)

                .into(image2);

        tvTitle.setText(title);
        tvRelease.setText(releaseDate);
        ratingBar.setRating(ratingValue);
        tvVoteCount.setText(Float.toString(voteCount));
        tvPopularity.setText(Float.toString(popularity));
        tvOverview.setText(desc);


        tvVoteAverage.setText(String.format("%.3f",ratingValue));

    }

    private void initView() {
        image1 = findViewById(R.id.image);
        image2 = findViewById(R.id.image1);
        ratingBar = findViewById(R.id.rating_bar);
        tvTitle = findViewById(R.id.title);
        tvRelease = findViewById(R.id.release);
        tvVoteAverage = findViewById(R.id.vote_average1);
        tvVoteCount = findViewById(R.id.vote_count1);
        tvPopularity = findViewById(R.id.popularity);
        tvOverview = findViewById(R.id.overview1);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();

    }

}

