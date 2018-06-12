package com.marcosvbras.empresas.views.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.marcosvbras.empresas.R;
import com.marcosvbras.empresas.databinding.ActivityLoginBinding;
import com.marcosvbras.empresas.viewmodels.LoginViewModel;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginViewModel loginViewModel = new LoginViewModel(this);
        ActivityLoginBinding activityLoginBinding = DataBindingUtil.setContentView(
                this, R.layout.activity_login
        );
        activityLoginBinding.setViewModel(loginViewModel);
        activityLoginBinding.executePendingBindings();
    }
}
