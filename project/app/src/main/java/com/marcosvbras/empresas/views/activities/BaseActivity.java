package com.marcosvbras.empresas.views.activities;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.marcosvbras.empresas.R;

public class BaseActivity extends AppCompatActivity {

    private AlertDialog.Builder alertDialog;

    protected void showErrorDialog(String message) {
        if (alertDialog == null)
            alertDialog = new AlertDialog.Builder(this);

        alertDialog
                .setMessage(message)
                .setPositiveButton(getString(R.string.ok), null)
                .show();
    }

}
