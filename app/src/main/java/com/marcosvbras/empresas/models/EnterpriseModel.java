package com.marcosvbras.empresas.models;

import com.google.gson.annotations.SerializedName;

public class EnterpriseModel {

    private int id;
    private int value;
    private int shares;
    private String description;
    private String facebook;
    private String twitter;
    private String linkedin;
    private String phone;
    private String photo;
    private String city;
    private String country;
    @SerializedName("enterprise_name")
    private String enterpriseName;
    @SerializedName("email_enterprise")
    private String emailEnterprise;
    @SerializedName("own_enterprise")
    private boolean ownEnterprise;
    @SerializedName("share_price")
    private int sharePrice;
    @SerializedName("own_shares")
    private int ownShares;
    @SerializedName("enterprise_type")
    private EnterpriseTypeModel enterpriseType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmailEnterprise() {
        return emailEnterprise;
    }

    public void setEmailEnterprise(String emailEnterprise) {
        this.emailEnterprise = emailEnterprise;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean isOwnEnterprise() {
        return ownEnterprise;
    }

    public void setOwnEnterprise(boolean ownEnterprise) {
        this.ownEnterprise = ownEnterprise;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    public int getSharePrice() {
        return sharePrice;
    }

    public void setSharePrice(int sharePrice) {
        this.sharePrice = sharePrice;
    }

    public int getOwnShares() {
        return ownShares;
    }

    public void setOwnShares(int ownShares) {
        this.ownShares = ownShares;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public EnterpriseTypeModel getEnterpriseType() {
        return enterpriseType;
    }

    public void setEnterpriseType(EnterpriseTypeModel enterpriseType) {
        this.enterpriseType = enterpriseType;
    }
}
