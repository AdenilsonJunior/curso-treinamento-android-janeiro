package com.treinamento.adenilson.myretrofitapplication.domain.repository;

import com.treinamento.adenilson.myretrofitapplication.domain.entity.User;

import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by adenilson on 11/01/2017.
 */

public interface GitHubUserRepository {

    Observable<User> basicAuth(String authorization);
}
