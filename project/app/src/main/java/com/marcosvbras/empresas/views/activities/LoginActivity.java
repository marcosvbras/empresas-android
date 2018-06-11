package com.marcosvbras.empresas.views.activities;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.marcosvbras.empresas.R;
import com.marcosvbras.empresas.presenters.LoginPresenter;
import com.marcosvbras.empresas.views.interfaces.LoginView;

public class LoginActivity extends MvpAppCompatActivity implements LoginView {

    @InjectPresenter
    LoginPresenter loginPresenter;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private ProgressBar progressBar;
    private AlertDialog.Builder alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindViews();
    }

    private void bindViews() {
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        progressBar = findViewById(R.id.progress_bar);
        findViewById(R.id.buttonEntry).setOnClickListener(click -> onEntryButtonClick());
        alertDialog = new AlertDialog.Builder(this);
    }

    @Override
    public void showErrorDialog(String message) {
        alertDialog
                .setMessage(message)
                .setPositiveButton(getString(R.string.ok), null)
                .show();
    }

    private void onEntryButtonClick() {
        loginPresenter.signIn(
                editTextEmail.getText().toString(),
                editTextPassword.getText().toString()
        );
    }

    @Override
    public void showEditTextError(int viewId, int message) {
        ((EditText)findViewById(viewId)).setError(getString(message));
        (findViewById(viewId)).requestFocus();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onSignIn() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
