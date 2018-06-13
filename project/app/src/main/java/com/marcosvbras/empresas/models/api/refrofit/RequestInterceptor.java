package com.marcosvbras.empresas.models.api.refrofit;

import com.marcosvbras.empresas.views.utils.Constants;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RequestInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        HashMap<String, String> credentials = EnterpriseApp.getInstance().getCredentials();
        String accessToken = credentials.get(Constants.ACCESS_TOKEN_KEY);
        String client = credentials.get(Constants.CLIENT_KEY);
        String uid = credentials.get(Constants.UID_KEY);

        if(accessToken != null && client != null && uid != null) {
            Request modifiedRequest = request.newBuilder()
                    .header("Content-Type", "application/json")
                    .header(Constants.ACCESS_TOKEN_KEY, accessToken)
                    .header(Constants.CLIENT_KEY, client)
                    .header(Constants.UID_KEY, uid)
                    .method(request.method(), request.body())
                    .build();
            request = modifiedRequest;
        }

        Response response = chain.proceed(request);

        if(response.code() == Constants.OK && response.headers() != null) {
            uid = response.headers().get(Constants.UID_KEY);
            accessToken = response.headers().get(Constants.ACCESS_TOKEN_KEY);
            client = response.headers().get(Constants.CLIENT_KEY);
            EnterpriseApp.getInstance().writeCredentials(accessToken, client, uid);
        }

        return response;
    }
}
