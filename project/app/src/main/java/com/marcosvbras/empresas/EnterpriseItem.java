package com.marcosvbras.empresas;

import com.genius.groupie.Item;
import com.marcosvbras.empresas.databinding.ItemEnterpriseBinding;
import com.marcosvbras.empresas.models.domain.Enterprise;

public class EnterpriseItem extends Item<ItemEnterpriseBinding> {

    private Enterprise enterprise;

    public EnterpriseItem(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    @Override
    public int getLayout() {
        return R.layout.item_enterprise;
    }

    @Override
    public void bind(ItemEnterpriseBinding viewBinding, int position) {
        viewBinding.setEnterprise(enterprise);
    }
}
