package com.marcosvbras.empresas.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.marcosvbras.empresas.R;
import com.marcosvbras.empresas.views.listeners.BaseViewModelCallback;

public class BaseActivity extends AppCompatActivity implements BaseViewModelCallback {

    private AlertDialog.Builder alertDialog;

    @Override
    public void showErrorDialog(String message) {
        showDialog(message);
    }

    @Override
    public void showErrorDialog(int message) {
        showDialog(getString(message));
    }

    private void showDialog(String message) {
        if (alertDialog == null)
            alertDialog = new AlertDialog.Builder(this);

        alertDialog
                .setMessage(message)
                .setPositiveButton(getString(R.string.ok), null)
                .show();
    }

    @Override
    public void openActivity(Class<?> activity, boolean finishCurrentActivity) {
        startActivity(new Intent(this, activity));

        if(finishCurrentActivity)
            finish();
    }

    @Override
    public void openActivity(Class<?> activity, Bundle bundle, boolean finishCurrentActivity) {
        Intent intent = new Intent(this, activity);

        if(bundle != null)
            intent.putExtras(bundle);

        startActivity(intent);

        if(finishCurrentActivity)
            finish();
    }

    @Override
    public void setToolbar(int viewId, String title, boolean displayHomeAsUpEnabled) {
        setSupportActionBar(findViewById(viewId));

        if(title != null)
            getSupportActionBar().setTitle(title);

        if(displayHomeAsUpEnabled)
            getSupportActionBar().setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled);
    }

    @Override
    public void setToolbarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}
