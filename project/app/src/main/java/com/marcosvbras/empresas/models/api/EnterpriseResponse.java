package com.marcosvbras.empresas.models.api;

import com.google.gson.annotations.SerializedName;
import com.marcosvbras.empresas.models.domain.Enterprise;

import java.util.List;

public class EnterpriseResponse {

    @SerializedName("enterprises")
    private List<Enterprise> listEnterprises;

    public List<Enterprise> getListEnterprises() {
        return listEnterprises;
    }

    public void setListEnterprises(List<Enterprise> listEnterprises) {
        this.listEnterprises = listEnterprises;
    }
}
