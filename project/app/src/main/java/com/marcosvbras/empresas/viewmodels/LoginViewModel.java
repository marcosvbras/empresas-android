package com.marcosvbras.empresas.viewmodels;

import android.annotation.SuppressLint;
import android.databinding.ObservableField;
import android.text.TextUtils;
import android.util.Patterns;

import com.marcosvbras.empresas.app.EnterpriseApp;
import com.marcosvbras.empresas.models.api.refrofit.APIService;
import com.marcosvbras.empresas.views.activities.HomeActivity;
import com.marcosvbras.empresas.views.listeners.BaseViewModelCallback;
import com.marcosvbras.empresas.views.utils.ErrorObservable;
import com.marcosvbras.empresas.R;
import com.marcosvbras.empresas.models.api.responses.LoginBody;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginViewModel extends BaseViewModel {

    private Disposable disposable;
    private BaseViewModelCallback baseCallback;
    public final ObservableField<String> email = new ObservableField<>();
    public final ObservableField<String> password = new ObservableField<>();
    public final ErrorObservable error = new ErrorObservable();

    public LoginViewModel(BaseViewModelCallback baseCallback) {
        this.baseCallback = baseCallback;

        if (EnterpriseApp.getInstance().hasCredentials()) {
            this.baseCallback.openActivity(HomeActivity.class, true);
            return;
        }
    }

    @SuppressLint("CheckResult")
    public void login() {
        if (isFormValid(email.get(), password.get())) {

            if (disposable != null && !disposable.isDisposed())
                disposable.dispose();

            APIService.getInstance()
                    .login(new LoginBody(email.get(), password.get()))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(d -> {
                        isLoading.set(true);
                        disposable = d;
                    })
                    .doOnComplete(() -> {
                        isLoading.set(false);
                        baseCallback.openActivity(HomeActivity.class, null, true);
                        disposable.dispose();
                        disposable = null;
                    })
                    .subscribe(next -> {

                    }, error -> {
                        isLoading.set(false);
                        baseCallback.showErrorDialog(error.getMessage());
                    }, () -> {

                    });
        }
    }

    private boolean isFormValid(String email, String password) {
        if (TextUtils.isEmpty(email)) {
            error.set(R.string.required_field_error);
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            error.set(R.string.invalid_email_format_error);
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            error.set(R.string.required_field_error);
            return false;
        }

        return true;
    }
}
