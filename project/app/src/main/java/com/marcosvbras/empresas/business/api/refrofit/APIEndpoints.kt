package com.marcosvbras.empresas.business.api.refrofit

import com.marcosvbras.empresas.business.api.responses.EnterpriseDetailResponse
import com.marcosvbras.empresas.business.api.responses.EnterpriseResponse
import com.marcosvbras.empresas.business.api.responses.LoginBody
import com.marcosvbras.empresas.utils.*
import io.reactivex.Observable
import retrofit2.http.*

interface APIEndpoints {

    @Headers(JSON_CONTENT_TYPE_HEADER)
    @POST(SIGN_IN_ENDPOINT)
    fun login(loginBody: LoginBody) : Observable<Void>

    @GET(ENTERPRISES_LIST_ENDPOINT)
    fun getAllEnterprises() : Observable<EnterpriseResponse>

    @GET(FILTER_ENTERPRISE_ENDPOINT)
    fun getEnterprises(@Query("name") name: String) : Observable<EnterpriseResponse>

    @GET(GET_ENTERPRISE_ENDPOINT)
    fun getEnterprise(@Path("id") id: Int) : Observable<EnterpriseDetailResponse>
}