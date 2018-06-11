package com.marcosvbras.empresas.retrofit;

import com.marcosvbras.empresas.models.EnterpriseModel;

public class EnterpriseDetailResponse {

    private EnterpriseModel enterprise;

    public EnterpriseModel getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(EnterpriseModel enterprise) {
        this.enterprise = enterprise;
    }
}
