package com.marcosvbras.empresas;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.marcosvbras.empresas.models.api.UserModel;

public class EnterpriseApplication extends Application {

    private static EnterpriseApplication instance;

    public static EnterpriseApplication getInstance() {
        if (instance == null)
            instance = new EnterpriseApplication();

        return instance;
    }
}
