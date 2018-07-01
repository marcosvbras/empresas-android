package com.marcosvbras.empresas.business.api.refrofit

import com.marcosvbras.empresas.app.EnterpriseApp
import com.marcosvbras.empresas.utils.*
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response? {
        var request = chain?.request()
        val credentials = EnterpriseApp.getInstance().credentials
        var accessToken = credentials[ACCESS_TOKEN_KEY]
        var client = credentials[CLIENT_KEY]
        var uid = credentials[UID_KEY]

        if(!accessToken.isNullOrEmpty() && !client.isNullOrEmpty() && !uid.isNullOrEmpty()) {
            request = request?.newBuilder()
                    ?.header(CONTENT_TYPE_KEY, JSON_CONTENT_TYPE_HEADER)
                    ?.header(ACCESS_TOKEN_KEY, accessToken)
                    ?.header(CLIENT_KEY, client)
                    ?.header(UID_KEY, uid)
                    ?.method(request.method(), request.body())
                    ?.build()
        }

        val response = chain?.proceed(request)

        if(response?.code() == OK && response.headers() != null) {
            uid = response.headers()[UID_KEY]
            client = response.headers()[CLIENT_KEY]
            accessToken = response.headers()[ACCESS_TOKEN_KEY]
            EnterpriseApp.getInstance().writeCredentials(accessToken, client, uid)
        }

        return response
    }
}