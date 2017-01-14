package com.treinamento.adenilson.myretrofitapplication.presentation.ui.auth;

import com.treinamento.adenilson.myretrofitapplication.domain.entity.Status;
import com.treinamento.adenilson.myretrofitapplication.domain.entity.User;

/**
 * Created by adenilson on 13/01/17.
 */

public interface AuthContract {

    interface View {
        void onLoadStatusType(Status.Type statusType);

        void onAuthSuccess(String credential, User entity);

        void showError(String message);
    }

    interface Presenter {
        void setView(AuthContract.View view);

        void loadStatus();

        void getUser(String authorization);

        void callAccessToken(String cliId, String cliSecret, String code);
    }


}


