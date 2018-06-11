package com.marcosvbras.empresas.viewmodels;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.marcosvbras.empresas.R;
import com.marcosvbras.empresas.models.api.EnterpriseModel;
import com.marcosvbras.empresas.models.api.UserModel;
import com.marcosvbras.empresas.models.domain.Enterprise;
import com.marcosvbras.empresas.views.adapters.EnterpriseAdapter;
import com.marcosvbras.empresas.views.listeners.BaseViewModelCallback;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends BaseViewModel implements EnterpriseModel.OnRequestEnterpriseListener {

    private BaseViewModelCallback baseCallback;
    private EnterpriseModel enterpriseModel;
    public ObservableBoolean isListEmpty = new ObservableBoolean(true);
    public ObservableField<List<Enterprise>> listEnterprise = new ObservableField<>();
    public ObservableField<EnterpriseAdapter> enterpriseAdapter = new ObservableField<>();

    public HomeViewModel(BaseViewModelCallback baseCallback) {
        this.baseCallback = baseCallback;

        if (!UserModel.isAuthenticated())
            onUnauthorizedRequest();

        config();
    }

    private void config() {
        listEnterprise.set(new ArrayList<>());
        enterpriseModel = new EnterpriseModel(this);
        enterpriseAdapter.set(new EnterpriseAdapter(listEnterprise.get()));
    }

    public void requestEnterprises(String query) {
        enterpriseModel.requestEnterprises(query);
    }

    @Override
    public void onSingleEnterpriseReceived(Enterprise enterprise) {

    }

    @Override
    public void onEnterpriseListReceived(List<Enterprise> enterpriseList) {
        this.listEnterprise.set(enterpriseList);
        isListEmpty.set(enterpriseList == null || enterpriseList.size() == 0 ? true : false);
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
        baseCallback.onInvalidAuthentication();
    }

    @Override
    public void onServerError() {
        baseCallback.showErrorDialog(R.string.server_error_message);
    }

}
