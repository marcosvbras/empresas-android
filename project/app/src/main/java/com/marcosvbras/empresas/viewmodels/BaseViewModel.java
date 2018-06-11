package com.marcosvbras.empresas.viewmodels;

import android.databinding.Bindable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

public class BaseViewModel {

    private ObservableField<String> toolbarTitle = new ObservableField<>("");
    private ObservableBoolean isLoading = new ObservableBoolean();

    @Bindable
    public ObservableField<String> getToolbarTitle() {
        return toolbarTitle;
    }

    @Bindable
    public ObservableBoolean getIsLoading() {
        return isLoading;
    }

}
