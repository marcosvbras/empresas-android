package com.marcosvbras.empresas.views.adapters;

import android.support.annotation.NonNull;

import com.genius.groupie.GroupAdapter;
import com.marcosvbras.empresas.models.domain.Enterprise;

import java.util.ArrayList;
import java.util.List;

public class EnterpriseAdapter extends GroupAdapter {

    private List<Enterprise> listEnterprises;

    public EnterpriseAdapter(@NonNull List<Enterprise> listEnterprises) {
        if (listEnterprises == null)
            this.listEnterprises = new ArrayList<>();

        this.listEnterprises = listEnterprises;
    }

    private void populateAdapter() {
        for(Enterprise e : listEnterprises)
            add(new EnterpriseItem(e));

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
