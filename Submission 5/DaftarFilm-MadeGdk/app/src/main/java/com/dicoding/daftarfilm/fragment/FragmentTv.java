package com.dicoding.daftarfilm.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import android.support.v7.widget.GridLayoutManager;

import android.support.v7.widget.RecyclerView;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dicoding.daftarfilm.R;

import com.dicoding.daftarfilm.adapter.SearchTvAdapter;
import com.dicoding.daftarfilm.model.tv.Result;
import com.dicoding.daftarfilm.model.tv.ListTv;
import com.dicoding.daftarfilm.retrofit.ApiListTv;
import com.dicoding.daftarfilm.adapter.RecyclerListTv;
import com.dicoding.daftarfilm.retrofit.ApiSearchTv;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class FragmentTv extends Fragment implements View.OnClickListener {

	private ArrayList<Result> listTv = new ArrayList<Result>();

	private ProgressBar loadProgress;

	private RecyclerView rcList , rcSearch;

	EditText edtSearch;

	ImageButton search;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_tv, container, false);
	}


	@Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcList = (RecyclerView)view.findViewById(R.id.rcListTv);
        rcSearch = view.findViewById(R.id.rcSearchtTv);
		loadProgress =(ProgressBar)view.findViewById(R.id.progressBar);

		edtSearch = view.findViewById(R.id.edt_search);

		ImageButton search = view.findViewById(R.id.btn_search);
		search.setOnClickListener(this);


		if (savedInstanceState != null && savedInstanceState.getSerializable("Simpan") != null){

			ArrayList<com.dicoding.daftarfilm.model.tv.Result> yourArray = new Gson().fromJson(savedInstanceState.getSerializable("Simpan").toString()
					, new TypeToken<ArrayList<com.dicoding.daftarfilm.model.tv.Result>>(){}.getType());
			listTv = yourArray;
			setListTv();
			loadProgress.setVisibility(View.GONE);
		}
		else {
			getListTv();
		}

	}

	@Override
	public void onClick(View v) {
		Toast.makeText(getActivity() , "di klik" , Toast.LENGTH_SHORT).show();
		loadProgress.setVisibility(View.VISIBLE);
		getSearchTv(edtSearch.getText().toString());
		rcList.setVisibility(View.GONE);
		rcSearch.setVisibility(View.VISIBLE);
		edtSearch.setText("");
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		outState.putSerializable("Simpan", new Gson().toJson(listTv));
	}


	private void getListTv(){
        Retrofit retrofit = new Retrofit.Builder()
			.baseUrl(ApiListTv.BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.build();
        ApiListTv apiListTv = retrofit.create(ApiListTv.class);

        Call<ListTv> call = apiListTv.getTv();

        call.enqueue(new Callback<ListTv>() {
				@Override
				public void onResponse(Call<ListTv> call, Response<ListTv> response) {
					ListTv tv = response.body();
					listTv = new ArrayList<Result>();

					for (int a = 0; a < tv.getResults().size(); a++){
						listTv.add(new Result(tv.getResults().get(a).getBackdropPath(), tv.getResults().get(a).getId(), tv.getResults().get(a).getName()
												, tv.getResults().get(a).getOverview(), tv.getResults().get(a).getPosterPath()
												, tv.getResults().get(a).getFirstAirDate(), tv.getResults().get(a).getVoteAverage(), tv.getResults().get(a).getVoteCount()
												, tv.getResults().get(a).getPopularity()
												));
						loadProgress.setVisibility(View.GONE);

					}
					setListTv();
				}

				@Override
				public void onFailure(Call<ListTv> call, Throwable t) {
					Toast.makeText(getActivity(),  t.getMessage().toString(), Toast.LENGTH_SHORT).show();
					loadProgress.setVisibility(View.GONE);
				}
			});
	}

	private void getSearchTv(String query){
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(ApiSearchTv.BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		ApiSearchTv apiSearchTv = retrofit.create(ApiSearchTv.class);

		Call<ListTv> call = apiSearchTv.searchTv("tv?api_key=407db308a634f3f54cc6c27b7051147d&query=" + query);

		call.enqueue(new Callback<ListTv>() {
			@Override
			public void onResponse(Call<ListTv> call, Response<ListTv> response) {
				ListTv tv = response.body();
				listTv = new ArrayList<Result>();

				for (int a = 0; a < tv.getResults().size(); a++){
					listTv.add(new Result(tv.getResults().get(a).getBackdropPath(), tv.getResults().get(a).getId(), tv.getResults().get(a).getName()
							, tv.getResults().get(a).getOverview(), tv.getResults().get(a).getPosterPath()
							, tv.getResults().get(a).getFirstAirDate(), tv.getResults().get(a).getVoteAverage(), tv.getResults().get(a).getVoteCount()
							, tv.getResults().get(a).getPopularity()
					));
					loadProgress.setVisibility(View.GONE);

				}
				setSearchTv();
			}

			@Override
			public void onFailure(Call<ListTv> call, Throwable t) {
				Toast.makeText(getActivity(), t.getMessage().toString(), Toast.LENGTH_SHORT).show();
			}
		});
	}

	private void setListTv(){
        rcList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
		RecyclerListTv gridHeroAdapter = new RecyclerListTv(listTv);
		rcList.setAdapter(gridHeroAdapter);
        rcList.setNestedScrollingEnabled(false);

    }

	private void setSearchTv(){
		rcSearch.setLayoutManager(new GridLayoutManager(getActivity(), 2));
		SearchTvAdapter gridHeroAdapter = new SearchTvAdapter(listTv);
		rcSearch.setAdapter(gridHeroAdapter);
		rcSearch.setNestedScrollingEnabled(false);

	}
}

