package com.marcosvbras.empresas.views.listeners;

import com.marcosvbras.empresas.models.domain.Enterprise;
import com.marcosvbras.empresas.views.listeners.BaseViewModelCallback;

import java.util.List;

public interface HomeViewModelCallback extends BaseViewModelCallback {
    void onSearchResponse(List<Enterprise> list);
    void onInvalidAuthentication();
}
