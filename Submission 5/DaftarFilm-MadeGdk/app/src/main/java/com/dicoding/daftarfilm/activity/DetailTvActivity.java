package com.dicoding.daftarfilm.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

import com.dicoding.daftarfilm.R;
import com.dicoding.daftarfilm.db.FavoriteHelper;
import com.dicoding.daftarfilm.model.tv.Result;
import android.view.Window;
import android.support.v7.widget.AppCompatRatingBar;
import android.widget.Toast;

import static android.support.constraint.Constraints.TAG;

public class DetailTvActivity extends AppCompatActivity {

    private TextView tvTitle , tvRelease , tvVoteAverage , tvVoteCount , tvPopularity, tvOverview;
	private AppCompatRatingBar ratingBar;
    private ImageView image1 , image2;
    private FavoriteHelper favoriteHelper;
    private CheckBox checkBox;

	ArrayList<Result> list;

	private Result modelTv;

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
        setContentView(R.layout.activity_detail_tv);
        initView();

		favoriteHelper = FavoriteHelper.getInstance(this.getApplicationContext());

		final String title = getIntent().getStringExtra(EXTRA_TITLE);
		final float popularity = getIntent().getFloatExtra(EXTRA_POPULARITY , 0);
		final float rating = getIntent().getFloatExtra(EXTRA_RATING, 0);
		final String desc = getIntent().getStringExtra(EXTRA_DESC);
		final String background = getIntent().getStringExtra(EXTRA_BACKGROUND);
		final String photo = getIntent().getStringExtra(EXTRA_PHOTO);
		final String releaseDate = getIntent().getStringExtra(EXTRA_DATE);
		final int voteCount = getIntent().getIntExtra(EXTRA_VOTE_COUNT, 0);

		final float ratingValue = (float) rating / 10 * 5;

		list = favoriteHelper.getAllTv();
		if (list.size() > 0){
			for (int i=0; i<list.size(); i ++) {
				Log.i(TAG, "onCreateView: "+ i);
				if (list.get(i).getName().equalsIgnoreCase(title) && list.get(i).getOverview().equalsIgnoreCase(desc)) {
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
					list = favoriteHelper.getAllTv();
					boolean noSimilarItem = true;
					for (int i = 0; i < list.size(); i++) {
						if (list.get(i).getName().equalsIgnoreCase(title) && list.get(i).getOverview().equalsIgnoreCase(desc)) {
							noSimilarItem = false;
							break;
						}
					}
					if (noSimilarItem) {
						modelTv = new Result();
						modelTv.setName(title);
						modelTv.setPopularity(popularity);
						modelTv.setVoteAverage(rating);
						modelTv.setOverview(desc);
						modelTv.setBackdropPath(background);
						modelTv.setPosterPath(photo);
						modelTv.setFirstAirDate(releaseDate);
						modelTv.setVoteCount(voteCount);
						modelTv.setFavorite(1);
						long result = favoriteHelper.insertFavoriteTv(modelTv);
						checkBox.setChecked(true);
						if (result > 0) {
							modelTv.setId((int) result);
							Toast.makeText(DetailTvActivity.this, "succes", Toast.LENGTH_SHORT).show();
						} else {
							Toast.makeText(DetailTvActivity.this, "Failed", Toast.LENGTH_SHORT).show();
						}
					}


				} else {
					checkBox.setChecked(false);
					FavoriteHelper favoriteHelper = FavoriteHelper.getInstance(DetailTvActivity.this);
					list = favoriteHelper.getAllTv();
					int id = 0;
					for (int i = 0; i < list.size(); i++) {
						if (list.get(i).getName().equalsIgnoreCase(title) && list.get(i).getOverview().equalsIgnoreCase(desc)) {
							id = list.get(i).getId();
						}
					}
					favoriteHelper.deleteFavoriteTv(id);

				}
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
}
