package com.treinamento.adenilson.myretrofitapplication.presentation.ui.auth;

import com.treinamento.adenilson.myretrofitapplication.domain.entity.Status;
import com.treinamento.adenilson.myretrofitapplication.domain.repository.GitHubFollowersRepository;
import com.treinamento.adenilson.myretrofitapplication.domain.repository.GitHubOAuthRepository;
import com.treinamento.adenilson.myretrofitapplication.domain.repository.GitHubStatusRepository;
import com.treinamento.adenilson.myretrofitapplication.domain.repository.GitHubUserRepository;

/**
 * Created by adenilson on 13/01/17.
 */

public class AuthPresenter implements AuthContract.Presenter {

    private AuthContract.View mView;
    private GitHubUserRepository mGitHubRepository;
    private GitHubStatusRepository mGitHubStatusRepository;
    private GitHubOAuthRepository mGitHubOAuthRepository;
    private GitHubFollowersRepository mGitHubFollowersRepository;

    public AuthPresenter(GitHubUserRepository gitHubRepository,
                         GitHubStatusRepository gitHubStatusRepository,
                         GitHubOAuthRepository gitHubOAuthRepository) {
        this.mGitHubRepository = gitHubRepository;
        this.mGitHubStatusRepository = gitHubStatusRepository;
        this.mGitHubOAuthRepository = gitHubOAuthRepository;
    }

    @Override
    public void setView(AuthContract.View view) {
        mView = view;
    }

    @Override
    public void loadStatus() {
        mGitHubStatusRepository.lastMessage()
                .subscribe(status -> {
                    mView.onLoadStatusType(status.type);
                }, error -> {
                    mView.onLoadStatusType(Status.Type.MAJOR);
                });
    }

    @Override
    public void getUser(String authorization) {
        mGitHubRepository.basicAuth(authorization)
                .subscribe(entity -> {
                    mView.onAuthSuccess(authorization, entity);
                }, error -> {
                    mView.showError(error.getMessage());
                });
    }

    @Override
    public void callAccessToken(String clientId,
                                String clientSecret,
                                String code) {
        mGitHubOAuthRepository.accessToken(clientId, clientSecret, code)
                .subscribe(entity -> {
                    getUser(entity.getAuthCredential());
                }, error -> {
                    mView.showError(error.getMessage());
                });
    }
}
