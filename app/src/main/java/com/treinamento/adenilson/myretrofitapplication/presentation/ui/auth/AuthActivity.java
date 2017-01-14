package com.treinamento.adenilson.myretrofitapplication.presentation.ui.auth;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.treinamento.adenilson.myretrofitapplication.presentation.base.BaseActivity;
import com.treinamento.adenilson.myretrofitapplication.R;
import com.treinamento.adenilson.myretrofitapplication.domain.entity.Status;
import com.treinamento.adenilson.myretrofitapplication.domain.entity.User;
import com.treinamento.adenilson.myretrofitapplication.infraestructure.storage.service
        .GitHubOAuthService;
import com.treinamento.adenilson.myretrofitapplication.presentation.helper.AppHelper;
import com.treinamento.adenilson.myretrofitapplication.presentation.ui.followers.FollowersActivity;
import com.treinamento.adenilson.myretrofitapplication.util.SharedPreferencesUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Credentials;

public class AuthActivity extends BaseActivity implements AuthContract.View {

    private String TAG = AuthActivity.class.getSimpleName();
    @BindView(R.id.image_view_git)
    ImageView mImageViewGit;
    @BindView(R.id.text_view_status)
    TextView mTextViewStatus;
    @BindView(R.id.text_input_user)
    TextInputLayout mTextInputUsername;
    @BindView(R.id.text_input_pass)
    TextInputLayout mTextInputPassword;
    @Inject
    AuthContract.Presenter mAuthPresenter;
    @Inject
    AppHelper mAppHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        getMyApplication().getDaggerUiComponent().inject(this);

        mAuthPresenter.setView(this);

        configureViews();
    }

    private void requestStatusApi() {

        mAuthPresenter.loadStatus();
    }

    private void changeStatus(int color, String message) {
        mTextViewStatus.setText(message);
        mTextViewStatus.setTextColor(color);
    }

    private void configureViews() {
        RxTextView.textChanges(mTextInputUsername.getEditText())
                .skip(1)
                .subscribe(text -> {
                    mAppHelper.validateRequiredField(mTextInputUsername);
                });

        RxTextView.textChanges(mTextInputPassword.getEditText())
                .skip(1).subscribe(text -> {
            mAppHelper.validateRequiredField(mTextInputPassword);
        });
    }

    @OnClick(R.id.button_auth)
    public void onOAuth(View view) {
        final String baseUrl = GitHubOAuthService.BASE_URL + "authorize";
        final String clientId = getString(R.string.oauth_client_id);
        final String redirectUri = getOAuthRedirectUri();
        final Uri uri = Uri.parse(baseUrl + "?client_id=" + clientId + "&redirect_uri=" +
                redirectUri);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    @OnClick(R.id.button_login)
    public void onBasicAuth(View view) {
        if (mAppHelper.validateRequiredField(mTextInputUsername,
                mTextInputPassword)) {
            String username = mTextInputUsername.getEditText().getText().toString();
            String password = mTextInputPassword.getEditText().getText().toString();
            requestLogin(username, password);
        }
    }

    private String getOAuthRedirectUri() {
        return getString(R.string.oauth_schema) + "://" + getString(R.string.oauth_host);
    }

    private void requestLogin(final String username, final String password) {
//        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//        httpClient.addInterceptor(chain -> {
//            Request original = chain.request();
//            Request request = original.newBuilder()
//                    .header("Authorization", Credentials.basic(username, password))
//                    .method(original.method(), original.body())
//                    .build();
//            return chain.proceed(request);
//        });
//
//        OkHttpClient client = httpClient.build();
//
//
//        final Retrofit retrofit = new Retrofit.Builder()
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .baseUrl(GitHubUserService.BASE_URL)
//                .build();

        mAuthPresenter.getUser(Credentials.basic(username, password));
    }

    private void processOAuthRedirectUri() {
        // Os intent-filter's permitem a interação com o ACTION_VIEW
        final Uri uri = getIntent().getData();
        if (uri != null && uri.toString().startsWith(this.getOAuthRedirectUri())) {
            String code = uri.getQueryParameter("code");
            if (code != null) {
                String clientId = getString(R.string.oauth_client_id);
                String secretClient = getString(R.string.oauth_client_secret);
                mAuthPresenter.callAccessToken(clientId, secretClient, code);
            } else if (uri.getQueryParameter("error") != null) {
                showError(uri.getQueryParameter("error"));
            }
            // Limpar os dados para evitar chamadas múltiplas
            getIntent().setData(null);
        }
    }

    @OnClick(R.id.button_call)
    public void call(View view) {
        String number = "+55 16 99387-0941";
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        requestStatusApi();
        processOAuthRedirectUri();
    }

    @Override
    public void onLoadStatusType(Status.Type statusType) {

        changeStatus(statusType.getColor(),
                getString(statusType.getMessage()));
    }

    @Override
    public void onAuthSuccess(String credential, User user) {
        SharedPreferencesUtil.saveToken(this, credential);
        Intent intent = new Intent(AuthActivity.this, FollowersActivity.class);
        intent.putExtra(User.USER_KEY, user);
        startActivity(intent);

    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
