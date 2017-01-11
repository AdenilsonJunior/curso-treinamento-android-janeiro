package com.treinamento.adenilson.myretrofitapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.treinamento.adenilson.myretrofitapplication.domain.GitHubStatusApi;
import com.treinamento.adenilson.myretrofitapplication.domain.GitHubUserApi;
import com.treinamento.adenilson.myretrofitapplication.domain.entity.Status;
import com.treinamento.adenilson.myretrofitapplication.domain.entity.User;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private ImageView mImageViewGit;
    private TextView mTextViewStatus;
    private GitHubStatusApi mStatusApi;
    private GitHubUserApi mUserApi;
    private TextInputLayout mTextInputUsername;
    private TextInputLayout mTextInputPassword;
    private Button mButtonLogin;
    private Button mButtonAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        createStatusApi();
    }

    private void createStatusApi() {

        //"2012-12-07T18:11:55Z"
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                .create();

        final Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(GitHubStatusApi.BASE_URL)
                .build();

        mStatusApi = retrofit.create(GitHubStatusApi.class);
    }

    private void requestStatusApi() {

        Call<Status> call = mStatusApi.lastMessage();
        call.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                Status body = response.body();
                if (response.isSuccessful()) {
                    changeStatus(body.getType().getColor(), body.getBody());
                    String date = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format
                            (body.created_on);
                    Toast.makeText(MainActivity.this, date, Toast.LENGTH_SHORT).show();

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
            }
        });
    }

    private void changeStatus(int color, String message) {
        mTextViewStatus.setText(message);
        mTextViewStatus.setTextColor(color);
    }

    private void bindViews() {
        mImageViewGit = (ImageView) findViewById(R.id.image_view_git);
        mTextViewStatus = (TextView) findViewById(R.id.text_view_status);

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
        mTextInputUsername = (TextInputLayout) findViewById(R.id.text_input_user);
        mTextInputPassword = (TextInputLayout) findViewById(R.id.text_input_pass);
        mTextInputUsername.getEditText().addTextChangedListener(textWatcher);
        mTextInputPassword.getEditText().addTextChangedListener(textWatcher1);

        mButtonAuth = (Button) findViewById(R.id.button_auth);

        mButtonLogin = (Button) findViewById(R.id.button_login);
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mTextInputUsername.getEditText().getText().toString();
                String password = mTextInputPassword.getEditText().getText().toString();
                if(!username.isEmpty()
                        || !password.isEmpty()){
                    requestLogin(username, password);

                    
                }else{
                    Toast.makeText(MainActivity.this, R.string.message_empty_field, Toast.LENGTH_SHORT)
                            .show();
                    mTextInputUsername.setError("Campo vazio");
                    mTextInputPassword.setError("Campo vazio");
                }
            }
        });
    }

    private void requestLogin(final String username, final String password) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("Authorization", Credentials.basic(username, password))
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
        });

        OkHttpClient client = httpClient.build();


        final Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl(GitHubUserApi.BASE_URL)
                .build();

        mUserApi = retrofit.create(GitHubUserApi.class);

        mUserApi.getUser().enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    Toast.makeText(MainActivity.this, response.body().login, Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        Toast.makeText(MainActivity.this, response.errorBody().string(), Toast.LENGTH_SHORT).show();
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


    @Override
    protected void onResume() {
        super.onResume();

        requestStatusApi();
    }
}
