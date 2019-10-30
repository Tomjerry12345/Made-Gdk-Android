package com.dicoding.submission2.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import com.dicoding.submission2.model.tv.ListTv;

/**
 * Created by IrfanRZ on 12/12/2018.
 */

public interface ApiListTv {
    String BASE_URL = "https://api.themoviedb.org/3/tv/";

    @GET("top_rated?api_key=407db308a634f3f54cc6c27b7051147d")
    Call<ListTv> getTv();
}
