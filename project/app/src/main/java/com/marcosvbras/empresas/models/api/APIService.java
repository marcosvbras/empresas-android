package com.marcosvbras.empresas.models.api;

import com.marcosvbras.empresas.views.utils.Constants;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIService {

    private static APIEndpoints instance;
    private Retrofit retrofit;

    public static APIEndpoints getInstance() {
        if(instance == null)
             new APIService();

        return instance;
    }

    private APIService() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new RequestInterceptor())
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_API_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        instance = retrofit.create(APIEndpoints.class);
    }
}
