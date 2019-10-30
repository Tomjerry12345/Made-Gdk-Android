package com.dicoding.daftarfilm.fragment;

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

import com.dicoding.daftarfilm.R;
import com.dicoding.daftarfilm.adapter.SearchFilmAdapter;
import com.dicoding.daftarfilm.model.film.ListFilm;
import com.dicoding.daftarfilm.model.film.Result;
import com.dicoding.daftarfilm.retrofit.ApiListMovie;
import com.dicoding.daftarfilm.adapter.RecyclerListFilm;
import android.support.v7.widget.GridLayoutManager;

import android.support.v7.widget.RecyclerView;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.dicoding.daftarfilm.retrofit.ApiSearchFilm;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class FragmentFilm extends Fragment implements View.OnClickListener {

	private ArrayList<Result> listFilm = new ArrayList<Result>();

	private RecyclerView rcList , rcSearch;
	private ProgressBar loadProgress;
	EditText edtSearch;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_film, container, false);
	}




	@Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcList = (RecyclerView)view.findViewById(R.id.rcListFilm);
        rcSearch = view.findViewById(R.id.rcListSearch);
		loadProgress =(ProgressBar)view.findViewById(R.id.progressBar);

		 edtSearch = view.findViewById(R.id.edt_search);

		ImageButton search = view.findViewById(R.id.btn_search);
		search.setOnClickListener(this);
		
		if (savedInstanceState != null && savedInstanceState.getSerializable("Simpan") != null){

            ArrayList<Result> yourArray = new Gson().fromJson(savedInstanceState.getSerializable("Simpan").toString()
															  , new TypeToken<ArrayList<Result>>(){}.getType());
            listFilm = yourArray;
            setListFilm();
			loadProgress.setVisibility(View.GONE);
        }
        else {
            getListFilm();
        }
		
    }

	@Override
	public void onClick(View v) {
		Toast.makeText(getActivity() , "di klik" , Toast.LENGTH_SHORT).show();
		loadProgress.setVisibility(View.VISIBLE);
		getSearchFilm(edtSearch.getText().toString());
		rcList.setVisibility(View.GONE);
		rcSearch.setVisibility(View.VISIBLE);
		edtSearch.setText("");
	}
	
	@Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("Simpan", new Gson().toJson(listFilm));
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
						listFilm.add(new Result(films.getResults().get(a).getBackdropPath(), films.getResults().get(a).getOverview(), films.getResults().get(a).getPosterPath()
												, films.getResults().get(a).getReleaseDate(), films.getResults().get(a).getTitle(), films.getResults().get(a).getVoteAverage()
												, films.getResults().get(a).getVoteCount(), films.getResults().get(a).getPopularity()
												));
												
							loadProgress.setVisibility(View.GONE);
						
					}
					setListFilm();
				}

				@Override
				public void onFailure(Call<ListFilm> call, Throwable t) {
					Toast.makeText(getActivity(),  t.getMessage().toString(), Toast.LENGTH_SHORT).show();
					loadProgress.setVisibility(View.GONE);
				}
			});
	}

	private void getSearchFilm(String query){
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(ApiSearchFilm.BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		ApiSearchFilm apiSearchMovie = retrofit.create(ApiSearchFilm.class);

		Call<ListFilm> call = apiSearchMovie.searchMovie("movie?api_key=407db308a634f3f54cc6c27b7051147d&query=" + query);

		call.enqueue(new Callback<ListFilm>() {
			@Override
			public void onResponse(Call<ListFilm> call, Response<ListFilm> response) {
				ListFilm films = response.body();
				listFilm = new ArrayList<Result>();

				for (int a = 0; a < films.getResults().size(); a++){
					listFilm.add(new Result(films.getResults().get(a).getBackdropPath(), films.getResults().get(a).getOverview(), films.getResults().get(a).getPosterPath()
							, films.getResults().get(a).getReleaseDate(), films.getResults().get(a).getTitle(), films.getResults().get(a).getVoteAverage()
							, films.getResults().get(a).getVoteCount(), films.getResults().get(a).getPopularity()
					));

					loadProgress.setVisibility(View.GONE);

				}
				setSearchFilm();
			}

			@Override
			public void onFailure(Call<ListFilm> call, Throwable t) {
				Toast.makeText(getActivity(), t.getMessage().toString(), Toast.LENGTH_SHORT).show();
				loadProgress.setVisibility(View.GONE);
			}
		});
	}
	
	private void setListFilm(){
		rcList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
		RecyclerListFilm gridHeroAdapter = new RecyclerListFilm(listFilm);
		rcList.setAdapter(gridHeroAdapter);
		rcList.setNestedScrollingEnabled(false);

	}

	private void setSearchFilm(){
		rcSearch.setLayoutManager(new GridLayoutManager(getActivity(), 2));
		SearchFilmAdapter gridHeroAdapter = new SearchFilmAdapter(listFilm);
		rcSearch.setAdapter(gridHeroAdapter);
		rcSearch.setNestedScrollingEnabled(false);

	}
	
}

