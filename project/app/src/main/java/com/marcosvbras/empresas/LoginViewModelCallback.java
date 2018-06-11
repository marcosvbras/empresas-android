package com.marcosvbras.empresas;

public interface LoginViewModelCallback extends BaseViewModelCallback {

    void onSignedIn();

    void onInvalidEmail(int message);

    void onInvalidPassword(int message);

}
