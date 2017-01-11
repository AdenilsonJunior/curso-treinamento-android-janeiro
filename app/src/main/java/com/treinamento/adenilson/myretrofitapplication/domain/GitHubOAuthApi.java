package com.treinamento.adenilson.myretrofitapplication.domain;

import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by adenilson on 11/01/17.
 */

public interface GitHubOAuthApi {

    String BASE_URL = "https://github.com/login/oauth/";

    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST("access_token")
    Call<AccessToken> accessToken(
            @Field("client_id") String clientId,
            @Field("client_secret") String clientSecret,
            @Field("code") String code);

    Retrofit RETROFIT = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                    .create()))
            .baseUrl(BASE_URL)
            .build();


}
