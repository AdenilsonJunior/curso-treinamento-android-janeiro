package com.treinamento.adenilson.myretrofitapplication.infraestructure.storage.service;

import com.treinamento.adenilson.myretrofitapplication.domain.entity.User;

import retrofit2.http.GET;
import retrofit2.http.Header;
import rx.Observable;

/**
 * Created by adenilson on 11/01/2017.
 */

public interface GitHubUserService {

    String BASE_URL = "https://api.github.com/";

    @GET("user")
    Observable<User> basicAuth(@Header("Authorization")String authorization);
}
