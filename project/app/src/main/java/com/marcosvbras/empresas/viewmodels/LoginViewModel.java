package com.marcosvbras.empresas.viewmodels;

import android.databinding.ObservableField;
import android.text.TextUtils;
import android.util.Patterns;

import com.marcosvbras.empresas.ErrorObservable;
import com.marcosvbras.empresas.LoginViewModelCallback;
import com.marcosvbras.empresas.R;
import com.marcosvbras.empresas.models.api.LoginBody;
import com.marcosvbras.empresas.models.api.UserModel;

public class LoginViewModel extends BaseViewModel implements UserModel.OnRequestUserListener {

    private UserModel userModel;
    private LoginViewModelCallback loginCallback;
    public final ObservableField<String> email = new ObservableField<>();
    public final ObservableField<String> password = new ObservableField<>();
    public final ErrorObservable error = new ErrorObservable();

    public LoginViewModel(LoginViewModelCallback loginCallback) {
        this.loginCallback = loginCallback;

        if (UserModel.isAuthenticated())
            loginCallback.onSignedIn();

        userModel = new UserModel(this);
    }

    public void requestLogin() {
        if (isFormValid(email.get(), password.get()))
            userModel.login(new LoginBody(email.get(), password.get()));
    }

    private boolean isFormValid(String email, String password) {
        if (TextUtils.isEmpty(email)) {
            error.set(R.string.required_field_error);
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            error.set(R.string.invalid_email_format_error);
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            error.set(R.string.required_field_error);
            return false;
        }

        return true;
    }

    @Override
    public void onLoginSuccessful() {
        loginCallback.onSignedIn();
    }

    @Override
    public void onLoginFailure(String message) {
        loginCallback.showError(message);
    }

    @Override
    public void onRequestError(String message) {
        loginCallback.showError(message);
    }

    @Override
    public void onRequestStarted() {
        isLoading.set(true);
    }

    @Override
    public void onRequestFinished() {
        isLoading.set(false);
    }

    @Override
    public void onUnauthorizedRequest() {
        loginCallback.showError(R.string.wrong_email_or_password);
    }

    @Override
    public void onServerError() {
        loginCallback.showError(R.string.server_error_message);
    }
}
