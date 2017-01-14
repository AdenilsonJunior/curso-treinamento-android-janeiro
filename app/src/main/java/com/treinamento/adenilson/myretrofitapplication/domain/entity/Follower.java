package com.treinamento.adenilson.myretrofitapplication.domain.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by adenilson on 14/01/17.
 */

public class Follower {

    public Long id;

    public String login;

    @SerializedName("avatar_url")
    public String avatarUrl;


}
