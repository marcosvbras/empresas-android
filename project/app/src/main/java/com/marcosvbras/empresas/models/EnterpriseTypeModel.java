package com.marcosvbras.empresas.models;

import com.google.gson.annotations.SerializedName;

public class EnterpriseTypeModel {

    private int id;
    @SerializedName("enterprise_type_name")
    private String enterpriseTypeName;

    public EnterpriseTypeModel() {}

    public EnterpriseTypeModel(int id, String enterpriseTypeName) {
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
