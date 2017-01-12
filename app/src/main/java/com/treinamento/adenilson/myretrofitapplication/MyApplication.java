package com.treinamento.adenilson.myretrofitapplication;

import android.app.Application;

import com.treinamento.adenilson.myretrofitapplication.component.DaggerDiComponent;
import com.treinamento.adenilson.myretrofitapplication.component.DiComponent;
import com.treinamento.adenilson.myretrofitapplication.dagger2.module.ApplicationModule;

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


    public DiComponent getDaggerDiComponent() {
        return mDiComponent;
    }

}
