package com.dicoding.daftarfilm.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.dicoding.daftarfilm.R;
import com.dicoding.daftarfilm.model.film.Result;
import com.squareup.picasso.Picasso;
import android.support.v7.widget.AppCompatRatingBar;


import com.dicoding.daftarfilm.activity.DetailFilm;
import android.widget.Toast;
import android.content.Intent;

public class RecyclerListFilm extends RecyclerView.Adapter<RecyclerListFilm.FilmViewHolder> {
    private ArrayList<Result> dataList;
    private Context context;

    public RecyclerListFilm(ArrayList<Result> dataList) {
        this.dataList = dataList;
    }


    @Override
    public FilmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.grid_item, parent, false);
        this.context = parent.getContext();
        return new FilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FilmViewHolder holder, final int position) {
        final Float ratng = dataList.get(position).getVoteAverage();
        final float ratingValue = ratng / 10 * 5;
        Uri uri = Uri.parse("https://image.tmdb.org/t/p/w500" + dataList.get(position).getPosterPath());
        Picasso.with(context).load(uri).into(holder.imageView);

        holder.txtNama.setText(dataList.get(position).getTitle());
        holder.txtStatus.setText(dataList.get(position).getReleaseDate());
//        holder.txtAlamat.setText("Release Date : " + dataList.get(position).getReleaseDate());
		holder.ratingBar.setRating(ratingValue);
		holder.itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
                    Intent moveDataFilm = new Intent(context, DetailFilm.class);
                    moveDataFilm.putExtra(DetailFilm.EXTRA_BACKGROUND,dataList.get(position).getBackdropPath());
                    moveDataFilm.putExtra(DetailFilm.EXTRA_TITLE, dataList.get(position).getTitle());
                    moveDataFilm.putExtra(DetailFilm.EXTRA_DATE, dataList.get(position).getReleaseDate());
                    moveDataFilm.putExtra(DetailFilm.EXTRA_POPULARITY, dataList.get(position).getPopularity());
                    moveDataFilm.putExtra(DetailFilm.EXTRA_RATING, dataList.get(position).getVoteAverage());
                    moveDataFilm.putExtra(DetailFilm.EXTRA_DESC, dataList.get(position).getOverview());
                    moveDataFilm.putExtra(DetailFilm.EXTRA_PHOTO, dataList.get(position).getPosterPath());
                    moveDataFilm.putExtra(DetailFilm.EXTRA_VOTE_COUNT, dataList.get(position).getVoteCount());

                    context.startActivity(moveDataFilm);
				}
			});
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class FilmViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNama, txtStatus;
        private ImageView imageView;
		private AppCompatRatingBar ratingBar;


        public FilmViewHolder(View itemView) {
            super(itemView);
            txtNama = (TextView) itemView.findViewById(R.id.title);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            txtStatus = (TextView) itemView.findViewById(R.id.release);
//            txtAlamat = (TextView) itemView.findViewById(R.id.text_alamat);
			ratingBar = (AppCompatRatingBar) itemView.findViewById(R.id.rating_bar);
        }
    }
}
