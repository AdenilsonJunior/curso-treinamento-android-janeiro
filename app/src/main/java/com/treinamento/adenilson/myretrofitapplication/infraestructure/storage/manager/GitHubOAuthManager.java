package com.treinamento.adenilson.myretrofitapplication.infraestructure.storage.manager;

import com.treinamento.adenilson.myretrofitapplication.domain.entity.AccessToken;
import com.treinamento.adenilson.myretrofitapplication.domain.repository.GitHubOAuthRepository;
import com.treinamento.adenilson.myretrofitapplication.infraestructure.storage.service
        .GitHubOAuthService;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by adenilson on 13/01/17.
 */

public class GitHubOAuthManager implements GitHubOAuthRepository {

    private final GitHubOAuthService mGitHubService;

    public GitHubOAuthManager(GitHubOAuthService gitHubService) {
        mGitHubService = gitHubService;
    }

    @Override
    public Observable<AccessToken> accessToken(String clientId, String clientSecret, String code) {
        return mGitHubService.accessToken(clientId, clientSecret, code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
