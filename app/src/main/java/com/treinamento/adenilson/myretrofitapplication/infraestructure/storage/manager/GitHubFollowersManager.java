package com.treinamento.adenilson.myretrofitapplication.infraestructure.storage.manager;

import com.treinamento.adenilson.myretrofitapplication.domain.entity.Follower;
import com.treinamento.adenilson.myretrofitapplication.domain.repository.GitHubFollowersRepository;
import com.treinamento.adenilson.myretrofitapplication.infraestructure.storage.service
        .GitHubFollowersService;
import com.treinamento.adenilson.myretrofitapplication.infraestructure.storage.service
        .GitHubUserService;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by adenilson on 14/01/17.
 */

public class GitHubFollowersManager implements GitHubFollowersRepository {

    public GitHubFollowersService mGitHubFollowersService;


    public GitHubFollowersManager(GitHubFollowersService gitHubFollowersService){
        this.mGitHubFollowersService = gitHubFollowersService;
    }

    @Override
    public Observable<List<Follower>> getFollowers(String authorization) {
        return mGitHubFollowersService.getFollowers(authorization)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
