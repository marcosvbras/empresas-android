package com.marcosvbras.empresas.viewmodels;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.marcosvbras.empresas.views.listeners.DetailViewModelCallBack;
import com.marcosvbras.empresas.R;
import com.marcosvbras.empresas.models.api.EnterpriseModel;
import com.marcosvbras.empresas.models.api.UserModel;
import com.marcosvbras.empresas.models.domain.Enterprise;

import java.util.List;

public class DetailViewModel extends BaseViewModel implements EnterpriseModel.OnRequestEnterpriseListener {

    private EnterpriseModel enterpriseModel;
    private DetailViewModelCallBack detailCallback;
    public ObservableField<Enterprise> enterprise = new ObservableField<>();

    public DetailViewModel(DetailViewModelCallBack detailCallback) {
        this.detailCallback = detailCallback;

        if (!UserModel.isAuthenticated())
            detailCallback.onInvalidAuthentication();

        enterpriseModel = new EnterpriseModel(this);
    }

    public void requestEnterpriseById(@NonNull int id) {
        enterpriseModel.requestSingleEnterprise(id);
    }

    @Override
    public void onSingleEnterpriseReceived(Enterprise enterprise) {
        detailCallback.onEnterpriseResponse(enterprise);
        this.enterprise.set(enterprise);
    }

    @Override
    public void onEnterpriseListReceived(List<Enterprise> enterpriseList) {

    }

    @Override
    public void onEnterpriseRequestFailure(String message) {
        detailCallback.showError(message);
    }

    @Override
    public void onRequestError(String message) {
        detailCallback.showError(message);
    }

    @Override
    public void onRequestStarted() {
        getIsLoading().set(true);
    }

    @Override
    public void onRequestFinished() {
        getIsLoading().set(false);
    }

    @Override
    public void onUnauthorizedRequest() {
        detailCallback.onInvalidAuthentication();
    }

    @Override
    public void onServerError() {
        detailCallback.showError(R.string.server_error_message);
    }
}
