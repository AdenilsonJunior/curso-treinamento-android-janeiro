package com.treinamento.adenilson.myretrofitapplication.domain.repository;

import com.treinamento.adenilson.myretrofitapplication.domain.entity.AccessToken;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by adenilson on 11/01/17.
 */

public interface GitHubOAuthRepository {

    Observable<AccessToken> accessToken(String clientId, String clientSecret, String code);

}
