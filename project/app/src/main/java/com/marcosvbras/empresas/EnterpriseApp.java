package com.marcosvbras.empresas;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

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
}
