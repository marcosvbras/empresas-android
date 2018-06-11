package com.marcosvbras.empresas.services;

import com.marcosvbras.empresas.models.EnterpriseModel;
import com.marcosvbras.empresas.retrofit.EnterpriseDetailResponse;
import com.marcosvbras.empresas.retrofit.EnterpriseResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EnterpriseService {

    @GET("enterprises")
    Call<EnterpriseResponse> getAllEnterprises();

    @GET("enterprises?enterprise_types=1")
    Call<EnterpriseResponse> getEnterprises(@Query("name") String name);

    @GET("enterprises/{id}")
    Call<EnterpriseDetailResponse> getEnterprise(@Path("id") int id);

}
