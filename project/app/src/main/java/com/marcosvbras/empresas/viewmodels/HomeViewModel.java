package com.marcosvbras.empresas.viewmodels;

import android.annotation.SuppressLint;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.text.TextUtils;

import com.marcosvbras.empresas.app.EnterpriseApp;
import com.marcosvbras.empresas.models.api.refrofit.APIService;
import com.marcosvbras.empresas.models.domain.Enterprise;
import com.marcosvbras.empresas.views.activities.LoginActivity;
import com.marcosvbras.empresas.views.adapters.EnterpriseAdapter;
import com.marcosvbras.empresas.views.listeners.BaseViewModelCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends BaseViewModel {

    private BaseViewModelCallback baseCallback;
    private Disposable disposable;
    public ObservableBoolean isListEmpty = new ObservableBoolean(true);
    public ObservableField<List<Enterprise>> listEnterprise = new ObservableField<>();
    public ObservableField<EnterpriseAdapter> enterpriseAdapter = new ObservableField<>();

    public HomeViewModel(BaseViewModelCallback baseCallback) {
        this.baseCallback = baseCallback;

        if (!EnterpriseApp.getInstance().hasCredentials()) {
            baseCallback.openActivity(LoginActivity.class, true);
            return;
        }

        config();
    }

    private void config() {
        listEnterprise.set(new ArrayList<>());
        enterpriseAdapter.set(new EnterpriseAdapter(listEnterprise.get(), baseCallback));
    }

    @SuppressLint("CheckResult")
    public void requestEnterprises(String query) {
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();

        if(TextUtils.isEmpty(query)) {
            APIService.getInstance().getAllEnterprises()
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
                        listEnterprise.set(next.getListEnterprises());
                        isListEmpty.set(Objects.requireNonNull(listEnterprise.get()).size() == 0);
                    }, error -> {
                        isLoading.set(false);
                        baseCallback.showErrorDialog(error.getMessage());
                    });
        } else {
            APIService.getInstance().getEnterprises(query)
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
                        listEnterprise.set(next.getListEnterprises());
                        isListEmpty.set(Objects.requireNonNull(listEnterprise.get()).size() == 0);
                    }, error -> {
                        isLoading.set(false);
                        baseCallback.showErrorDialog(error.getMessage());
                    });
        }
    }

}
