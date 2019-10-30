package com.dicoding.daftarfilm.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;

import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.dicoding.daftarfilm.R;
import com.dicoding.daftarfilm.activity.DetailFilm;
import com.dicoding.daftarfilm.model.film.Result;
import com.squareup.picasso.Picasso;

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
        final Float ratng = modelMovie.getVoteAverage();
        final float ratingValue = ratng / 10 * 5;
        favoriteViewHolder.txtTitle.setText(modelMovie.getTitle());
        String popular = context.getString(R.string.title) + modelMovie.getPopularity();
        favoriteViewHolder.ratingBar.setRating(ratingValue);
        favoriteViewHolder.txtDesc.setText(modelMovie.getOverview());
        Uri uri = Uri.parse("https://image.tmdb.org/t/p/w500" + modelMovie.getPosterPath());
        Picasso.with(context).load(uri).into(favoriteViewHolder.imageView);

        favoriteViewHolder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveDataFilm = new Intent(context, DetailFilm.class);
                moveDataFilm.putExtra(DetailFilm.EXTRA_BACKGROUND, modelMovie.getBackdropPath());
                moveDataFilm.putExtra(DetailFilm.EXTRA_TITLE, modelMovie.getTitle());
                moveDataFilm.putExtra(DetailFilm.EXTRA_DATE, modelMovie.getReleaseDate());
                moveDataFilm.putExtra(DetailFilm.EXTRA_POPULARITY, modelMovie.getPopularity());
                moveDataFilm.putExtra(DetailFilm.EXTRA_RATING, modelMovie.getVoteAverage());
                moveDataFilm.putExtra(DetailFilm.EXTRA_DESC, modelMovie.getOverview());
                moveDataFilm.putExtra(DetailFilm.EXTRA_PHOTO, modelMovie.getPosterPath());
                moveDataFilm.putExtra(DetailFilm.EXTRA_ID, modelMovie.getId());
                moveDataFilm.putExtra(DetailFilm.EXTRA_VOTE_COUNT, modelMovie.getVoteCount());

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
        private TextView txtPopularity;
        private TextView txtDesc;
        private AppCompatRatingBar ratingBar;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.cl_items);
            imageView = itemView.findViewById(R.id.img_item);
            txtTitle = itemView.findViewById(R.id.tv_title);
            txtDesc = itemView.findViewById(R.id.tv_desc);
            ratingBar = (AppCompatRatingBar) itemView.findViewById(R.id.rating_bar);
        }
    }
}

