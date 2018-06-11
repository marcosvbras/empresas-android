package com.marcosvbras.empresas.views.activities;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.marcosvbras.empresas.EnterpriseApplication;
import com.marcosvbras.empresas.R;
import com.marcosvbras.empresas.models.api.UserModel;
import com.marcosvbras.empresas.models.domain.Enterprise;
import com.marcosvbras.empresas.presenters.DetailPresenter;
import com.marcosvbras.empresas.views.interfaces.DetailView;

public class DetailActivity extends MvpAppCompatActivity implements DetailView {

    @InjectPresenter
    DetailPresenter detailPresenter;
    private int id;
    private TextView textViewDescription;
    private TextView textViewLetters;
    private AlertDialog.Builder alertDialog;

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
        alertDialog = new AlertDialog.Builder(this);
    }

    @Override
    public void showErrorDialog(String message) {
        alertDialog
                .setMessage(message)
                .setPositiveButton(getString(R.string.ok), null)
                .show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        detailPresenter.requestEnterpriseBy(id);
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
        UserModel.deleteCredentials(this);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
