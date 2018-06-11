package com.marcosvbras.empresas.viewmodels;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;

import com.marcosvbras.empresas.models.api.EnterpriseModel;
import com.marcosvbras.empresas.models.api.UserModel;
import com.marcosvbras.empresas.models.domain.Enterprise;

import java.util.List;

public class DetailViewModel implements EnterpriseModel.OnRequestEnterpriseListener {

    private Context context;
    private EnterpriseModel enterpriseModel;
    public ObservableBoolean isAuthenticated = new ObservableBoolean();

    public DetailViewModel(Context context) {
        this.context = context;
        isAuthenticated.set(UserModel.isAuthenticated() ? true : false);
        enterpriseModel = new EnterpriseModel(this);
    }

    public void requestEnterpriseBy(@NonNull int id) {
        enterpriseModel.requestSingleEnterprise(id);
    }

    @Override
    public void onSingleEnterpriseReceived(Enterprise enterprise) {

    }

    @Override
    public void onEnterpriseListReceived(List<Enterprise> enterpriseList) {

    }

    @Override
    public void onEnterpriseRequestFailure(String message) {

    }

    @Override
    public void onRequestError(String message) {

    }

    @Override
    public void onRequestStarted() {

    }

    @Override
    public void onRequestFinished() {

    }

    @Override
    public void onUnauthorizedRequest() {

    }

    @Override
    public void onServerError() {

    }
}
