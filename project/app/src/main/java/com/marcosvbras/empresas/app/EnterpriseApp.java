package com.marcosvbras.empresas.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

import static com.marcosvbras.empresas.utils.ConstantsKt.ACCESS_TOKEN_KEY;
import static com.marcosvbras.empresas.utils.ConstantsKt.AUTH_PREF_KEY;
import static com.marcosvbras.empresas.utils.ConstantsKt.CLIENT_KEY;
import static com.marcosvbras.empresas.utils.ConstantsKt.UID_KEY;

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
        SharedPreferences sharedPreferences = EnterpriseApp.getInstance().getPreferences(AUTH_PREF_KEY);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ACCESS_TOKEN_KEY, accessToken);
        editor.putString(CLIENT_KEY, client);
        editor.putString(UID_KEY, uid);
        editor.commit();
    }

    public void deleteCredentials() {
        SharedPreferences.Editor editor = instance.getPreferences(AUTH_PREF_KEY).edit();
        editor.putString(ACCESS_TOKEN_KEY, null);
        editor.putString(CLIENT_KEY, null);
        editor.putString(UID_KEY, null);
        editor.commit();
    }

    public boolean hasCredentials() {
        SharedPreferences sharedPreferences = instance.getPreferences(AUTH_PREF_KEY);
        String accessToken = sharedPreferences.getString(ACCESS_TOKEN_KEY, null);
        String client = sharedPreferences.getString(CLIENT_KEY, null);
        String uid = sharedPreferences.getString(UID_KEY, null);

        return accessToken != null && client != null && uid != null;
    }

    public HashMap<String, String> getCredentials() {
        SharedPreferences sharedPreferences = instance.getPreferences(AUTH_PREF_KEY);
        String accessToken = sharedPreferences.getString(ACCESS_TOKEN_KEY, "");
        String client = sharedPreferences.getString(CLIENT_KEY, "");
        String uid = sharedPreferences.getString(UID_KEY, "");
        HashMap<String, String> credentials = new HashMap<>();
        credentials.put(ACCESS_TOKEN_KEY, accessToken);
        credentials.put(CLIENT_KEY, client);
        credentials.put(UID_KEY, uid);

        return credentials;
    }
}
