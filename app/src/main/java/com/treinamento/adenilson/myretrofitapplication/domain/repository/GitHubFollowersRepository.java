package com.treinamento.adenilson.myretrofitapplication.domain.repository;

import com.treinamento.adenilson.myretrofitapplication.domain.entity.Follower;

import java.util.List;

import rx.Observable;

/**
 * Created by adenilson on 14/01/17.
 */

public interface GitHubFollowersRepository {

    Observable<List<Follower>> getFollowers(String authorization);

    //PUT SHARED PREFERENCE HERE.
}
