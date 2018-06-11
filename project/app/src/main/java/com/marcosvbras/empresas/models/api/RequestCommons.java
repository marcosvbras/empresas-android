package com.marcosvbras.empresas.models.api;

public interface RequestCommons {

    int UNAUTHORIZED = 401;
    int SERVER_ERROR = 500;
    int OK = 200;
    void onRequestError(String message);
    void onRequestStarted();
    void onRequestFinished();
    void onUnauthorizedRequest();
    void onServerError();

}
