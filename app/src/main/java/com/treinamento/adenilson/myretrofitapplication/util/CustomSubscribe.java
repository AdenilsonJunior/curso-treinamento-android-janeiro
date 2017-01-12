package com.treinamento.adenilson.myretrofitapplication.util;

import android.app.Activity;
import android.os.Looper;
import android.widget.Toast;

import com.treinamento.adenilson.myretrofitapplication.MainActivity;

import rx.Subscriber;

/**
 * Created by adenilson on 12/01/17.
 */

public abstract class CustomSubscribe<T> extends Subscriber<T> {

    private Activity activity;

    public CustomSubscribe(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        Toast.makeText(activity, e.getMessage(), Toast
                .LENGTH_SHORT).show();
        onError(e.getMessage());
    }

    public abstract void onError(String message);

}
