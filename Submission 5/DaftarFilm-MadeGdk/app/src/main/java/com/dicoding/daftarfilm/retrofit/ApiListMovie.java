package com.dicoding.daftarfilm.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import com.dicoding.daftarfilm.model.film.ListFilm;


public interface ApiListMovie {
    String BASE_URL = "https://api.themoviedb.org/3/movie/";

    @GET("top_rated?api_key=407db308a634f3f54cc6c27b7051147d")
    Call<ListFilm> getMovies();
}
