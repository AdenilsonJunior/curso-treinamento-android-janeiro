package com.treinamento.adenilson.myretrofitapplication.dagger2.module.presentation;

import android.content.Context;

import com.treinamento.adenilson.myretrofitapplication.presentation.helper.AppHelper;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;

/**
 * Created by adenilson on 13/01/17.
 */

@Module
public class HelperModule {
    @Provides
    @Reusable
    AppHelper provideTextHelper(Context context) {
        return new AppHelper(context);
    }
}