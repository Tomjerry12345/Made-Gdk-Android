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

import com.dicoding.daftarfilm.activity.DetailTv;
import com.dicoding.daftarfilm.model.tv.Result;
import com.squareup.picasso.Picasso;

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
        Uri uri = Uri.parse("https://image.tmdb.org/t/p/w500" + modelTv.getPosterPath());
        Picasso.with(context).load(uri).into(favoriteViewHolder.imageView);

        favoriteViewHolder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveDataTv= new Intent(context, DetailTv.class);
                moveDataTv.putExtra(DetailTv.EXTRA_BACKGROUND, modelTv.getBackdropPath());
                moveDataTv.putExtra(DetailTv.EXTRA_TITLE, modelTv.getName());
                moveDataTv.putExtra(DetailTv.EXTRA_DATE, modelTv.getFirstAirDate());
                moveDataTv.putExtra(DetailTv.EXTRA_POPULARITY, modelTv.getPopularity());
                moveDataTv.putExtra(DetailTv.EXTRA_RATING, modelTv.getVoteAverage());
                moveDataTv.putExtra(DetailTv.EXTRA_DESC, modelTv.getOverview());
                moveDataTv.putExtra(DetailTv.EXTRA_PHOTO, modelTv.getPosterPath());
                moveDataTv.putExtra(DetailTv.EXTRA_ID, modelTv.getId());
                moveDataTv.putExtra(DetailTv.EXTRA_VOTE_COUNT, modelTv.getVoteCount());

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
            ratingBar = (AppCompatRatingBar) itemView.findViewById(R.id.rating_bar);
        }
    }
}


