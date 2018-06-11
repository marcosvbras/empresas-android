package com.marcosvbras.empresas.views.utils;

import android.databinding.BaseObservable;

public class ErrorObservable extends BaseObservable {

    private String error;
    private Integer errorString;

    public ErrorObservable() {}

    public ErrorObservable(String error) {
        this.error = error;
    }

    public String get() {
        return error;
    }

    public void set(String error) {
        this.error = error;
        notifyChange();
    }

    public Integer getIntError() {
        return errorString;
    }

    public void set(Integer errorString) {
        this.errorString = errorString;
        notifyChange();
    }

    public void clear() {
        this.error = null;
        this.errorString = null;
        notifyChange();
    }

    public boolean hasError() {
        return error != null || errorString != null;
    }
}
