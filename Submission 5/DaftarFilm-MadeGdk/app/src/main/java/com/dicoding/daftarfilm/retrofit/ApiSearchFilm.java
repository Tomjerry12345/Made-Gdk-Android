package com.dicoding.daftarfilm.retrofit;

import com.dicoding.daftarfilm.model.film.ListFilm;
import com.dicoding.daftarfilm.model.film.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by IrfanRZ on 12/12/2018.
 */

public interface ApiSearchFilm {
    String BASE_URL = "https://api.themoviedb.org/3/search/";

    @GET
    Call<ListFilm> searchMovie(@Url String url);
}

