package com.marcosvbras.empresas.viewmodels;

import android.databinding.ObservableBoolean;

import com.marcosvbras.empresas.HomeViewModelCallback;
import com.marcosvbras.empresas.R;
import com.marcosvbras.empresas.models.api.EnterpriseModel;
import com.marcosvbras.empresas.models.api.UserModel;
import com.marcosvbras.empresas.models.domain.Enterprise;

import java.util.List;

public class HomeViewModel extends BaseViewModel implements EnterpriseModel.OnRequestEnterpriseListener {

    private HomeViewModelCallback homeCallback;
    private EnterpriseModel enterpriseModel;
    private ObservableBoolean isListEmpty = new ObservableBoolean(true);

    public HomeViewModel(HomeViewModelCallback homeCallback) {
        this.homeCallback = homeCallback;

        if (!UserModel.isAuthenticated())
            onUnauthorizedRequest();

        enterpriseModel = new EnterpriseModel(this);
    }

    public void requestEnterprises(String query) {
        enterpriseModel.requestEnterprises(query);
    }

    @Override
    public void onSingleEnterpriseReceived(Enterprise enterprise) {

    }

    @Override
    public void onEnterpriseListReceived(List<Enterprise> enterpriseList) {
        homeCallback.onSearchResponse(enterpriseList);
        isListEmpty.set(enterpriseList == null || enterpriseList.size() == 0 ? true : false);
    }

    @Override
    public void onEnterpriseRequestFailure(String message) {
        homeCallback.showError(message);
    }

    @Override
    public void onRequestError(String message) {
        homeCallback.showError(message);
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
        homeCallback.onInvalidAuthentication();
    }

    @Override
    public void onServerError() {
        homeCallback.showError(R.string.server_error_message);
    }

}
