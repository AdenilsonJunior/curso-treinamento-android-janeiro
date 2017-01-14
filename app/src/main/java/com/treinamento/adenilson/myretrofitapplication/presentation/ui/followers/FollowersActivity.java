package com.treinamento.adenilson.myretrofitapplication.presentation.ui.followers;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ListView;

import com.treinamento.adenilson.myretrofitapplication.R;
import com.treinamento.adenilson.myretrofitapplication.domain.entity.Follower;
import com.treinamento.adenilson.myretrofitapplication.domain.entity.User;
import com.treinamento.adenilson.myretrofitapplication.presentation.base.BaseActivity;
import com.treinamento.adenilson.myretrofitapplication.presentation.ui.auth.AuthContract;
import com.treinamento.adenilson.myretrofitapplication.util.SharedPreferencesUtil;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by adenilson on 14/01/17.
 */

public class FollowersActivity extends BaseActivity implements FollowersContract.View{

    @BindView(R.id.list_view_followers)
    ListView mListViewFollowers;

    @Inject
    FollowersContract.Presenter mPresenter;

    private User mUser;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);

        ButterKnife.bind(this);
        getMyApplication().getDaggerUiComponent().inject(this);
        mPresenter.setView(this);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            mUser = extras.getParcelable(User.USER_KEY);
        }

        mPresenter.getFollowers(SharedPreferencesUtil.getToken(this));




    }

    @Override
    public void onLoadFollowers(List<Follower> followers) {
        Log.i("Sucesso", "followers");

        mListViewFollowers.setAdapter(new FollowersListAdapter(followers));

    }

    @Override
    public void showError(String error) {
        Log.i("error", "followers");

    }
}
