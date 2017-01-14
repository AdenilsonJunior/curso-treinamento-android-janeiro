package com.treinamento.adenilson.myretrofitapplication.dagger2.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by adenilson on 13/01/17.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity { }