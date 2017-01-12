package com.treinamento.adenilson.myretrofitapplication.domain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.treinamento.adenilson.myretrofitapplication.domain.entity.Status;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by adenilson on 09/01/17.
 */

public interface GitHubStatusApi {

    String BASE_URL = "https://status.github.com/api/";


    Retrofit RETROFIT = new Retrofit.Builder()
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                    .create()))
            .baseUrl(BASE_URL)
            .build();

    @GET("last-message.json")
    Observable<Status> lastMessage();

}
