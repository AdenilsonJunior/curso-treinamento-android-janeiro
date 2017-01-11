package com.treinamento.adenilson.myretrofitapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.treinamento.adenilson.myretrofitapplication.domain.AccessToken;
import com.treinamento.adenilson.myretrofitapplication.domain.GitHubOAuthApi;
import com.treinamento.adenilson.myretrofitapplication.domain.GitHubStatusApi;
import com.treinamento.adenilson.myretrofitapplication.domain.GitHubUserApi;
import com.treinamento.adenilson.myretrofitapplication.domain.entity.Status;
import com.treinamento.adenilson.myretrofitapplication.domain.entity.User;
import com.treinamento.adenilson.myretrofitapplication.util.AppUtil;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.image_view_git)
    ImageView mImageViewGit;
    @BindView(R.id.text_view_status)
    TextView mTextViewStatus;
    @BindView(R.id.text_input_user)
    TextInputLayout mTextInputUsername;
    @BindView(R.id.text_input_pass)
    TextInputLayout mTextInputPassword;
    private GitHubStatusApi mStatusApi;
    private GitHubUserApi mUserApi;
    private GitHubOAuthApi mOAuthApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        configureViews();
        createApis();
    }

    private void createApis() {
        mStatusApi = GitHubStatusApi.RETROFIT.create(GitHubStatusApi.class);
        mOAuthApi = GitHubOAuthApi.RETROFIT.create(GitHubOAuthApi.class);
    }

    private void requestStatusApi() {

        Call<Status> call = mStatusApi.lastMessage();
        call.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                Status body = response.body();
                if (response.isSuccessful()) {
                    changeStatus(body.getType().getColor(), body.getBody());
                    // String date = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format
                    //         (body.created_on);
                    // Toast.makeText(MainActivity.this, date, Toast.LENGTH_SHORT).show();

                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        changeStatus(Status.Type.MAJOR.getColor(), errorBody);
                    } catch (IOException e) {
                        Log.e(TAG, e.getMessage(), e);
                    }


                }

            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void changeStatus(int color, String message) {
        mTextViewStatus.setText(message);
        mTextViewStatus.setTextColor(color);
    }

    private void configureViews() {

        TextWatcher textWatcher = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mTextInputUsername.setError(null);
            }
        };

        TextWatcher textWatcher1 = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mTextInputPassword.setError(null);
            }
        };
        mTextInputUsername.getEditText().addTextChangedListener(textWatcher);
        mTextInputPassword.getEditText().addTextChangedListener(textWatcher1);

    }

    @OnClick(R.id.button_auth)
    public void onOAuth(View view) {
        final String baseUrl = GitHubOAuthApi.BASE_URL + "authorize";
        final String clientId = getString(R.string.oauth_client_id);
        final String redirectUri = getOAuthRedirectUri();
        final Uri uri = Uri.parse(baseUrl + "?client_id=" + clientId + "&redirect_uri=" + redirectUri);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    @OnClick(R.id.button_login)
    public void onBasicAuth(View view) {
        if (AppUtil.validateRequiredField(getApplicationContext(), mTextInputUsername,
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
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("Authorization", Credentials.basic(username, password))
                    .method(original.method(), original.body())
                    .build();
            return chain.proceed(request);
        });

        OkHttpClient client = httpClient.build();


        final Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl(GitHubUserApi.BASE_URL)
                .build();

        mUserApi = retrofit.create(GitHubUserApi.class);

        mUserApi.basicAuth().enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, response.body().login, Toast.LENGTH_SHORT)
                            .show();
                } else {
                    try {
                        Toast.makeText(MainActivity.this, response.errorBody().string(), Toast
                                .LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void processOAuthRedirectUri() {
        // Os intent-filter's permitem a interação com o ACTION_VIEW
        final Uri uri = getIntent().getData();
        if (uri != null && uri.toString().startsWith(this.getOAuthRedirectUri())) {
            String code = uri.getQueryParameter("code");
            if (code != null) {
                String clienteId = getString(R.string.oauth_client_id);
                String secretClient = getString(R.string.oauth_client_secret);
                mOAuthApi.accessToken(clienteId, secretClient, code).enqueue(new Callback<AccessToken>() {
                    @Override
                    public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                        if (response.isSuccessful()) {
                            AccessToken accessToken = response.body();
                            Toast.makeText(MainActivity.this, accessToken.access_token, Toast
                                    .LENGTH_SHORT).show();
                        } else {
                            try {
                                Toast.makeText(MainActivity.this, response.errorBody().string(),
                                        Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<AccessToken> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
            } else if (uri.getQueryParameter("error") != null) {
                //TODO Tratar erro
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
}
