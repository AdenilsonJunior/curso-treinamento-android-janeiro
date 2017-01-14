package com.treinamento.adenilson.myretrofitapplication.infraestructure.storage.manager;

import com.treinamento.adenilson.myretrofitapplication.domain.entity.Status;
import com.treinamento.adenilson.myretrofitapplication.domain.repository.GitHubStatusRepository;
import com.treinamento.adenilson.myretrofitapplication.infraestructure.storage.service
        .GitHubStatusService;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by adenilson on 13/01/17.
 */

public class GitHubStatusManager implements GitHubStatusRepository {

    private final GitHubStatusService mGitHubService;

    public GitHubStatusManager(GitHubStatusService gitHubService) {
        mGitHubService = gitHubService;
    }

    @Override
    public Observable<Status> lastMessage() {
        return mGitHubService.lastMessage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
