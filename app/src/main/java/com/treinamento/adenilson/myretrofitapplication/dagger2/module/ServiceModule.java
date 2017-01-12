package com.treinamento.adenilson.myretrofitapplication.dagger2.module;

import com.treinamento.adenilson.myretrofitapplication.domain.GitHubOAuthApi;
import com.treinamento.adenilson.myretrofitapplication.domain.GitHubStatusApi;
import com.treinamento.adenilson.myretrofitapplication.domain.GitHubUserApi;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

import static com.treinamento.adenilson.myretrofitapplication.dagger2.module.NetworkModule
        .RETROFIT_GITHUB;
import static com.treinamento.adenilson.myretrofitapplication.dagger2.module.NetworkModule
        .RETROFIT_GITHUB_OAUTH;
import static com.treinamento.adenilson.myretrofitapplication.dagger2.module.NetworkModule
        .RETROFIT_GITHUB_STATUS;

/**
 * Created by adenilson on 12/01/17.
 */

@Module
public class ServiceModule {

    @Singleton
    @Provides
    GitHubUserApi providesGitHub(
            @Named(RETROFIT_GITHUB) Retrofit retrofit) {
        return retrofit.create(GitHubUserApi.class);
    }

    @Singleton
    @Provides
    GitHubStatusApi providesGitHubStatus(
            @Named(RETROFIT_GITHUB_STATUS) Retrofit retrofit) {
        return retrofit.create(GitHubStatusApi.class);
    }

    @Singleton
    @Provides
    GitHubOAuthApi providesGitHubOAuth(
            @Named(RETROFIT_GITHUB_OAUTH) Retrofit retrofit) {
        return retrofit.create(GitHubOAuthApi.class);
    }
}
