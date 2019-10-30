package com.dicoding.daftarfilm.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

import com.dicoding.daftarfilm.R;
import com.dicoding.daftarfilm.db.FavoriteHelper;
import com.dicoding.daftarfilm.model.film.Result;
import com.dicoding.daftarfilm.widget.FavoriteMovieWidget;

import android.view.Window;
import android.support.v7.widget.AppCompatRatingBar;
import android.widget.Toast;

public class DetailFilmActivity extends AppCompatActivity {

    private TextView tvTitle , tvRelease , tvVoteAverage , tvVoteCount , tvPopularity, tvOverview;
	private AppCompatRatingBar ratingBar;
    private ImageView image1 , image2;
    private CheckBox checkBox;

	private FavoriteHelper favoriteHelper;

	private Result modelMovie;

	public static final String EXTRA_TITLE = "extra_title";
	public static final String EXTRA_DATE = "extra_date";
	public static final String EXTRA_POPULARITY = "extra_popularity";
	public static final String EXTRA_RATING = "extra_rating";
	public static final String EXTRA_DESC = "extra_desc";
	public static final String EXTRA_BACKGROUND = "extra_background";
	public static final String EXTRA_PHOTO = "extra_photo";
	public static final String EXTRA_ID = "extra_id";
	public static final String EXTRA_VOTE_COUNT = "extra_vote_count";

    ArrayList<Result> list;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		super.onCreate(savedInstanceState);
		getSupportActionBar().hide();
		setContentView(R.layout.activity_detail_film);

		initView();

		favoriteHelper = FavoriteHelper.getInstance(this.getApplicationContext());

		final String title = getIntent().getStringExtra(EXTRA_TITLE);
		final Float popularity = getIntent().getFloatExtra(EXTRA_POPULARITY , 0);
		final float rating = getIntent().getFloatExtra(EXTRA_RATING, 0);
		final String desc = getIntent().getStringExtra(EXTRA_DESC);
		final String background = getIntent().getStringExtra(EXTRA_BACKGROUND);
		final String photo = getIntent().getStringExtra(EXTRA_PHOTO);
		final String releaseDate = getIntent().getStringExtra(EXTRA_DATE);
		final int voteCount = getIntent().getIntExtra(EXTRA_VOTE_COUNT, 0);

		final float ratingValue = (float) rating / 10 * 5;


		list = favoriteHelper.getAllFilm();
		if (list.size() > 0){
			for (int i=0; i<list.size(); i ++) {
				if (list.get(i).getTitle().equalsIgnoreCase(title) && list.get(i).getOverview().equalsIgnoreCase(desc)) {
					checkBox.setChecked(true);
				}
			}
		}

		Glide.with(this)
				.load(background)

				.into(image1);

		Glide.with(this)
				.load(photo)

				.into(image2);

		tvTitle.setText(title);
		tvRelease.setText(releaseDate);
		ratingBar.setRating(ratingValue);
		tvVoteCount.setText(Integer.toString(voteCount));
		tvPopularity.setText(Float.toString(popularity));
		tvOverview.setText(desc);


		tvVoteAverage.setText(String.format("%.3f",ratingValue));

		checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					list = favoriteHelper.getAllFilm();
					boolean noSimilarItem = true;
					for (int i = 0; i < list.size(); i++) {
						if (list.get(i).getTitle().equalsIgnoreCase(title) && list.get(i).getOverview().equalsIgnoreCase(desc)) {
							noSimilarItem = false;
							break;
						}
					}
					if (noSimilarItem) {
						modelMovie = new Result();
						modelMovie.setTitle(title);
						modelMovie.setPopularity(popularity);
						modelMovie.setVoteAverage(rating);
						modelMovie.setOverview(desc);
						modelMovie.setBackdropPath(background);
						modelMovie.setPosterPath(photo);
						modelMovie.setReleaseDate(releaseDate);
						modelMovie.setVoteCount(voteCount);
						modelMovie.setFavorite(1);
						long result = favoriteHelper.insertFavorite(modelMovie);
						checkBox.setChecked(true);
						if (result > 0) {
							modelMovie.setId((int) result);
							Toast.makeText(DetailFilmActivity.this, "succes", Toast.LENGTH_SHORT).show();
						} else {
							Toast.makeText(DetailFilmActivity.this, "Failed", Toast.LENGTH_SHORT).show();
						}
					}


				} else {
					checkBox.setChecked(false);
					FavoriteHelper favoriteHelper = FavoriteHelper.getInstance(DetailFilmActivity.this);
					list = favoriteHelper.getAllFilm();
					int id = 0;
					for (int i = 0; i < list.size(); i++) {
						if (list.get(i).getTitle().equalsIgnoreCase(title) && list.get(i).getOverview().equalsIgnoreCase(desc)) {
							id = list.get(i).getId();
						}
					}
					favoriteHelper.deleteFavorite(id);

				}

				sendUpdateFavoriteList(getApplicationContext());

			}

		});
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
		checkBox = findViewById(R.id.cb_fav);
    }

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();

	}

	public static void sendUpdateFavoriteList(Context context)
	{
		Intent i = new Intent(context, FavoriteMovieWidget.class);
		i.setAction(FavoriteMovieWidget.UPDATE_WIDGET);
		context.sendBroadcast(i);
	}

}

