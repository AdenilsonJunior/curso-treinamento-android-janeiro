package com.treinamento.adenilson.myretrofitapplication;

import android.app.Application;

import com.treinamento.adenilson.myretrofitapplication.dagger2.DaggerDiComponent;
import com.treinamento.adenilson.myretrofitapplication.dagger2.module.ApplicationModule;
import com.treinamento.adenilson.myretrofitapplication.dagger2.DiComponent;
import com.treinamento.adenilson.myretrofitapplication.dagger2.UiComponent;

/**
 * Created by adenilson on 12/01/17.
 */

public class MyApplication extends Application {

    private DiComponent mDiComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mDiComponent = DaggerDiComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public UiComponent getDaggerUiComponent() {
        return mDiComponent.uiComponent();
    }

}
