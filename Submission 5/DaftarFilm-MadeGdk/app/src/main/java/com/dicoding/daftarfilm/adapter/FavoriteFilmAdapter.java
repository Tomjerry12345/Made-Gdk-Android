package com.dicoding.daftarfilm.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;

import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.dicoding.daftarfilm.R;
import com.dicoding.daftarfilm.activity.DetailFilmActivity;
import com.dicoding.daftarfilm.model.film.Result;

import java.util.ArrayList;

public class FavoriteFilmAdapter extends RecyclerView.Adapter<FavoriteFilmAdapter.FavoriteViewHolder> {

    private ArrayList<Result> listFavorite = new ArrayList<>();
    private Context context;

    public FavoriteFilmAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<Result> getListFavorite() {
        return listFavorite;
    }

    public void setListFavorite(ArrayList<Result> listFavorite) {
        if (listFavorite.size() > 0) {
            this.listFavorite.clear();
        }
        this.listFavorite = listFavorite;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder favoriteViewHolder, int i) {
        final Result modelMovie = listFavorite.get(i);
        final Float rating = modelMovie.getVoteAverage();
        final float ratingValue = rating / 10 * 5;
        favoriteViewHolder.txtTitle.setText(modelMovie.getTitle());
        favoriteViewHolder.ratingBar.setRating(ratingValue);
        favoriteViewHolder.txtDesc.setText(modelMovie.getOverview());

        Glide.with(favoriteViewHolder.itemView.getContext())
                .load(modelMovie.getPosterPath())
                .into(favoriteViewHolder.imageView);

        favoriteViewHolder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveDataFilm = new Intent(context, DetailFilmActivity.class);
                moveDataFilm.putExtra(DetailFilmActivity.EXTRA_BACKGROUND, modelMovie.getBackdropPath());
                moveDataFilm.putExtra(DetailFilmActivity.EXTRA_TITLE, modelMovie.getTitle());
                moveDataFilm.putExtra(DetailFilmActivity.EXTRA_DATE, modelMovie.getReleaseDate());
                moveDataFilm.putExtra(DetailFilmActivity.EXTRA_POPULARITY, modelMovie.getPopularity());
                moveDataFilm.putExtra(DetailFilmActivity.EXTRA_RATING, modelMovie.getVoteAverage());
                moveDataFilm.putExtra(DetailFilmActivity.EXTRA_DESC, modelMovie.getOverview());
                moveDataFilm.putExtra(DetailFilmActivity.EXTRA_PHOTO, modelMovie.getPosterPath());
                moveDataFilm.putExtra(DetailFilmActivity.EXTRA_ID, modelMovie.getId());
                moveDataFilm.putExtra(DetailFilmActivity.EXTRA_VOTE_COUNT, modelMovie.getVoteCount());

                context.startActivity(moveDataFilm);


            }
        });
    }

    @Override
    public int getItemCount() {
        return listFavorite.size();
    }

    public class FavoriteViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout constraintLayout;
        private ImageView imageView;
        private TextView txtTitle;
        private TextView txtDesc;
        private AppCompatRatingBar ratingBar;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.cl_items);
            imageView = itemView.findViewById(R.id.img_item);
            txtTitle = itemView.findViewById(R.id.tv_title);
            txtDesc = itemView.findViewById(R.id.tv_desc);
            ratingBar = itemView.findViewById(R.id.rating_bar);
        }
    }
}

