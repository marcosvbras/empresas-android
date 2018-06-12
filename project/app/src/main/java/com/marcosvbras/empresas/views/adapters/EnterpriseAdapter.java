package com.marcosvbras.empresas.views.adapters;

import android.support.annotation.NonNull;

import com.genius.groupie.GroupAdapter;
import com.marcosvbras.empresas.models.domain.Enterprise;
import com.marcosvbras.empresas.views.listeners.BaseViewModelCallback;

import java.util.ArrayList;
import java.util.List;

public class EnterpriseAdapter extends GroupAdapter {

    private List<Enterprise> listEnterprises;
    private BaseViewModelCallback baseCallback;

    public EnterpriseAdapter(@NonNull List<Enterprise> listEnterprises, BaseViewModelCallback baseCallback) {
        if (listEnterprises == null)
            this.listEnterprises = new ArrayList<>();

        this.listEnterprises = listEnterprises;
        this.baseCallback = baseCallback;
    }

    private void populateAdapter() {
        for(Enterprise e : listEnterprises)
            add(new EnterpriseItem(e, baseCallback));

        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(listEnterprises != null)
            return listEnterprises.size();

        return 0;
    }

    public void updateItems(@NonNull List<Enterprise> listEnterprises) {
        if(listEnterprises == null)
            this.listEnterprises = new ArrayList<>();

        this.listEnterprises.clear();
        this.listEnterprises.addAll(listEnterprises);
        this.notifyDataSetChanged();
        populateAdapter();
    }

    public List<Enterprise> getCurrentList() {
        return this.listEnterprises;
    }
}
