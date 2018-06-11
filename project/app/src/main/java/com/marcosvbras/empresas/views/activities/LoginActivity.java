package com.marcosvbras.empresas.views.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.marcosvbras.empresas.LoginViewModelCallback;
import com.marcosvbras.empresas.R;
import com.marcosvbras.empresas.databinding.ActivityLoginBinding;
import com.marcosvbras.empresas.viewmodels.LoginViewModel;

public class LoginActivity extends BaseActivity implements LoginViewModelCallback {

    private ActivityLoginBinding activityLoginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginViewModel loginViewModel = new LoginViewModel(this);
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        activityLoginBinding.setViewModel(loginViewModel);
        activityLoginBinding.executePendingBindings();
    }

    public void onEntryButtonClick(View view) {
        activityLoginBinding.getViewModel().requestLogin();
    }

    @Override
    public void onInvalidEmail(int message) {

    }

    @Override
    public void onInvalidPassword(int message) {

    }

    @Override
    public void onSignedIn() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showMessage(int message) {

    }

    @Override
    public void showError(String message) {
        showErrorDialog(message);
    }

    @Override
    public void showError(int message) {
        showErrorDialog(getString(message));
    }
}
