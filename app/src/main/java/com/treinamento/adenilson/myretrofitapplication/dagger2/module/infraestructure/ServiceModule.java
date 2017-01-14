package com.treinamento.adenilson.myretrofitapplication.dagger2.module.infraestructure;

import com.treinamento.adenilson.myretrofitapplication.domain.repository.GitHubOAuthRepository;
import com.treinamento.adenilson.myretrofitapplication.domain.repository.GitHubStatusRepository;
import com.treinamento.adenilson.myretrofitapplication.domain.repository.GitHubUserRepository;
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

import static com.treinamento.adenilson.myretrofitapplication.dagger2.module.infraestructure.NetworkModule
        .RETROFIT_GITHUB;
import static com.treinamento.adenilson.myretrofitapplication.dagger2.module.infraestructure
        .NetworkModule.RETROFIT_GITHUB_FOLLOWERS;
import static com.treinamento.adenilson.myretrofitapplication.dagger2.module.infraestructure.NetworkModule
        .RETROFIT_GITHUB_OAUTH;
import static com.treinamento.adenilson.myretrofitapplication.dagger2.module.infraestructure.NetworkModule
        .RETROFIT_GITHUB_STATUS;

/**
 * Created by adenilson on 12/01/17.
 */

@Module
public class ServiceModule {

    @Singleton
    @Provides
    GitHubUserService providesGitHub(
            @Named(RETROFIT_GITHUB) Retrofit retrofit) {
        return retrofit.create(GitHubUserService.class);
    }

    @Singleton
    @Provides
    GitHubStatusService providesGitHubStatus(
            @Named(RETROFIT_GITHUB_STATUS) Retrofit retrofit) {
        return retrofit.create(GitHubStatusService.class);
    }

    @Singleton
    @Provides
    GitHubOAuthService providesGitHubOAuth(
            @Named(RETROFIT_GITHUB_OAUTH) Retrofit retrofit) {
        return retrofit.create(GitHubOAuthService.class);
    }

    @Singleton
    @Provides
    GitHubFollowersService providesGitHubFollowers(
            @Named(RETROFIT_GITHUB_FOLLOWERS) Retrofit retrofit) {
        return retrofit.create(GitHubFollowersService.class);
    }



}
