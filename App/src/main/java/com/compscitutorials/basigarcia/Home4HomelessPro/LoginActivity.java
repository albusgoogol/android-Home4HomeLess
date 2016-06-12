/**
 * Author: Ravi Tamada
 * URL: www.androidhive.info
 * twitter: http://twitter.com/ravitamada
 */
package com.compscitutorials.basigarcia.Home4HomelessPro;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.compscitutorials.basigarcia.Home4HomelessPro.manager.HttpManager;
import com.compscitutorials.basigarcia.Home4HomelessPro.model.LoginCollection;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends Activity {
    private static final String TAG = RegisterActivity.class.getSimpleName();
    private Button btnLogin;
    private Button btnLinkToRegister;
    private EditText inputEmail;
    private EditText inputPassword;
    private ProgressDialog dialog;

    private SharedPreferences sf;

    Call<LoginCollection> call;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sf = getSharedPreferences("state", MODE_PRIVATE);

        if (sf.getString("login", "false").equals("true")) {
            if (sf.getString("status", "unknow").equals("พลเมืองดี")) {
                startActivity(new Intent(LoginActivity.this, UserActivity.class));
                finish();
            } else {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
        }

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

            String email = inputEmail.getText().toString();
            String password = inputPassword.getText().toString();

            call = HttpManager.getInstance().getService().login(email, password, "android");
            call.enqueue(new CallbackData());
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (call != null && !call.isCanceled())
            call.cancel();
    }

    private class CallbackData implements Callback<LoginCollection> {
        @Override
        public void onResponse(Call<LoginCollection> call, Response<LoginCollection> response) {
            if (response.isSuccessful() && response.body().getSuccess().equals("true")) {
                SharedPreferences.Editor editor = sf.edit();
                editor.putString("login", "true");

                if (response.body().getStatus().equals("พลเมืองดี")) {
                    startActivity(new Intent(LoginActivity.this, UserActivity.class));
                    editor.putString("status", "พลเมืองดี");
                    finish();
                } else {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    editor.putString("status", "เจ้าหน้าที่");
                    finish();
                }
                editor.apply();
            } else {
                inputPassword.setText("");
                Toast.makeText(LoginActivity.this, "รหัสผ่านหรืออีเมลไม่ถูกต้อง", Toast.LENGTH_LONG).show();
            }
            dialog.cancel();
        }

        @Override
        public void onFailure(Call<LoginCollection> call, Throwable t) {
            Toast.makeText(LoginActivity.this, t.getMessage().toString(), Toast.LENGTH_LONG).show();
            dialog.cancel();
        }
    }
}
