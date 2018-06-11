package com.marcosvbras.empresas.views.listeners;

public interface BaseViewModelCallback {

    void showErrorDialog(String message);
    void showErrorDialog(int message);
    void onInvalidAuthentication();

}
