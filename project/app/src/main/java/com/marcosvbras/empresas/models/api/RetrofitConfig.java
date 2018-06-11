package com.marcosvbras.empresas.models.api;

import com.marcosvbras.empresas.Constants;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private Retrofit retrofit;
    private HashMap<String, String> credentials;
    private OkHttpClient okHttpClient;

    public RetrofitConfig(HashMap<String, String> credentials) {
        this.credentials = credentials;
        createClient();
        this.retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    private void createClient() {
        String accessToken = credentials.get(Constants.ACCESS_TOKEN_KEY);
        String client = credentials.get(Constants.CLIENT_KEY);
        String uid = credentials.get(Constants.UID_KEY);

        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new RequestInterceptor(accessToken, client, uid))
                .build();
    }

    public LoginService getLoginService() {
        return this.retrofit.create(LoginService.class);
    }

    public EnterpriseService getEnterpriseService() {
        return this.retrofit.create(EnterpriseService.class);
    }
}
