package com.marcosvbras.empresas.models.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface LoginService {

    @Headers("Content-Type: application/json")
    @POST("users/auth/sign_in")
    Call<Void> login(@Body LoginBody login);

}
