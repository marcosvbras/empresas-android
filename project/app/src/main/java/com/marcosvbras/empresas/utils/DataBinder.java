package com.marcosvbras.empresas.utils;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import com.marcosvbras.empresas.business.domain.Enterprise;
import com.marcosvbras.empresas.views.adapters.EnterpriseAdapter;

import java.util.List;

public class DataBinder {

    @BindingAdapter(value="setError")
    public static void setError(EditText editText, ErrorObservable errorObservable) {
        if (errorObservable.hasError()) {
            if (errorObservable.getErrorInt() != null)
                editText.setError(editText.getContext().getString(errorObservable.getErrorInt()));
            else if (errorObservable.getError() != null)
                editText.setError(errorObservable.getError());

            errorObservable.clear();
        }
    }

    @BindingAdapter({"adapter", "data"})
    public static void bindData(RecyclerView recyclerView, EnterpriseAdapter adapter, List<Enterprise> list) {
        recyclerView.setAdapter(adapter);
        adapter.updateItems(list);
    }

}
