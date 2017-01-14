package com.treinamento.adenilson.myretrofitapplication.infraestructure.storage.service;

import com.treinamento.adenilson.myretrofitapplication.domain.entity.Follower;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Header;
import rx.Observable;

/**
 * Created by adenilson on 14/01/17.
 */

public interface GitHubFollowersService {

    String BASE_URL = "https://api.github.com/";

    @GET("user/followers")
    Observable<List<Follower>> getFollowers(@Header("Authorization") String authorization);

}
