package com.treinamento.adenilson.myretrofitapplication.presentation.ui.followers;

import com.treinamento.adenilson.myretrofitapplication.domain.entity.Follower;

import java.util.List;

/**
 * Created by adenilson on 14/01/17.
 */

public class FollowersContract {

    interface View{

        void onLoadFollowers(List<Follower> followers);

        void showError(String error);

    }


    public interface Presenter{

        void getFollowers(String authorization);

        void setView(View activity);
    }
}
