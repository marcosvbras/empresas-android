package com.marcosvbras.empresas.viewmodels;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

public class BaseViewModel {

    public ObservableField<String> toolbarTitle = new ObservableField<>("");
    public ObservableBoolean isLoading = new ObservableBoolean();

}
