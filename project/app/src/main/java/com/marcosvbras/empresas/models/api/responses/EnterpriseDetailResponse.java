package com.marcosvbras.empresas.models.api.responses;

import com.marcosvbras.empresas.models.domain.Enterprise;

public class EnterpriseDetailResponse {

    private Enterprise enterprise;

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }
}
