package com.marcosvbras.empresas.views.interfaces;

import com.marcosvbras.empresas.models.domain.Enterprise;

public interface DetailView {

    void showErrorDialog(String message);
    void showLoading();
    void hideLoading();
    void showEnterpriseInfo(Enterprise enterprise);
    void onInvalidAuthentication();

}
