package com.marcosvbras.empresas.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.marcosvbras.empresas.R;
import com.marcosvbras.empresas.retrofit.RetrofitConfig;
import com.marcosvbras.empresas.models.LoginModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Call call;
    private AlertDialog.Builder alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        checkAuth();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
    }

    private void checkAuth() {
        SharedPreferences sharedPreferences = getSharedPreferences(
                getString(R.string.auth_pref),
                Context.MODE_PRIVATE);
        String accessToken = sharedPreferences.getString(getString(R.string.access_token_key), null);
        String client = sharedPreferences.getString(getString(R.string.client_key), null);
        String uid = sharedPreferences.getString(getString(R.string.uid_key), null);

        if(accessToken != null && client != null && uid != null)
            goToHome();
    }

    private void bindViews() {
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        findViewById(R.id.buttonEntry).setOnClickListener(onEntryButtonClick());
        alertDialog = new AlertDialog.Builder(this);
    }

    private void showMessage(String message) {
        alertDialog
                .setMessage(message)
                .setPositiveButton(getString(R.string.ok),  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) { }
                })
                .show();
    }

    private View.OnClickListener onEntryButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFormValid()) {
                    LoginModel login = new LoginModel();
                    login.setEmail(editTextEmail.getText().toString());
                    login.setPassword(editTextPassword.getText().toString());

                    call = new RetrofitConfig(getBaseContext()).getLoginService().login(login);
                    call.enqueue(new Callback() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            if(response.code() == 200 && response.headers() != null) {
                                String uid = response.headers().get("uid");
                                String accessToken = response.headers().get("access-token");
                                String client = response.headers().get("client");
                                writeSharedPreferences(accessToken, client, uid);
                                goToHome();
                            } else {
                                showMessage(response.message());
                            }
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                            showMessage(t.getMessage());
                        }
                    });
                }
            }
        };
    }

    private void goToHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void writeSharedPreferences(String accessToken, String client, String uid) {
        SharedPreferences sharedPreferences = getSharedPreferences(
                getString(R.string.auth_pref),
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.access_token_key), accessToken);
        editor.putString(getString(R.string.client_key), client);
        editor.putString(getString(R.string.uid_key), uid);
        editor.commit();
    }

    private boolean isFormValid() {
        if (editTextEmail.getText().toString().equals("") || editTextEmail.getText().toString().indexOf("@") < 0) {
            editTextEmail.setError(getString(R.string.error_invalid_email));
            return false;
        }

        if (editTextPassword.getText().toString().equals("")) {
            editTextPassword.setError(getString(R.string.error_invalid_password));
            return false;
        }

        return true;
    }


}
