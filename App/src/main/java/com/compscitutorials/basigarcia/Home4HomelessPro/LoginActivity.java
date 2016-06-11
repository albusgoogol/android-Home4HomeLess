/**
 * Author: Ravi Tamada
 * URL: www.androidhive.info
 * twitter: http://twitter.com/ravitamada
 */
package com.compscitutorials.basigarcia.Home4HomelessPro;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import com.compscitutorials.basigarcia.Home4HomelessPro.app.AppConfig;
import com.compscitutorials.basigarcia.Home4HomelessPro.app.AppController;
import com.compscitutorials.basigarcia.Home4HomelessPro.helper.SQLiteHandler;
import com.compscitutorials.basigarcia.Home4HomelessPro.helper.SessionManager;
import com.compscitutorials.basigarcia.Home4HomelessPro.model.LoginCollection;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.R;
import com.google.gson.Gson;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class LoginActivity extends Activity {
    private static final String TAG = RegisterActivity.class.getSimpleName();
    private Button btnLogin;
    private Button btnLinkToRegister;
    private EditText inputEmail;
    private EditText inputPassword;
    private ProgressDialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dialog = new ProgressDialog(this);

        initialView();
        setupView();
    }

    private void setupView() {

    }

    private void initialView() {
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLinkToRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);

        inputEmail.setText("lambobobo17@gmail.com");

        btnLogin.setOnClickListener(v -> {
            dialog.setMessage("Loading...");
            dialog.show();

            OkHttpClient client = new OkHttpClient();
            RequestBody body = new FormBody.Builder()
                    .add("email", inputEmail.getText().toString())
                    .add("pass", inputPassword.getText().toString())
                    .add("android", "android")
                    .build();


            Request request = new Request.Builder()
                    .url("http://home4homeless.azurewebsites.net/check_login.php")
                    .post(body)
                    .build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                Handler mainHandler = new Handler(getMainLooper());

                @Override
                public void onFailure(Call call, IOException e) {
                    mainHandler.post(() -> {
                        dialog.cancel();
                        Toast.makeText(LoginActivity.this, "กรุณาต่อใช้งานอินเทร์เน็ต", Toast.LENGTH_LONG).show();
                    });

                }

                @Override
                public void onResponse(Call call, okhttp3.Response response) throws IOException {
                    if (response.isSuccessful()) {
                        LoginCollection collection = new Gson().fromJson(response.body().string(), LoginCollection.class);
                        if (collection.getSuccess().equals("true")) {
                            if (collection.getStatus().equals("พลเมืองดี")) {
                                startActivity(new Intent(LoginActivity.this, UserActivity.class));

                            } else {
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            }
                            finish();
                        } else {
                            mainHandler.post(() -> Toast.makeText(LoginActivity.this, "email and password invalid", Toast.LENGTH_LONG).show());
                        }
                    }
                    mainHandler.post(() -> dialog.cancel());

                }
            });
        });
    }
}
