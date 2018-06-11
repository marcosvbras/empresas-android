package com.marcosvbras.empresas.views.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.marcosvbras.empresas.R;
import com.marcosvbras.empresas.databinding.ActivityLoginBinding;
import com.marcosvbras.empresas.viewmodels.LoginViewModel;

public class LoginActivity extends BaseActivity implements LoginViewModel.LoginViewModelCallback {

    private ActivityLoginBinding activityLoginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginViewModel loginViewModel = new LoginViewModel(this);
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        activityLoginBinding.setViewModel(loginViewModel);
        activityLoginBinding.executePendingBindings();
    }

    @Override
    public void onSignedIn(){
        startNewActivity(HomeActivity.class, null, true);
    }

    @Override
    public void showErrorDialog(String message) {
        showErrorDialog(message);
    }

    @Override
    public void showErrorDialog(int message) {
        showErrorDialog(message);
    }
}
