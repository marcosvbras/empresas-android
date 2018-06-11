package com.marcosvbras.empresas;

import com.marcosvbras.empresas.models.domain.Enterprise;

import java.util.List;

public interface HomeViewModelCallback extends BaseViewModelCallback {
    void onSearchResponse(List<Enterprise> list);
    void onInvalidAuthentication();
}
