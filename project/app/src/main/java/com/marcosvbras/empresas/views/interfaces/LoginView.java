package com.marcosvbras.empresas.views.interfaces;

import com.arellomobile.mvp.MvpView;

public interface LoginView extends MvpView {

    void showErrorDialog(String message);

    void showLoading();

    void hideLoading();

    void onSignIn();

    void showEditTextError(int viewId, int message);

}
