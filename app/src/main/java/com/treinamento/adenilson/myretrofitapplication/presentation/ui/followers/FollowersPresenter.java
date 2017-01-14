package com.treinamento.adenilson.myretrofitapplication.presentation.ui.followers;

import com.treinamento.adenilson.myretrofitapplication.domain.repository.GitHubFollowersRepository;

/**
 * Created by adenilson on 14/01/17.
 */

public class FollowersPresenter implements FollowersContract.Presenter {


    private GitHubFollowersRepository mGitHubFollowersRepository;
    private FollowersContract.View mView;

    public FollowersPresenter(GitHubFollowersRepository gitHubFollowersRepository) {
        this.mGitHubFollowersRepository = gitHubFollowersRepository;
    }

    public void setView(FollowersContract.View view) {
        this.mView = view;
    }

    @Override
    public void getFollowers(String authorization) {
        mGitHubFollowersRepository.getFollowers(authorization)
                .subscribe(followers -> {
                            mView.onLoadFollowers(followers);
                        },
                        throwable -> {
                            mView.showError(throwable.getMessage());
                        });
    }
}
