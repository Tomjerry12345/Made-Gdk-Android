package com.dicoding.submission2.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.dicoding.submission2.R;
import com.dicoding.submission2.retrofit.ApiListMovie;
import com.dicoding.submission2.adapter.RecyclerListFilm;
import android.support.v7.widget.GridLayoutManager;

import android.support.v7.widget.RecyclerView;

import android.widget.Toast;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.dicoding.submission2.model.film.ListFilm;
import com.dicoding.submission2.model.film.Result;
import com.dicoding.submission2.adapter.ItemClickSupport;
import android.content.Intent;


public class FragmentFilm extends Fragment {
	
	private ArrayList<Result> listFilm = new ArrayList<Result>();

	private RecyclerView rcList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view_frag1 = inflater.inflate(R.layout.fragment_film, container, false);
		return view_frag1;
    }
	
	@Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcList = (RecyclerView)view.findViewById(R.id.rcListFilm);
    }
	
	@Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
		getListFilm();
    }
	
	private void getListFilm(){
        Retrofit retrofit = new Retrofit.Builder()
			.baseUrl(ApiListMovie.BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.build();
        ApiListMovie apiListMovie = retrofit.create(ApiListMovie.class);

        Call<ListFilm> call = apiListMovie.getMovies();

        call.enqueue(new Callback<ListFilm>() {
				@Override
				public void onResponse(Call<ListFilm> call, Response<ListFilm> response) {
					ListFilm films = response.body();
					listFilm = new ArrayList<Result>();

					for (int a = 0; a < films.getResults().size(); a++){
						listFilm.add(new Result(films.getResults().get(a).getAdult() , films.getResults().get(a).getBackdropPath()
												, films.getResults().get(a).getGenreIds() , films.getResults().get(a).getId()
												, films.getResults().get(a).getOriginalLanguage(), films.getResults().get(a).getOriginalTitle()
												, films.getResults().get(a).getOverview(), films.getResults().get(a).getPosterPath()
												, films.getResults().get(a).getReleaseDate(), films.getResults().get(a).getTitle()
												, films.getResults().get(a).getVideo(), films.getResults().get(a).getVoteAverage()
												, films.getResults().get(a).getVoteCount(), films.getResults().get(a).getPopularity()
												));
					}
					setListFilm();
				}

				@Override
				public void onFailure(Call<ListFilm> call, Throwable t) {
					Toast.makeText(getActivity(),  t.getMessage().toString(), Toast.LENGTH_SHORT).show();
				}
			});
	}
	
	private void setListFilm(){
        rcList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
		RecyclerListFilm gridHeroAdapter = new RecyclerListFilm(listFilm);
		rcList.setAdapter(gridHeroAdapter);
        rcList.setNestedScrollingEnabled(false);

    }
	
	
}

