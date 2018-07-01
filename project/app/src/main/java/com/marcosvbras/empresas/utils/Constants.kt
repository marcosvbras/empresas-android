//@file:JvmName("Constants")

package com.marcosvbras.empresas.utils

const val UNAUTHORIZED = 401
const val SERVER_ERROR = 500
const val OK = 200
const val BASE_API_URL = "http://54.94.179.135:8090/api/v1/"
const val ACCESS_TOKEN_KEY = "access-token"
const val CLIENT_KEY = "client"
const val UID_KEY = "uid"
const val AUTH_PREF_KEY = "auth_data"
const val CONTENT_TYPE_KEY = "Content-Type"
const val JSON_CONTENT_TYPE_HEADER = "Content-Type: application/json"
const val SIGN_IN_ENDPOINT = "users/auth/sign_in"
const val ENTERPRISES_LIST_ENDPOINT = "enterprises"
const val FILTER_ENTERPRISE_ENDPOINT = "enterprises?enterprise_types=1"
const val GET_ENTERPRISE_ENDPOINT = "enterprises/{id}"