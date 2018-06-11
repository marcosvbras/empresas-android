package com.marcosvbras.empresas.models.api;

import com.marcosvbras.empresas.views.utils.Constants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RequestInterceptor implements Interceptor {

    private String accessToken;
    private String client;
    private String uid;

    public RequestInterceptor(String accessToken, String client, String uid) {
        this.accessToken = accessToken;
        this.client = client;
        this.uid = uid;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();

        Request request = originalRequest.newBuilder()
                .header("Content-Type", "application/json")
                .header(Constants.ACCESS_TOKEN_KEY, accessToken)
                .header(Constants.CLIENT_KEY, client)
                .header(Constants.UID_KEY, uid)
                .method(originalRequest.method(), originalRequest.body())
                .build();
        Response response = chain.proceed(request);

        return response;
    }
}
