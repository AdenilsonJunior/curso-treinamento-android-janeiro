package com.treinamento.adenilson.myretrofitapplication.component;

import com.treinamento.adenilson.myretrofitapplication.BaseActivity;
import com.treinamento.adenilson.myretrofitapplication.MainActivity;
import com.treinamento.adenilson.myretrofitapplication.dagger2.module.ApplicationModule;
import com.treinamento.adenilson.myretrofitapplication.dagger2.module.NetworkModule;
import com.treinamento.adenilson.myretrofitapplication.dagger2.module.PreferencesModule;
import com.treinamento.adenilson.myretrofitapplication.dagger2.module.ServiceModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by adenilson on 12/01/17.
 */
@Singleton
@Component(modules = {
        ApplicationModule.class,
        PreferencesModule.class,
        NetworkModule.class,
        ServiceModule.class
})
public interface DiComponent {
    void inject(BaseActivity activity);
}

