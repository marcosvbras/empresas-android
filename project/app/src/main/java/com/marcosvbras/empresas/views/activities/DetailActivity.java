package com.marcosvbras.empresas.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.marcosvbras.empresas.R;
import com.marcosvbras.empresas.models.api.UserModel;
import com.marcosvbras.empresas.models.domain.Enterprise;
import com.marcosvbras.empresas.views.interfaces.DetailView;

public class DetailActivity extends BaseActivity implements DetailView {

    private int id;
    private TextView textViewDescription;
    private TextView textViewLetters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        bindViews();
    }

    private void bindViews() {
        setSupportActionBar(findViewById(R.id.top_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        id = getIntent().getExtras().getInt("id");
        textViewDescription = findViewById(R.id.text_view_description);
        textViewLetters = findViewById(R.id.text_view_letters);
    }

    @Override
    public void showErrorDialog(String message) {
        showErrorDialog(message);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        detailPresenter.requestEnterpriseBy(id);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showEnterpriseInfo(Enterprise enterprise) {
        if(enterprise != null) {
            getSupportActionBar().setTitle(enterprise.getEnterpriseName());
            textViewDescription.setText(enterprise.getDescription());
        }
    }

    @Override
    public void onInvalidAuthentication() {
        UserModel.deleteCredentials();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
