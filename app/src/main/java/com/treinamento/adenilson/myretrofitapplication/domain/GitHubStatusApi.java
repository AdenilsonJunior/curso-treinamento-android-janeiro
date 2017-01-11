package com.treinamento.adenilson.myretrofitapplication.domain;

import com.treinamento.adenilson.myretrofitapplication.domain.entity.Status;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by adenilson on 09/01/17.
 */

public interface GitHubStatusApi {

    String BASE_URL = "https://status.github.com/api/";

    @GET("last-message.json")
    Call<Status> lastMessage();

}
