package com.treinamento.adenilson.myretrofitapplication.util;

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

    public static String getToken(Activity activity){
        SharedPreferences sharedPreferences = activity.getSharedPreferences(AUTH, 0);
        return sharedPreferences.getString(TOKEN, "null");
    }
}
