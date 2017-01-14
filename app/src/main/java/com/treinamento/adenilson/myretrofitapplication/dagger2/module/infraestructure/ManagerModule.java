package com.treinamento.adenilson.myretrofitapplication.dagger2.module.infraestructure;

import com.treinamento.adenilson.myretrofitapplication.domain.repository.GitHubFollowersRepository;
import com.treinamento.adenilson.myretrofitapplication.domain.repository.GitHubOAuthRepository;
import com.treinamento.adenilson.myretrofitapplication.domain.repository.GitHubStatusRepository;
import com.treinamento.adenilson.myretrofitapplication.domain.repository.GitHubUserRepository;
import com.treinamento.adenilson.myretrofitapplication.infraestructure.storage.manager
        .GitHubFollowersManager;
import com.treinamento.adenilson.myretrofitapplication.infraestructure.storage.manager
        .GitHubManager;
import com.treinamento.adenilson.myretrofitapplication.infraestructure.storage.manager
        .GitHubOAuthManager;
import com.treinamento.adenilson.myretrofitapplication.infraestructure.storage.manager
        .GitHubStatusManager;
import com.treinamento.adenilson.myretrofitapplication.infraestructure.storage.service
        .GitHubFollowersService;
import com.treinamento.adenilson.myretrofitapplication.infraestructure.storage.service
        .GitHubOAuthService;
import com.treinamento.adenilson.myretrofitapplication.infraestructure.storage.service
        .GitHubStatusService;
import com.treinamento.adenilson.myretrofitapplication.infraestructure.storage.service
        .GitHubUserService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by adenilson on 13/01/17.
 */
@Module
public class ManagerModule {

    @Singleton
    @Provides
    GitHubUserRepository providesGitHubRepository(
            GitHubUserService gitHubService) {
        return new GitHubManager(gitHubService);
    }

    @Singleton
    @Provides
    GitHubStatusRepository providesGitHubStatusRepository(
            GitHubStatusService gitHubStatusService) {
        return new GitHubStatusManager(gitHubStatusService);
    }

    @Singleton
    @Provides
    GitHubOAuthRepository providesGitHubOAuthRepository(
            GitHubOAuthService gitHubOAuthService) {
        return new GitHubOAuthManager(gitHubOAuthService);
    }

    @Singleton
    @Provides
    GitHubFollowersRepository providesGitHubFollowersRepository(
            GitHubFollowersService gitHubFollowersService) {
        return new GitHubFollowersManager(gitHubFollowersService);
    }
}
