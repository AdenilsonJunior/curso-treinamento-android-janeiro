package com.treinamento.adenilson.myretrofitapplication.dagger2.module.infraestructure;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.treinamento.adenilson.myretrofitapplication.infraestructure.storage.service
        .GitHubFollowersService;
import com.treinamento.adenilson.myretrofitapplication.infraestructure.storage.service
        .GitHubOAuthService;
import com.treinamento.adenilson.myretrofitapplication.infraestructure.storage.service
        .GitHubStatusService;
import com.treinamento.adenilson.myretrofitapplication.infraestructure.storage.service
        .GitHubUserService;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by adenilson on 12/01/17.
 */

@Module
public class NetworkModule {


    static final String RETROFIT_GITHUB = "GitHub";
    static final String RETROFIT_GITHUB_STATUS = "GitHubStatus";
    static final String RETROFIT_GITHUB_OAUTH = "GitHubOAuth";
    static final String RETROFIT_GITHUB_FOLLOWERS = "GitHubOFollowers";

    @Provides
    @Singleton
    Gson providesGson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                .create();
    }

    @Provides
    @Singleton
    GsonConverterFactory providesGsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @Singleton
    RxJavaCallAdapterFactory providesRxJavaCallAdapterFactory() {
        return RxJavaCallAdapterFactory.create();
    }

    @Provides
    @Singleton
    @Named(RETROFIT_GITHUB)
    Retrofit providesRetrofitGitHub(GsonConverterFactory gsonFactory,
                                    RxJavaCallAdapterFactory rxFactory) {
        return buildRetrofit(gsonFactory, rxFactory,
                GitHubUserService.BASE_URL);
    }

    @Provides
    @Singleton
    @Named(RETROFIT_GITHUB_STATUS)
    Retrofit providesRetrofitGitHubStatus(GsonConverterFactory gsonFactory,
                                          RxJavaCallAdapterFactory rxFactory) {
        return buildRetrofit(gsonFactory, rxFactory,
                GitHubStatusService.BASE_URL);
    }

    @Provides
    @Singleton
    @Named(RETROFIT_GITHUB_OAUTH)
    Retrofit providesRetrofitGitHubOAuth(GsonConverterFactory gsonFactory,
                                         RxJavaCallAdapterFactory rxFactory) {
        return buildRetrofit(gsonFactory, rxFactory,
                GitHubOAuthService.BASE_URL);
    }

    @Provides
    @Singleton
    @Named(RETROFIT_GITHUB_FOLLOWERS)
    Retrofit providesRetrofitGitHubFollowers(GsonConverterFactory gsonConverterFactory,
                                             RxJavaCallAdapterFactory rxFactory){
        return buildRetrofit(gsonConverterFactory, rxFactory, GitHubFollowersService.BASE_URL);
    }

    private Retrofit buildRetrofit(GsonConverterFactory converterFactory,
                                   RxJavaCallAdapterFactory callAdapterFactory,
                                   String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .build();
    }

}
