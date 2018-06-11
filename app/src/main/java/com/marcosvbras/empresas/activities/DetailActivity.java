package com.marcosvbras.empresas.activities;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.marcosvbras.empresas.R;
import com.marcosvbras.empresas.models.EnterpriseModel;
import com.marcosvbras.empresas.retrofit.EnterpriseDetailResponse;
import com.marcosvbras.empresas.retrofit.RetrofitConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private Call<EnterpriseDetailResponse> call;
    private int id;
    private EnterpriseModel enterprise;
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
        setSupportActionBar((Toolbar)findViewById(R.id.top_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        id = getIntent().getExtras().getInt("id");
        textViewDescription = findViewById(R.id.text_view_description);
        textViewLetters = findViewById(R.id.text_view_letters);
        alertDialog = new AlertDialog.Builder(this);
    }

    private void showMessage(String message) {
        alertDialog
                .setMessage(message)
                .setPositiveButton(getString(R.string.ok),  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        call = new RetrofitConfig(this).getEnterpriseService().getEnterprise(id);
        call.enqueue(detailCallback());
    }

    public void showInfo() {
        if(enterprise != null) {
            getSupportActionBar().setTitle(enterprise.getEnterpriseName());
            textViewDescription.setText(enterprise.getDescription());
        }
    }

    private Callback<EnterpriseDetailResponse> detailCallback() {
        return new Callback<EnterpriseDetailResponse>() {
            @Override
            public void onResponse(Call<EnterpriseDetailResponse> call, Response<EnterpriseDetailResponse> response) {
                if(response.isSuccessful() && response.body() != null) {
                    if(response.body().getEnterprise() != null) {
                        enterprise = response.body().getEnterprise();
                        showInfo();
                    }
                } else {
                    showMessage(response.message());
                }
            }

            @Override
            public void onFailure(Call<EnterpriseDetailResponse> call, Throwable t) {
                showMessage(t.getMessage());
            }
        };
    }


}
