package com.marcosvbras.empresas.models.api;

import android.support.annotation.NonNull;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserModel {

    private OnRequestUserListener requestListener;
    private Disposable disposable;

    public interface OnRequestUserListener extends RequestCommons {
        void onLoginSuccessful();
        void onLoginFailure(String message);
    }

    public UserModel(@NonNull OnRequestUserListener requestListener) {
        this.requestListener = requestListener;
    }

    public void login(@NonNull LoginBody loginBody) {
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();

        APIService.getInstance()
                .login(loginBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Void>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(Void aVoid) {
                        requestListener.onRequestStarted();
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        requestListener.onRequestFinished();
                        requestListener.onRequestError(throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        requestListener.onRequestFinished();
                        requestListener.onLoginSuccessful();
                        disposable.dispose();
                        disposable = null;
                    }
                });
    }

}
