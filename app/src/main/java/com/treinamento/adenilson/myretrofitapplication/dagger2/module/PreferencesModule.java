package com.treinamento.adenilson.myretrofitapplication.dagger2.module;

import android.content.Context;
import android.content.SharedPreferences;

import com.treinamento.adenilson.myretrofitapplication.R;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by adenilson on 12/01/17.
 */

@Module
public class PreferencesModule {

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Context context) {
        final String fileName = context.getString(R.string.sp_file_key);
        return context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
    }
}