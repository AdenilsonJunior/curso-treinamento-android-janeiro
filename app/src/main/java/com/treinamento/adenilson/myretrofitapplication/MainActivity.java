package com.treinamento.adenilson.myretrofitapplication;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.treinamento.adenilson.myretrofitapplication.domain.GitHubStatusApi;
import com.treinamento.adenilson.myretrofitapplication.domain.entity.Status;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;

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
    private TextInputEditText mTextInputUsername;
    private TextInputEditText mTextInputPassword;
    private Button mButtonLogin;

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
                changeStatus(Status.Type.MAJOR.getColor(), t.getMessage());
            }
        });
    }

    private void changeStatus(int color, String message) {
        DrawableCompat.setTint(mImageViewGit.getDrawable(), color);
        mTextViewStatus.setText(message);
        mTextViewStatus.setTextColor(color);

    }

    private void bindViews() {
        mImageViewGit = (ImageView) findViewById(R.id.image_view_git);
        mTextViewStatus = (TextView) findViewById(R.id.text_view_status);
        mTextInputUsername = (TextInputEditText) findViewById(R.id.text_input_user);
        mTextInputPassword = (TextInputEditText) findViewById(R.id.text_input_pass);

        mButtonLogin = (Button) findViewById(R.id.button_login);
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mTextInputUsername.getText().toString();
                String password = mTextInputPassword.getText().toString();
                if(!username.isEmpty()
                        || !password.isEmpty()){


                    
                }else{
                    Toast.makeText(MainActivity.this, R.string.message_empty_field, Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();

        requestStatusApi();
    }
}
