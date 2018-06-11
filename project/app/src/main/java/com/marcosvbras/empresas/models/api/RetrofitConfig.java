package com.marcosvbras.empresas.models.api;

import android.content.Context;
import android.content.SharedPreferences;

import com.marcosvbras.empresas.EnterpriseApplication;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private Retrofit retrofit;
    private HashMap<String, String> credentials;
    private OkHttpClient okHttpClient;
    public static final String BASE_API_URL = "http://54.94.179.135:8090/api/v1/";

    public RetrofitConfig(HashMap<String, String> credentials) {
        this.credentials = credentials;
        createClient();
        this.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    private void createClient() {
        String accessToken = credentials.get(UserModel.ACCESS_TOKEN_KEY);
        String client = credentials.get(UserModel.CLIENT_KEY);
        String uid = credentials.get(UserModel.UID_KEY);

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
