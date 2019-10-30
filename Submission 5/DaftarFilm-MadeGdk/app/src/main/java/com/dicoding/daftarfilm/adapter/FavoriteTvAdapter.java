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

import com.dicoding.daftarfilm.activity.DetailTvActivity;
import com.dicoding.daftarfilm.model.tv.Result;

import java.util.ArrayList;

public class FavoriteTvAdapter extends RecyclerView.Adapter<FavoriteTvAdapter.FavoriteViewHolder> {

    private ArrayList<Result> listFavorite = new ArrayList<>();
    private Context context;

    public FavoriteTvAdapter(Context context) {
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
        final Result modelTv = listFavorite.get(i);
        final Float rating = modelTv.getVoteAverage();
        final float ratingValue = rating / 10 * 5;
        favoriteViewHolder.txtTitle.setText(modelTv.getName());
        favoriteViewHolder.ratingBar.setRating(ratingValue);
        favoriteViewHolder.txtDesc.setText(modelTv.getOverview());
        Glide.with(favoriteViewHolder.itemView.getContext())
                .load(modelTv.getPosterPath())
                .into(favoriteViewHolder.imageView);

        favoriteViewHolder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveDataTv= new Intent(context, DetailTvActivity.class);
                moveDataTv.putExtra(DetailTvActivity.EXTRA_BACKGROUND, modelTv.getBackdropPath());
                moveDataTv.putExtra(DetailTvActivity.EXTRA_TITLE, modelTv.getName());
                moveDataTv.putExtra(DetailTvActivity.EXTRA_DATE, modelTv.getFirstAirDate());
                moveDataTv.putExtra(DetailTvActivity.EXTRA_POPULARITY, modelTv.getPopularity());
                moveDataTv.putExtra(DetailTvActivity.EXTRA_RATING, modelTv.getVoteAverage());
                moveDataTv.putExtra(DetailTvActivity.EXTRA_DESC, modelTv.getOverview());
                moveDataTv.putExtra(DetailTvActivity.EXTRA_PHOTO, modelTv.getPosterPath());
                moveDataTv.putExtra(DetailTvActivity.EXTRA_ID, modelTv.getId());
                moveDataTv.putExtra(DetailTvActivity.EXTRA_VOTE_COUNT, modelTv.getVoteCount());

                context.startActivity(moveDataTv);

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


