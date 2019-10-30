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
import com.dicoding.favoritedaftarfilm.activity.DetailTvActivity;
import com.dicoding.favoritedaftarfilm.model.ModelTv;

import java.util.ArrayList;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.TvViewHolder> {

    private ArrayList<ModelTv> listTv = new ArrayList<>();
    private Context context;

    public TvAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<ModelTv> getListTv() {
        return listTv;
    }

    public void setListTv(ArrayList<ModelTv> listTv) {
        this.listTv = listTv;
    }

    @NonNull
    @Override
    public TvViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_items, viewGroup, false);
        return new TvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvViewHolder tvViewHolder, int i) {
        final ModelTv modelTv = listTv.get(i);
        final float rating = modelTv.getVote_average();
        final float ratingValue = rating / 10 * 5;
        tvViewHolder.txtTitle.setText(modelTv.getTitle());
        tvViewHolder.ratingBar.setRating(ratingValue);
        tvViewHolder.txtDesc.setText(modelTv.getDeskripsi());
        Glide.with(tvViewHolder.itemView.getContext())
                .load(modelTv.getPhoto())
                .into(tvViewHolder.imageView);

        tvViewHolder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailTvActivity.class);
                intent.putExtra(DetailTvActivity.EXTRA_TITLE, modelTv.getTitle());
                intent.putExtra(DetailTvActivity.EXTRA_BACKGROUND, modelTv.getBackground());
                intent.putExtra(DetailTvActivity.EXTRA_DESC, modelTv.getDeskripsi());
                intent.putExtra(DetailTvActivity.EXTRA_POPULARITY, modelTv.getPopularity());
                intent.putExtra(DetailTvActivity.EXTRA_RATING, modelTv.getVote_average());
                intent.putExtra(DetailTvActivity.EXTRA_PHOTO, modelTv.getPhoto());
                intent.putExtra(DetailTvActivity.EXTRA_VOTE_COUNT, modelTv.getVoteCount());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTv.size();
    }

    public class TvViewHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout constraintLayout;
        private ImageView imageView;
        private TextView txtTitle;
        private TextView txtPopularity;
        private TextView txtDesc;
        private AppCompatRatingBar ratingBar;
        public TvViewHolder(@NonNull View itemView) {
            super(itemView);

            constraintLayout = itemView.findViewById(R.id.cl_items);
            imageView = itemView.findViewById(R.id.img_item);
            txtTitle = itemView.findViewById(R.id.tv_title);
            ratingBar = itemView.findViewById(R.id.rating_bar);
            txtDesc = itemView.findViewById(R.id.tv_desc);
        }
    }
}

