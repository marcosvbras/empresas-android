package com.marcosvbras.empresas.views.listeners;

import com.marcosvbras.empresas.models.domain.Enterprise;
import com.marcosvbras.empresas.views.listeners.BaseViewModelCallback;

public interface DetailViewModelCallBack extends BaseViewModelCallback {
    void onEnterpriseResponse(Enterprise enterprise);
    void onInvalidAuthentication();
}
