package com.marcosvbras.empresas.retrofit;

import com.google.gson.annotations.SerializedName;
import com.marcosvbras.empresas.models.EnterpriseModel;

import java.util.List;

public class EnterpriseResponse {

    @SerializedName("enterprises")
    private List<EnterpriseModel> listEnterprises;

    public List<EnterpriseModel> getListEnterprises() {
        return listEnterprises;
    }

    public void setListEnterprises(List<EnterpriseModel> listEnterprises) {
        this.listEnterprises = listEnterprises;
    }
}
