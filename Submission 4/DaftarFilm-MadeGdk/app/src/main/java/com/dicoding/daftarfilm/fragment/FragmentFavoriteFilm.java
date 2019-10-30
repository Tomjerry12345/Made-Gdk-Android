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
import com.dicoding.daftarfilm.adapter.FavoriteFilmAdapter;
import com.dicoding.daftarfilm.db.FavoriteHelper;

public class FragmentFavoriteFilm extends Fragment {


    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    public FragmentFavoriteFilm() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_favorite_film, container, false);

        progressBar = view.findViewById(R.id.progressBarFavMov);
        recyclerView = view.findViewById(R.id.rv_favorite_movie);


        showLoading(true);
        setRecyclerView();

        return view;
    }

    private void setRecyclerView(){
        FavoriteFilmAdapter favoriteAdapter = new FavoriteFilmAdapter(getContext());
        favoriteAdapter.notifyDataSetChanged();
        FavoriteHelper favoriteHelper = FavoriteHelper.getInstance(getContext());
        favoriteAdapter.setListFavorite(favoriteHelper.getAllFilm());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(favoriteAdapter);
        recyclerView.setNestedScrollingEnabled(false);
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
