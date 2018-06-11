package com.marcosvbras.empresas.views.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.marcosvbras.empresas.views.listeners.DetailViewModelCallBack;
import com.marcosvbras.empresas.R;
import com.marcosvbras.empresas.databinding.ActivityDetailBinding;
import com.marcosvbras.empresas.models.api.UserModel;
import com.marcosvbras.empresas.models.domain.Enterprise;
import com.marcosvbras.empresas.viewmodels.DetailViewModel;

public class DetailActivity extends BaseActivity implements DetailViewModelCallBack {

    private int id;
    private ActivityDetailBinding activityDetailBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DetailViewModel detailViewModel = new DetailViewModel(this);
//        setContentView(R.layout.activity_detail);
        activityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        activityDetailBinding.setViewModel(detailViewModel);
        activityDetailBinding.executePendingBindings();
        config();
    }

    private void config() {
        setSupportActionBar(findViewById(R.id.top_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        id = getIntent().getExtras().getInt("id");
    }

    @Override
    protected void onResume() {
        super.onResume();
        activityDetailBinding.getViewModel().requestEnterpriseById(id);
    }

    @Override
    public void onEnterpriseResponse(Enterprise enterprise) {
        if(enterprise != null) {
            getSupportActionBar().setTitle(enterprise.getEnterpriseName());
        }
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showMessage(int message) {

    }

    @Override
    public void showError(String message) {
        showErrorDialog(message);
    }

    @Override
    public void showError(int message) {
        showErrorDialog(getString(message));
    }

    @Override
    public void onInvalidAuthentication() {
        UserModel.deleteCredentials();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
