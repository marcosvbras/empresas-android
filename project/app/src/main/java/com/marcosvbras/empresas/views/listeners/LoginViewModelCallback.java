package com.marcosvbras.empresas.views.listeners;

import com.marcosvbras.empresas.views.listeners.BaseViewModelCallback;

public interface LoginViewModelCallback extends BaseViewModelCallback {

    void onSignedIn();

    void onInvalidEmail(int message);

    void onInvalidPassword(int message);

}
