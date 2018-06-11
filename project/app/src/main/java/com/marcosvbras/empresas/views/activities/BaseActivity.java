package com.marcosvbras.empresas.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.marcosvbras.empresas.R;

public class BaseActivity extends AppCompatActivity {

    private AlertDialog.Builder alertDialog;

    protected void showErrorDialog(String message) {
        showDialog(message);
    }

    protected void showErrorDialog(int message) {
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

    public void startNewActivity(Class<?> activity, Bundle bundle, boolean finish) {
        Intent intent = new Intent(this, activity);
        intent.putExtras(bundle);

        if(finish)
            finish();
    }

}
