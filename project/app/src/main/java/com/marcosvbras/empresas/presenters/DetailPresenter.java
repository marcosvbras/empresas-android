package com.marcosvbras.empresas.presenters;

import android.content.Context;
import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.viewstate.MvpViewState;
import com.marcosvbras.empresas.R;
import com.marcosvbras.empresas.models.api.EnterpriseModel;
import com.marcosvbras.empresas.models.domain.Enterprise;
import com.marcosvbras.empresas.views.interfaces.DetailView;

import java.util.List;

@InjectViewState
public class DetailPresenter extends MvpPresenter<DetailView> implements EnterpriseModel.OnRequestEnterpriseListener {

    private EnterpriseModel enterpriseModel;

    @Override
    public void attachView(DetailView view) {
        super.attachView(view);

        if(enterpriseModel == null)
            enterpriseModel = new EnterpriseModel(this, (Context)view);
    }

    public void requestEnterpriseBy(@NonNull int id) {
        enterpriseModel.requestSingleEnterprise(id);
    }

    @Override
    public void onSingleEnterpriseReceived(Enterprise enterprise) {
        getViewState().showEnterpriseInfo(enterprise);
    }

    @Override
    public void onEnterpriseListReceived(List<Enterprise> enterpriseList) {

    }

    @Override
    public void onEnterpriseRequestFailure(String message) {
        getViewState().showErrorDialog(message);
    }

    @Override
    public void onRequestError(String message) {
        getViewState().showErrorDialog(message);
    }

    @Override
    public void onRequestStarted() {
        getViewState().showLoading();
    }

    @Override
    public void onRequestFinished() {
        getViewState().hideLoading();
    }

    @Override
    public void onUnauthorizedRequest() {
        getViewState().onInvalidAuthentication();
    }

    @Override
    public void onServerError() {
        getViewState().showErrorDialog(((Context)getViewState()).getString(R.string.server_error_message));
    }
}
