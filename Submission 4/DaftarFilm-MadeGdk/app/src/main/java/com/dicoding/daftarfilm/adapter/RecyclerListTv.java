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
import com.squareup.picasso.Picasso;
import android.support.v7.widget.AppCompatRatingBar;


import com.dicoding.daftarfilm.model.tv.Result;

import android.content.Intent;
import com.dicoding.daftarfilm.activity.DetailTv;


public class RecyclerListTv extends RecyclerView.Adapter<RecyclerListTv.TvViewHolder> {
    private ArrayList<Result> dataList;
    private Context context;

    public RecyclerListTv(ArrayList<Result> dataList) {
        this.dataList = dataList;
    }

    @Override
    public TvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.grid_item, parent, false);
        this.context = parent.getContext();
        return new TvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TvViewHolder holder, final int position) {
        final Float ratng = dataList.get(position).getVoteAverage();
        final float ratingValue = ratng / 10 * 5;

        Uri uri = Uri.parse("https://image.tmdb.org/t/p/w500" + dataList.get(position).getPosterPath());
        Picasso.with(context).load(uri).into(holder.imageView);

        holder.txtNama.setText(dataList.get(position).getName());
        holder.txtStatus.setText(dataList.get(position).getFirstAirDate());
//        holder.txtAlamat.setText("Release Date : " + dataList.get(position).getReleaseDate());
		holder.ratingBar.setRating(ratingValue);
		holder.itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
                    Intent moveDataFilm = new Intent(context, DetailTv.class);
                    moveDataFilm.putExtra(DetailTv.EXTRA_BACKGROUND,dataList.get(position).getBackdropPath());
                    moveDataFilm.putExtra(DetailTv.EXTRA_TITLE, dataList.get(position).getName());
                    moveDataFilm.putExtra(DetailTv.EXTRA_DATE, dataList.get(position).getFirstAirDate());
                    moveDataFilm.putExtra(DetailTv.EXTRA_POPULARITY, dataList.get(position).getPopularity());
                    moveDataFilm.putExtra(DetailTv.EXTRA_RATING, dataList.get(position).getVoteAverage());
                    moveDataFilm.putExtra(DetailTv.EXTRA_DESC, dataList.get(position).getOverview());
                    moveDataFilm.putExtra(DetailTv.EXTRA_PHOTO, dataList.get(position).getPosterPath());
                    moveDataFilm.putExtra(DetailTv.EXTRA_VOTE_COUNT, dataList.get(position).getVoteCount());

                    context.startActivity(moveDataFilm);
				}
			});
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class TvViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNama, txtStatus;
        private ImageView imageView;
		private AppCompatRatingBar ratingBar;


        public TvViewHolder(View itemView) {
            super(itemView);
            txtNama = (TextView) itemView.findViewById(R.id.title);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            txtStatus = (TextView) itemView.findViewById(R.id.release);
//            txtAlamat = (TextView) itemView.findViewById(R.id.text_alamat);
			ratingBar = (AppCompatRatingBar) itemView.findViewById(R.id.rating_bar);
        }
    }
}
