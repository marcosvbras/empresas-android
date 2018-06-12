package com.marcosvbras.empresas.views.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.marcosvbras.empresas.R;
import com.marcosvbras.empresas.databinding.ActivityDetailBinding;
import com.marcosvbras.empresas.viewmodels.DetailViewModel;

public class DetailActivity extends BaseActivity {

    private int id;
    private ActivityDetailBinding activityDetailBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DetailViewModel detailViewModel = new DetailViewModel(this);
        activityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        activityDetailBinding.setViewModel(detailViewModel);
        activityDetailBinding.executePendingBindings();
        setToolbar(R.id.top_toolbar, null, true);
        id = getIntent().getExtras().getInt("id");
    }

    @Override
    protected void onResume() {
        super.onResume();
        activityDetailBinding.getViewModel().requestEnterpriseById(id);
    }

}
