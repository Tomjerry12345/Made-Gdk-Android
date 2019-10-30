package com.dicoding.daftarfilm.retrofit;

import com.dicoding.daftarfilm.model.film.ListFilm;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiReleaseToday {
    String BASE_URL = "https://api.themoviedb.org/3/discover/";

    @GET
    Call<ListFilm> releaseToday(@Url String url);
}
