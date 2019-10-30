package com.dicoding.daftarfilm.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.dicoding.daftarfilm.R;
import com.dicoding.daftarfilm.adapter.FavoriteTvAdapter;
import com.dicoding.daftarfilm.db.FavoriteHelper;

public class FragmentFavoriteTv extends Fragment {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    public FragmentFavoriteTv() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_favorite_tv, container, false);

        progressBar = view.findViewById(R.id.progressBarFavTv);
        recyclerView = view.findViewById(R.id.rv_favorite_tv_show);
        recyclerView.setHasFixedSize(true);

        showLoading(true);
        setRecyclerView();
        return view;
    }

    private void setRecyclerView(){
        FavoriteTvAdapter favoriteFilmAdapter = new FavoriteTvAdapter(getContext());
        favoriteFilmAdapter.notifyDataSetChanged();
        FavoriteHelper favoriteHelper = FavoriteHelper.getInstance(getContext());
        favoriteFilmAdapter.setListFavorite(favoriteHelper.getAllTv());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(favoriteFilmAdapter);
        showLoading(false);
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

}
