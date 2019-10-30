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

import android.widget.ProgressBar;
import android.widget.Toast;

import com.dicoding.daftarfilm.R;

import com.dicoding.daftarfilm.model.tv.Result;
import com.dicoding.daftarfilm.model.tv.ListTv;
import com.dicoding.daftarfilm.retrofit.ApiListTv;
import com.dicoding.daftarfilm.adapter.RecyclerListTv;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class FragmentTv extends Fragment {

	private ArrayList<Result> listTv = new ArrayList<Result>();

	private ProgressBar loadProgress;

	private RecyclerView rcList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view_frag1 = inflater.inflate(R.layout.fragment_tv, container, false);
		return view_frag1;
    }

	@Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcList = (RecyclerView)view.findViewById(R.id.rcListTv);
		loadProgress =(ProgressBar)view.findViewById(R.id.progressBar);


		if (savedInstanceState != null && savedInstanceState.getSerializable("Simpan") != null){

			ArrayList<com.dicoding.daftarfilm.model.tv.Result> yourArray = new Gson().fromJson(savedInstanceState.getSerializable("Simpan").toString()
					, new TypeToken<ArrayList<com.dicoding.daftarfilm.model.tv.Result>>(){}.getType());
			listTv = yourArray;
			setListFilm();
			loadProgress.setVisibility(View.GONE);
		}
		else {
			getListFilm();
		}

	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		outState.putSerializable("Simpan", new Gson().toJson(listTv));
	}


	private void getListFilm(){
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
						listTv.add(new Result(tv.getResults().get(a).getOriginalName() , tv.getResults().get(a).getBackdropPath()
												, tv.getResults().get(a).getGenreIds() , tv.getResults().get(a).getId()
												, tv.getResults().get(a).getOriginalLanguage(), tv.getResults().get(a).getName()
												, tv.getResults().get(a).getOverview(), tv.getResults().get(a).getPosterPath()
												, tv.getResults().get(a).getFirstAirDate(), tv.getResults().get(a).getOriginCountry()
												, tv.getResults().get(a).getVoteAverage(), tv.getResults().get(a).getVoteCount()
												, tv.getResults().get(a).getPopularity()
												));
						loadProgress.setVisibility(View.GONE);

					}
					setListFilm();
				}

				@Override
				public void onFailure(Call<ListTv> call, Throwable t) {
					Toast.makeText(getActivity(),  t.getMessage().toString(), Toast.LENGTH_SHORT).show();
					loadProgress.setVisibility(View.GONE);
				}
			});
	}

	private void setListFilm(){
        rcList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
		RecyclerListTv gridHeroAdapter = new RecyclerListTv(listTv);
		rcList.setAdapter(gridHeroAdapter);
        rcList.setNestedScrollingEnabled(false);

    }
}

