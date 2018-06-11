package com.marcosvbras.empresas.presenters;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.viewstate.MvpViewState;
import com.marcosvbras.empresas.EnterpriseApplication;
import com.marcosvbras.empresas.R;
import com.marcosvbras.empresas.models.api.EnterpriseModel;
import com.marcosvbras.empresas.models.api.UserModel;
import com.marcosvbras.empresas.models.domain.Enterprise;
import com.marcosvbras.empresas.views.interfaces.HomeView;

import java.util.List;

@InjectViewState
public class HomePresenter extends MvpPresenter<HomeView> implements EnterpriseModel.OnRequestEnterpriseListener {

    private EnterpriseModel enterpriseModel;

    @Override
    public void setViewState(MvpViewState<HomeView> viewState) {
        super.setViewState(viewState);

        if(!UserModel.isAuthenticated((Context)getViewState()))
            getViewState().onInvalidAuthentication();
    }

    @Override
    public void attachView(HomeView view) {
        super.attachView(view);

        if(enterpriseModel == null)
            enterpriseModel = new EnterpriseModel(this, (Context)view);
    }

    public void requestEnterprises(String query) {
       enterpriseModel.requestEnterprises(query);
    }

    @Override
    public void onEnterpriseRequestFailure(String message) {
        getViewState().showErrorDialog(message);
    }

    @Override
    public void onSingleEnterpriseReceived(Enterprise enterprise) {

    }

    @Override
    public void onEnterpriseListReceived(List<Enterprise> enterpriseList) {
        getViewState().updateRecyclerView(enterpriseList);
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
