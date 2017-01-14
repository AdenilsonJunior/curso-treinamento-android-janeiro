package com.treinamento.adenilson.myretrofitapplication.dagger2;

import com.treinamento.adenilson.myretrofitapplication.dagger2.module.presentation.PresenterModule;
import com.treinamento.adenilson.myretrofitapplication.dagger2.scope.PerActivity;
import com.treinamento.adenilson.myretrofitapplication.presentation.ui.auth.AuthActivity;
import com.treinamento.adenilson.myretrofitapplication.presentation.ui.followers.FollowersActivity;

import dagger.Subcomponent;

/**
 * Created by adenilson on 13/01/17.
 */

@PerActivity
@Subcomponent(modules = {PresenterModule.class})
public interface UiComponent {
    void inject(AuthActivity activity);
    void inject(FollowersActivity activity);
}