package com.treinamento.adenilson.myretrofitapplication.domain;

import com.treinamento.adenilson.myretrofitapplication.domain.entity.User;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by adenilson on 11/01/2017.
 */

public interface GitHubUserApi {

    String BASE_URL = "https://api.github.com/";

    @GET("user")
    Call<User> basicAuth();
}
