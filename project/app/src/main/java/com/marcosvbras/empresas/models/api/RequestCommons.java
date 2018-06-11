package com.marcosvbras.empresas.models.api;

public interface RequestCommons {

    void onRequestError(String message);
    void onRequestStarted();
    void onRequestFinished();
    void onUnauthorizedRequest();
    void onServerError();

}
