package com.marcosvbras.empresas.views.utils;

public class Constants {

    public static final int UNAUTHORIZED = 401;
    public static final int SERVER_ERROR = 500;
    public static final int OK = 200;
    public static final String BASE_API_URL = "http://54.94.179.135:8090/api/v1/";
    public static final String ACCESS_TOKEN_KEY = "access-token";
    public static final String CLIENT_KEY = "client";
    public static final String UID_KEY = "uid";
    public static final String AUTH_PREF_KEY = "auth_data";
    public static final String JSON_CONTENT_TYPE_HEADER = "Content-Type: application/json";
    public static final String SIGN_IN_ENDPOINT = "users/auth/sign_in";
    public static final String ENTERPRISES_LIST_ENDPOINT = "enterprises";
    public static final String FILTER_ENTERPRISE_ENDPOINT = "enterprises?enterprise_types=1";
    public static final String GET_ENTERPRISE_ENDPOINT = "enterprises/{id}";

}
