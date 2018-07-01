package com.marcosvbras.empresas.business.api.refrofit

import com.marcosvbras.empresas.BuildConfig
import com.marcosvbras.empresas.utils.BASE_API_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class APIService private constructor() {

    private var retrofit: Retrofit

    init {
        val builder = OkHttpClient.Builder()
                .addInterceptor(RequestInterceptor())

        if(BuildConfig.DEBUG) {
            val logInterceptor = HttpLoggingInterceptor()
            logInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
            logInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(logInterceptor)
        }

        retrofit = Retrofit.Builder()
                .baseUrl(BASE_API_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build()

        service = retrofit.create(APIEndpoints::class.java)
    }

    companion object {

        private var service: APIEndpoints? = null

        fun getService(): APIEndpoints? {
            if (service == null)
                APIService()

            return service
        }
    }
}