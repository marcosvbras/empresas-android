package com.marcosvbras.empresas.views.utils;

import android.databinding.BindingAdapter;
import android.widget.EditText;

public class DataBinder {

    @BindingAdapter(value="setError")
    public static void serError(EditText editText, ErrorObservable errorObservable) {
        if (errorObservable.hasError()) {
            if (errorObservable.getIntError() != null)
                editText.setError(editText.getContext().getString(errorObservable.getIntError()));
            else if (errorObservable.get() != null)
                editText.setError(errorObservable.get());

            errorObservable.clear();
        }
    }

}
