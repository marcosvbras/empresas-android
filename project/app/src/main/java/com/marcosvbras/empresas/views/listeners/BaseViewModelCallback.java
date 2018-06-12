package com.marcosvbras.empresas.views.listeners;

import android.os.Bundle;

public interface BaseViewModelCallback {

    void showErrorDialog(String message);
    void showErrorDialog(int message);
    void openActivity(Class<?> activity, boolean finishCurrentActivity);
    void openActivity(Class<?> activity, Bundle bundle, boolean finishCurrentActivity);
    void setToolbar(int viewId, String title, boolean displayHomeAsUpEnabled);
    void setToolbarTitle(String title);

}
