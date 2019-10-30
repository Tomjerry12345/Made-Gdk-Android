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
import android.support.v7.widget.GridLayoutManager;

import android.support.v7.widget.RecyclerView;

import android.widget.Toast;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.dicoding.submission2.R;
import com.dicoding.submission2.adapter.ItemClickSupport;
import android.content.Intent;
import com.dicoding.submission2.model.tv.Result;
import com.dicoding.submission2.model.tv.ListTv;
import com.dicoding.submission2.retrofit.ApiListTv;
import com.dicoding.submission2.adapter.RecyclerListTv;


public class FragmentTv extends Fragment {

	private ArrayList<Result> listTv = new ArrayList<Result>();

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
    }

	@Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
		getListFilm();
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
					}
					setListFilm();
				}

				@Override
				public void onFailure(Call<ListTv> call, Throwable t) {
					Toast.makeText(getActivity(),  t.getMessage().toString(), Toast.LENGTH_SHORT).show();
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

