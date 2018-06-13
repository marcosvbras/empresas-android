package com.marcosvbras.empresas.models.api;

import com.marcosvbras.empresas.views.utils.Constants;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIEndpoints {

    @Headers(Constants.JSON_CONTENT_TYPE_HEADER)
    @POST(Constants.SIGN_IN_ENDPOINT)
    Observable<Void> login(@Body LoginBody login);

    @GET(Constants.ENTERPRISES_LIST_ENDPOINT)
    Call<EnterpriseResponse> getAllEnterprises();

    @GET(Constants.FILTER_ENTERPRISE_ENDPOINT)
    Call<EnterpriseResponse> getEnterprises(@Query("name") String name);

    @GET(Constants.GET_ENTERPRISE_ENDPOINT)
    Call<EnterpriseDetailResponse> getEnterprise(@Path("id") int id);


}
