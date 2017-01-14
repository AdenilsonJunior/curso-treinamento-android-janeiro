package com.treinamento.adenilson.myretrofitapplication.infraestructure.storage.service;

import com.treinamento.adenilson.myretrofitapplication.domain.entity.Status;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by adenilson on 09/01/17.
 */

public interface GitHubStatusService {

    String BASE_URL = "https://status.github.com/api/";

    @GET("last-message.json")
    Observable<Status> lastMessage();

}
