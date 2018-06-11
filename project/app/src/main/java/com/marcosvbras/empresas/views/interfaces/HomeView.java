package com.marcosvbras.empresas.views.interfaces;

import com.arellomobile.mvp.MvpView;
import com.marcosvbras.empresas.models.domain.Enterprise;

import java.util.List;

public interface HomeView extends MvpView {

    void updateRecyclerView(List<Enterprise> list);

    void showErrorDialog(String message);

    void onInvalidAuthentication();

    void showLoading();

    void hideLoading();

}
