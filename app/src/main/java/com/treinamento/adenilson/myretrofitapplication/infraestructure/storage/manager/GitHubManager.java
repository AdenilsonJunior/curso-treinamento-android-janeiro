package com.treinamento.adenilson.myretrofitapplication.infraestructure.storage.manager;

import com.treinamento.adenilson.myretrofitapplication.domain.entity.User;
import com.treinamento.adenilson.myretrofitapplication.domain.repository.GitHubUserRepository;
import com.treinamento.adenilson.myretrofitapplication.infraestructure.storage.service
        .GitHubUserService;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by adenilson on 13/01/17.
 */

public class GitHubManager implements GitHubUserRepository {

    private final GitHubUserService mGitHubService;

    public GitHubManager(GitHubUserService gitHubService) {
        mGitHubService = gitHubService;
    }

     @Override
    public Observable<User> basicAuth(String authorization) {
        return mGitHubService.basicAuth(authorization)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
