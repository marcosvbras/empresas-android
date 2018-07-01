package com.marcosvbras.empresas.viewmodels;

import android.annotation.SuppressLint;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.marcosvbras.empresas.app.EnterpriseApp;
import com.marcosvbras.empresas.business.api.refrofit.APIService;
import com.marcosvbras.empresas.business.domain.Enterprise;
import com.marcosvbras.empresas.views.activities.LoginActivity;
import com.marcosvbras.empresas.views.listeners.BaseViewModelCallback;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DetailViewModel extends BaseViewModel {

    private BaseViewModelCallback baseCallback;
    private Disposable disposable;
    public ObservableField<Enterprise> enterprise = new ObservableField<>();

    public DetailViewModel(@NonNull BaseViewModelCallback baseCallback) {
        this.baseCallback = baseCallback;

        if (!EnterpriseApp.getInstance().hasCredentials()) {
            baseCallback.openActivity(LoginActivity.class, true);
            return;
        }
    }

    @SuppressLint("CheckResult")
    public void requestSingleEnterprise(@NonNull int id) {
        if(disposable != null && !disposable.isDisposed())
            disposable.dispose();

        APIService.Companion.getService().getEnterprise(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d -> {
                    isLoading.set(true);
                    disposable = d;
                })
                .doOnComplete(() -> {
                    isLoading.set(false);
                    disposable.dispose();
                    disposable = null;
                })
                .subscribe(next -> {
                    enterprise.set(next.getEnterprise());
                    baseCallback.setToolbarTitle(enterprise.get().getEnterpriseName());
                }, error -> {
                    isLoading.set(false);
                    baseCallback.showErrorDialog(error.getMessage());
                });
    }
}
