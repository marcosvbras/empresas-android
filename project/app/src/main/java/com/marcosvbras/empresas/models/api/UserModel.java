package com.marcosvbras.empresas.models.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.marcosvbras.empresas.EnterpriseApplication;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserModel {

    private Context context;
    private OnRequestUserListener requestListener;
    public static final String ACCESS_TOKEN_KEY = "access-token";
    public static final String CLIENT_KEY = "client";
    public static final String UID_KEY = "uid";
    public static final String AUTH_PREF_KEY = "auth_data";

    public interface OnRequestUserListener extends RequestCommons {
        void onLoginSuccessful();
        void onLoginFailure(String message);
    }

    public UserModel(@NonNull OnRequestUserListener requestListener, @NonNull Context context) {
        this.context = context;
        this.requestListener = requestListener;
    }

    public void login(@NonNull LoginBody loginBody) {
        requestListener.onRequestStarted();
        Call<Void> call = new RetrofitConfig(getCredentials(context)).getLoginService().login(loginBody);
        call.enqueue(onCallback());
    }

    private Callback<Void> onCallback() {
        return new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                requestListener.onRequestFinished();

                if (response.code() == OnRequestUserListener.UNAUTHORIZED)
                    requestListener.onUnauthorizedRequest();
                else if (response.code() == OnRequestUserListener.SERVER_ERROR)
                    requestListener.onServerError();
                else if(response.code() == OnRequestUserListener.OK && response.headers() != null) {
                    String uid = response.headers().get(UID_KEY);
                    String accessToken = response.headers().get(ACCESS_TOKEN_KEY);
                    String client = response.headers().get(CLIENT_KEY);
                    writeCredentials(accessToken, client, uid);
                    requestListener.onLoginSuccessful();
                } else {
                    requestListener.onLoginFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                requestListener.onRequestFinished();
                requestListener.onRequestError(throwable.getMessage());
            }
        };
    }

    private void writeCredentials(String accessToken, String client, String uid) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(AUTH_PREF_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(UserModel.ACCESS_TOKEN_KEY, accessToken);
        editor.putString(UserModel.CLIENT_KEY, client);
        editor.putString(UserModel.UID_KEY, uid);
        editor.commit();
    }

    public static void deleteCredentials(Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(AUTH_PREF_KEY, Context.MODE_PRIVATE).edit();
        editor.putString(UserModel.ACCESS_TOKEN_KEY, null);
        editor.putString(UserModel.CLIENT_KEY, null);
        editor.putString(UserModel.UID_KEY, null);
        editor.commit();
    }

    public static boolean isAuthenticated(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(AUTH_PREF_KEY, Context.MODE_PRIVATE);
        String accessToken = sharedPreferences.getString(UserModel.ACCESS_TOKEN_KEY, null);
        String client = sharedPreferences.getString(UserModel.CLIENT_KEY, null);
        String uid = sharedPreferences.getString(UserModel.UID_KEY, null);
        return accessToken != null && client != null && uid != null;
    }

    public static HashMap<String, String> getCredentials(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(AUTH_PREF_KEY, Context.MODE_PRIVATE);
        String accessToken = sharedPreferences.getString(UserModel.ACCESS_TOKEN_KEY, "");
        String client = sharedPreferences.getString(UserModel.CLIENT_KEY, "");
        String uid = sharedPreferences.getString(UserModel.UID_KEY, "");
        HashMap<String, String> credentials = new HashMap<>();
        credentials.put(ACCESS_TOKEN_KEY, accessToken);
        credentials.put(CLIENT_KEY, client);
        credentials.put(UID_KEY, uid);
        return credentials;
    }

}
