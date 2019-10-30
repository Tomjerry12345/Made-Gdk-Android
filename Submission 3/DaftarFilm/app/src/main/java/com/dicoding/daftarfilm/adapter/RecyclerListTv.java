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

import android.widget.Toast;
import android.content.Intent;
import com.dicoding.daftarfilm.activity.DetailTv;


public class RecyclerListTv extends RecyclerView.Adapter<RecyclerListTv.bidangViewHolder> {
    private ArrayList<Result> dataList;
    private Context context;

    public RecyclerListTv(ArrayList<Result> dataList) {
        this.dataList = dataList;
    }

    @Override
    public bidangViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_film, parent, false);
        this.context = parent.getContext();
        return new bidangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final bidangViewHolder holder, final int position) {
        Uri uri = Uri.parse("https://image.tmdb.org/t/p/w500" + dataList.get(position).getPosterPath());
        Picasso.with(context).load(uri).into(holder.imageView);

        holder.txtNama.setText(dataList.get(position).getName());
        holder.txtStatus.setText(dataList.get(position).getFirstAirDate());
		holder.ratingBar.setRating(dataList.get(position).getPopularity());
		holder.itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(context, DetailTv.class);
                    intent.putParcelableArrayListExtra("list",dataList);
                    intent.putExtra("position",holder.getAdapterPosition());
                    context.startActivity(intent);
				}
			});
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class bidangViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNama, txtStatus;
        private ImageView imageView;
		private AppCompatRatingBar ratingBar;


        public bidangViewHolder(View itemView) {
            super(itemView);
            txtNama = (TextView) itemView.findViewById(R.id.title);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            txtStatus = (TextView) itemView.findViewById(R.id.release);
//            txtAlamat = (TextView) itemView.findViewById(R.id.text_alamat);
			ratingBar = (AppCompatRatingBar) itemView.findViewById(R.id.rating_bar);
        }
    }
}
