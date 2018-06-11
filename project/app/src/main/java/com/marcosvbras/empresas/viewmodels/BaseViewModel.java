package com.marcosvbras.empresas.viewmodels;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

public class BaseViewModel {

    private ObservableField<String> toolbarTitle = new ObservableField<>("");
    private ObservableBoolean isLoading = new ObservableBoolean();

    public ObservableField<String> getToolbarTitle() {
        return toolbarTitle;
    }

    public ObservableBoolean getIsLoading() {
        return isLoading;
    }

}
