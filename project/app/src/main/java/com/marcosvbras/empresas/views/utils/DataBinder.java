package com.marcosvbras.empresas.views.utils;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import com.marcosvbras.empresas.models.domain.Enterprise;
import com.marcosvbras.empresas.views.adapters.EnterpriseAdapter;

import java.util.List;

public class DataBinder {

    @BindingAdapter(value="setError")
    public static void setError(EditText editText, ErrorObservable errorObservable) {
        if (errorObservable.hasError()) {
            if (errorObservable.getIntError() != null)
                editText.setError(editText.getContext().getString(errorObservable.getIntError()));
            else if (errorObservable.get() != null)
                editText.setError(errorObservable.get());

            errorObservable.clear();
        }
    }

    @BindingAdapter({"adapter", "data"})
    public static void bindData(RecyclerView recyclerView, EnterpriseAdapter adapter, List<Enterprise> list) {
        if(recyclerView.getAdapter() == null)
            recyclerView.setAdapter(adapter);

        adapter.updateItems(list);
    }

}
