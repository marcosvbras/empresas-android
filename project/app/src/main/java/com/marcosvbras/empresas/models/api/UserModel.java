package com.marcosvbras.empresas.models.api;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.marcosvbras.empresas.views.utils.Constants;
import com.marcosvbras.empresas.EnterpriseApp;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserModel {

    private OnRequestUserListener requestListener;

    public interface OnRequestUserListener extends RequestCommons {
        void onLoginSuccessful();
        void onLoginFailure(String message);
    }

    public UserModel(@NonNull OnRequestUserListener requestListener) {
        this.requestListener = requestListener;
    }

    public void login(@NonNull LoginBody loginBody) {
        requestListener.onRequestStarted();
        Call<Void> call = new RetrofitConfig(getCredentials()).getLoginService().login(loginBody);
        call.enqueue(onCallback());
    }

    private Callback<Void> onCallback() {
        return new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                requestListener.onRequestFinished();

                if (response.code() == Constants.UNAUTHORIZED)
                    requestListener.onUnauthorizedRequest();
                else if (response.code() == Constants.SERVER_ERROR)
                    requestListener.onServerError();
                else if(response.code() == Constants.OK && response.headers() != null) {
                    String uid = response.headers().get(Constants.UID_KEY);
                    String accessToken = response.headers().get(Constants.ACCESS_TOKEN_KEY);
                    String client = response.headers().get(Constants.CLIENT_KEY);
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
        SharedPreferences sharedPreferences = EnterpriseApp.getInstance().getPreferences(Constants.AUTH_PREF_KEY);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constants.ACCESS_TOKEN_KEY, accessToken);
        editor.putString(Constants.CLIENT_KEY, client);
        editor.putString(Constants.UID_KEY, uid);
        editor.commit();
    }

    public static void deleteCredentials() {
        SharedPreferences.Editor editor = EnterpriseApp.getInstance().getPreferences(Constants.AUTH_PREF_KEY).edit();
        editor.putString(Constants.ACCESS_TOKEN_KEY, null);
        editor.putString(Constants.CLIENT_KEY, null);
        editor.putString(Constants.UID_KEY, null);
        editor.commit();
    }

    public static boolean isAuthenticated() {
        SharedPreferences sharedPreferences = EnterpriseApp.getInstance().getPreferences(Constants.AUTH_PREF_KEY);
        String accessToken = sharedPreferences.getString(Constants.ACCESS_TOKEN_KEY, null);
        String client = sharedPreferences.getString(Constants.CLIENT_KEY, null);
        String uid = sharedPreferences.getString(Constants.UID_KEY, null);
        return accessToken != null && client != null && uid != null;
    }

    public static HashMap<String, String> getCredentials() {
        SharedPreferences sharedPreferences = EnterpriseApp.getInstance().getPreferences(Constants.AUTH_PREF_KEY);
        String accessToken = sharedPreferences.getString(Constants.ACCESS_TOKEN_KEY, "");
        String client = sharedPreferences.getString(Constants.CLIENT_KEY, "");
        String uid = sharedPreferences.getString(Constants.UID_KEY, "");
        HashMap<String, String> credentials = new HashMap<>();
        credentials.put(Constants.ACCESS_TOKEN_KEY, accessToken);
        credentials.put(Constants.CLIENT_KEY, client);
        credentials.put(Constants.UID_KEY, uid);
        return credentials;
    }

}
