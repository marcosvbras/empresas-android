package com.marcosvbras.empresas.presenters;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.viewstate.MvpViewState;
import com.marcosvbras.empresas.R;
import com.marcosvbras.empresas.models.api.LoginBody;
import com.marcosvbras.empresas.models.api.UserModel;
import com.marcosvbras.empresas.views.interfaces.LoginView;

@InjectViewState
public class LoginPresenter extends MvpPresenter<LoginView> implements UserModel.OnRequestUserListener {

    private UserModel userModel;

    @Override
    public void attachView(LoginView view) {
        super.attachView(view);

        if(UserModel.isAuthenticated((Context)view))
            getViewState().onSignIn();

        if(userModel == null)
            userModel = new UserModel(this, (Context)view);
    }

    public void signIn(String email, String password) {
        if (isFormValid(email, password))
            requestUser(email, password);
    }

    private boolean isFormValid(String email, String password) {
        if(TextUtils.isEmpty(email)) {
            getViewState().showEditTextError(R.id.editTextEmail, R.string.required_field_error);
            return false;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            getViewState().showEditTextError(R.id.editTextEmail, R.string.invalid_email_format_error);
            return false;
        }

        if(TextUtils.isEmpty(password)) {
            getViewState().showEditTextError(R.id.editTextPassword, R.string.required_field_error);
            return false;
        }

        return true;
    }

    private void requestUser(String email, String password) {
        userModel.login(new LoginBody(email, password));
    }

    @Override
    public void onLoginSuccessful() {
        getViewState().onSignIn();
    }

    @Override
    public void onLoginFailure(String message) {
        getViewState().showErrorDialog(message);
    }

    @Override
    public void onRequestError(String message) {
        getViewState().showErrorDialog(message);
    }

    @Override
    public void onRequestStarted() {
        getViewState().showLoading();
    }

    @Override
    public void onRequestFinished() {
        getViewState().hideLoading();
    }

    @Override
    public void onUnauthorizedRequest() {
        getViewState().showErrorDialog(((Context)getViewState()).getString(R.string.wrong_email_or_password));
    }

    @Override
    public void onServerError() {
        getViewState().showErrorDialog(((Context)getViewState()).getString(R.string.server_error_message));
    }
}
