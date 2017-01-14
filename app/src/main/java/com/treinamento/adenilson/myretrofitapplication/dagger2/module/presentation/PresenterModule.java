package com.treinamento.adenilson.myretrofitapplication.dagger2.module.presentation;

import com.treinamento.adenilson.myretrofitapplication.dagger2.scope.PerActivity;
import com.treinamento.adenilson.myretrofitapplication.domain.entity.Follower;
import com.treinamento.adenilson.myretrofitapplication.domain.repository.GitHubFollowersRepository;
import com.treinamento.adenilson.myretrofitapplication.domain.repository.GitHubOAuthRepository;
import com.treinamento.adenilson.myretrofitapplication.domain.repository.GitHubStatusRepository;
import com.treinamento.adenilson.myretrofitapplication.domain.repository.GitHubUserRepository;
import com.treinamento.adenilson.myretrofitapplication.presentation.ui.auth.AuthContract;
import com.treinamento.adenilson.myretrofitapplication.presentation.ui.auth.AuthPresenter;
import com.treinamento.adenilson.myretrofitapplication.presentation.ui.followers.FollowersContract;
import com.treinamento.adenilson.myretrofitapplication.presentation.ui.followers.FollowersPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by adenilson on 13/01/17.
 */

@Module
public class PresenterModule {

    @PerActivity
    @Provides
    AuthContract.Presenter provideMainPresenter(
            GitHubUserRepository gitHubRepository,
            GitHubStatusRepository gitHubStatusRepository,
            GitHubOAuthRepository gitHubOAuthRepository) {
        return new AuthPresenter(gitHubRepository,
                gitHubStatusRepository,
                gitHubOAuthRepository);
    }

    @PerActivity
    @Provides
    FollowersContract.Presenter provideFollowersPresenter(
            GitHubFollowersRepository followersRepository) {
        return new FollowersPresenter(followersRepository);
    }
}