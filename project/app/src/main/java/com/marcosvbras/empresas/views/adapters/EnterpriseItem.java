package com.marcosvbras.empresas.views.adapters;

import android.os.Bundle;
import android.view.View;

import com.genius.groupie.Item;
import com.marcosvbras.empresas.R;
import com.marcosvbras.empresas.databinding.ItemEnterpriseBinding;
import com.marcosvbras.empresas.models.domain.Enterprise;
import com.marcosvbras.empresas.views.activities.DetailActivity;
import com.marcosvbras.empresas.views.listeners.BaseViewModelCallback;

public class EnterpriseItem extends Item<ItemEnterpriseBinding> implements View.OnClickListener {

    private Enterprise enterprise;
    private BaseViewModelCallback baseCallback;

    public EnterpriseItem(Enterprise enterprise,BaseViewModelCallback baseCallback ) {
        this.enterprise = enterprise;
        this.baseCallback = baseCallback;
    }

    @Override
    public int getLayout() {
        return R.layout.item_enterprise;
    }

    @Override
    public void bind(ItemEnterpriseBinding viewBinding, int position) {
        viewBinding.setEnterprise(enterprise);
        viewBinding.listItem.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(enterprise != null) {
            Bundle bundle = new Bundle();
            bundle.putInt("id", enterprise.getId());
            baseCallback.openActivity(DetailActivity.class, bundle, false);
        }
    }
}
