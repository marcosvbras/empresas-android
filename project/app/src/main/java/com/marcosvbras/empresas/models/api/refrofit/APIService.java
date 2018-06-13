package com.marcosvbras.empresas.models.api.refrofit;

import com.marcosvbras.empresas.BuildConfig;
import com.marcosvbras.empresas.views.utils.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(new RequestInterceptor());

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
            logInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
            logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logInterceptor);
        }

        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_API_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();

        instance = retrofit.create(APIEndpoints.class);
    }
}
