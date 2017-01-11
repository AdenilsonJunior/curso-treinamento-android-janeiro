package com.treinamento.adenilson.myretrofitapplication.domain;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created by adenilson on 11/01/17.
 */

public class SharedPreferencesUtil {

    public static final String AUTH = "auth";
    public static final String TOKEN = "token";

    public static void saveToken(Activity activity, String token){
        SharedPreferences sharedPreferences = activity.getSharedPreferences(AUTH, 0);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(TOKEN, token).apply();

    }
}
