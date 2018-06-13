package com.marcosvbras.empresas.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.marcosvbras.empresas.views.utils.Constants;

import java.util.HashMap;

public class EnterpriseApp extends Application {

    private static EnterpriseApp instance;

    public static EnterpriseApp getInstance() {
        return instance;
    }

    public SharedPreferences getPreferences(String preferenceKey) {
        return getSharedPreferences(preferenceKey, Context.MODE_PRIVATE);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public void writeCredentials(String accessToken, String client, String uid) {
        SharedPreferences sharedPreferences = EnterpriseApp.getInstance().getPreferences(Constants.AUTH_PREF_KEY);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constants.ACCESS_TOKEN_KEY, accessToken);
        editor.putString(Constants.CLIENT_KEY, client);
        editor.putString(Constants.UID_KEY, uid);
        editor.commit();
    }

    public void deleteCredentials() {
        SharedPreferences.Editor editor = instance.getPreferences(Constants.AUTH_PREF_KEY).edit();
        editor.putString(Constants.ACCESS_TOKEN_KEY, null);
        editor.putString(Constants.CLIENT_KEY, null);
        editor.putString(Constants.UID_KEY, null);
        editor.commit();
    }

    public boolean hasCredentials() {
        SharedPreferences sharedPreferences = instance.getPreferences(Constants.AUTH_PREF_KEY);
        String accessToken = sharedPreferences.getString(Constants.ACCESS_TOKEN_KEY, null);
        String client = sharedPreferences.getString(Constants.CLIENT_KEY, null);
        String uid = sharedPreferences.getString(Constants.UID_KEY, null);

        return accessToken != null && client != null && uid != null;
    }

    public HashMap<String, String> getCredentials() {
        SharedPreferences sharedPreferences = instance.getPreferences(Constants.AUTH_PREF_KEY);
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
