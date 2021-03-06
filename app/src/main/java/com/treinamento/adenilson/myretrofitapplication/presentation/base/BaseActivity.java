package com.treinamento.adenilson.myretrofitapplication.presentation.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.treinamento.adenilson.myretrofitapplication.MyApplication;

import butterknife.ButterKnife;

/**
 * Created by adenilson on 12/01/17.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected MyApplication getMyApplication() {
        return (MyApplication) getApplication();
    }
}
