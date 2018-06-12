package com.marcosvbras.empresas.viewmodels;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.marcosvbras.empresas.R;
import com.marcosvbras.empresas.models.api.EnterpriseModel;
import com.marcosvbras.empresas.models.api.UserModel;
import com.marcosvbras.empresas.models.domain.Enterprise;
import com.marcosvbras.empresas.views.activities.LoginActivity;
import com.marcosvbras.empresas.views.listeners.BaseViewModelCallback;

import java.util.List;

public class DetailViewModel extends BaseViewModel implements EnterpriseModel.OnRequestEnterpriseListener {

    private EnterpriseModel enterpriseModel;
    private BaseViewModelCallback baseCallback;
    public ObservableField<Enterprise> enterprise = new ObservableField<>();

    public DetailViewModel(@NonNull BaseViewModelCallback baseCallback) {
        this.baseCallback = baseCallback;

        if (!UserModel.isAuthenticated()) {
            baseCallback.openActivity(LoginActivity.class, true);
            return;
        }

        enterpriseModel = new EnterpriseModel(this);
    }

    public void requestEnterpriseById(@NonNull int id) {
        enterpriseModel.requestSingleEnterprise(id);
    }

    @Override
    public void onSingleEnterpriseReceived(Enterprise enterprise) {
        this.enterprise.set(enterprise);
        baseCallback.setToolbarTitle(enterprise.getEnterpriseName());
    }

    @Override
    public void onEnterpriseListReceived(List<Enterprise> enterpriseList) {

    }

    @Override
    public void onEnterpriseRequestFailure(String message) {
        baseCallback.showErrorDialog(message);
    }

    @Override
    public void onRequestError(String message) {
        baseCallback.showErrorDialog(message);
    }

    @Override
    public void onRequestStarted() {
        isLoading.set(true);
    }

    @Override
    public void onRequestFinished() {
        isLoading.set(false);
    }

    @Override
    public void onUnauthorizedRequest() {
        UserModel.deleteCredentials();
        baseCallback.openActivity(LoginActivity.class, true);
    }

    @Override
    public void onServerError() {
        baseCallback.showErrorDialog(R.string.server_error_message);
    }
}
