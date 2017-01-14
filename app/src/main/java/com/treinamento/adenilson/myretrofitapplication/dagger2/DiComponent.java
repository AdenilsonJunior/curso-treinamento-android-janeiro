package com.treinamento.adenilson.myretrofitapplication.dagger2;

import com.treinamento.adenilson.myretrofitapplication.dagger2.module.ApplicationModule;
import com.treinamento.adenilson.myretrofitapplication.dagger2.module.PreferencesModule;
import com.treinamento.adenilson.myretrofitapplication.dagger2.module.infraestructure.ManagerModule;
import com.treinamento.adenilson.myretrofitapplication.dagger2.module.infraestructure.NetworkModule;
import com.treinamento.adenilson.myretrofitapplication.dagger2.module.infraestructure.ServiceModule;
import com.treinamento.adenilson.myretrofitapplication.dagger2.module.presentation.HelperModule;

import javax.inject.Singleton;

import dagger.Component;


/**
 * Created by falvojr on 1/12/17.
 */
@Singleton
@Component(modules = {
        ApplicationModule.class,
        HelperModule.class,
        PreferencesModule.class,
        NetworkModule.class,
        ServiceModule.class,
        ManagerModule.class
})
public interface DiComponent {
  UiComponent uiComponent();
}