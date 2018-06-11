package com.marcosvbras.empresas.models.domain;

import com.google.gson.annotations.SerializedName;

public class EnterpriseType {

    private int id;
    @SerializedName("enterprise_type_name")
    private String enterpriseTypeName;

    public EnterpriseType() {}

    public EnterpriseType(int id, String enterpriseTypeName) {
        this.id = id;
        this.enterpriseTypeName = enterpriseTypeName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnterpriseTypeName() {
        return enterpriseTypeName;
    }

    public void setEnterpriseTypeName(String enterpriseTypeName) {
        this.enterpriseTypeName = enterpriseTypeName;
    }
}
