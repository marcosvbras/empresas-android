package com.marcosvbras.empresas.retrofit;

import android.content.Context;
import android.content.SharedPreferences;

import com.marcosvbras.empresas.R;
import com.marcosvbras.empresas.services.EnterpriseService;
import com.marcosvbras.empresas.services.LoginService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private Context context;
    private Retrofit retrofit;
    private OkHttpClient okHttpClient;
    public static final String BASE_API_URL = "http://54.94.179.135:8090/api/v1/";

    public RetrofitConfig(Context context) {
        this.context = context;
        createClient();
        this.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    private void createClient() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                context.getString(R.string.auth_pref),
                Context.MODE_PRIVATE);
        String accessToken = sharedPreferences.getString(context.getString(R.string.access_token_key), "");
        String client = sharedPreferences.getString(context.getString(R.string.client_key), "");
        String uid = sharedPreferences.getString(context.getString(R.string.uid_key), "");

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
