package com.dicoding.favoritedaftarfilm.adapter;

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
import com.dicoding.favoritedaftarfilm.R;
import com.dicoding.favoritedaftarfilm.activity.DetailFilmActivity;
import com.dicoding.favoritedaftarfilm.model.ModelMovie;

import java.util.ArrayList;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    ArrayList<ModelMovie> listMovies= new ArrayList<>();
    Context context;

    public MovieAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<ModelMovie> getListMovies() {
        return listMovies;
    }

    public void setListMovies(ArrayList<ModelMovie> listMovies) {
        this.listMovies.clear();
        this.listMovies.addAll(listMovies);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_items, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
        final ModelMovie modelMovie = listMovies.get(i);
        final float rating = modelMovie.getVote_average();
        final float ratingValue = rating / 10 * 5;
        movieViewHolder.txtTitle.setText(modelMovie.getTitle());
        movieViewHolder.ratingBar.setRating(ratingValue);
        movieViewHolder.txtDesc.setText(modelMovie.getDeskripsi());
        Glide.with(movieViewHolder.itemView.getContext())
                .load(modelMovie.getPhoto())
                .into(movieViewHolder.imageView);
        movieViewHolder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailFilmActivity.class);
                intent.putExtra(DetailFilmActivity.EXTRA_ID, modelMovie.getId());
                intent.putExtra(DetailFilmActivity.EXTRA_TITLE, modelMovie.getTitle());
                intent.putExtra(DetailFilmActivity.EXTRA_BACKGROUND, modelMovie.getBackground());
                intent.putExtra(DetailFilmActivity.EXTRA_DATE, modelMovie.getDate());
                intent.putExtra(DetailFilmActivity.EXTRA_DESC, modelMovie.getDeskripsi());
                intent.putExtra(DetailFilmActivity.EXTRA_PHOTO, modelMovie.getPhoto());
                intent.putExtra(DetailFilmActivity.EXTRA_POPULARITY, modelMovie.getPopularity());
                intent.putExtra(DetailFilmActivity.EXTRA_RATING, modelMovie.getVote_average());
                intent.putExtra(DetailFilmActivity.EXTRA_VOTE_COUNT, modelMovie.getVoteCount());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMovies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout constraintLayout;
        private ImageView imageView;
        private TextView txtTitle;
//        private TextView txtPopularity;
        private TextView txtDesc;
        private AppCompatRatingBar ratingBar;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.cl_items);
            imageView = itemView.findViewById(R.id.img_item);
            txtTitle = itemView.findViewById(R.id.tv_title);
            ratingBar = itemView.findViewById(R.id.rating_bar);
            txtDesc = itemView.findViewById(R.id.tv_desc);
        }
    }
}
