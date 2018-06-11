package com.marcosvbras.empresas.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.marcosvbras.empresas.R;
import com.marcosvbras.empresas.retrofit.EnterpriseResponse;
import com.marcosvbras.empresas.retrofit.RetrofitConfig;
import com.marcosvbras.empresas.adapters.EnterpriseAdapter;
import com.marcosvbras.empresas.listeners.RecyclerTouchListener;
import com.marcosvbras.empresas.listeners.RecyclerViewTouchListener;
import com.marcosvbras.empresas.models.EnterpriseModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements RecyclerViewTouchListener {

    private SearchView searchView;
    private RecyclerView recyclerView;
    private Call<EnterpriseResponse> currentCall;
    private List<EnterpriseModel> originalListEnterprise;
    private List<EnterpriseModel> filteredListEnterprise;
    private LinearLayoutManager linearLayoutManager;
    private EnterpriseAdapter enterpriseAdapter;
    private ProgressBar progressBar;
    private MenuItem menuItemSearch;
    private TextView textViewNotFound;
    private AlertDialog.Builder alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        checkAuth();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bindViews();
        originalListEnterprise = new ArrayList<>();
        filteredListEnterprise = new ArrayList<>();
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestEnterpriseList();
    }

    private void checkAuth() {
        SharedPreferences sharedPreferences = getSharedPreferences(
                getString(R.string.auth_pref),
                Context.MODE_PRIVATE);
        String accessToken = sharedPreferences.getString(getString(R.string.access_token_key), null);
        String client = sharedPreferences.getString(getString(R.string.client_key), null);
        String uid = sharedPreferences.getString(getString(R.string.uid_key), null);

        if(accessToken == null || client == null && uid == null)
            goToLogin();
    }

    private void goToLogin() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void bindViews() {
        setSupportActionBar((Toolbar)findViewById(R.id.top_toolbar));
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        textViewNotFound = findViewById(R.id.text_view_not_found);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getBaseContext(), recyclerView, this));
        alertDialog = new AlertDialog.Builder(this);
    }

    private void updateRecyclerView(List<EnterpriseModel> list) {
        enterpriseAdapter.updateItems(list);

        if(list.size() == 0)
            textViewNotFound.setVisibility(View.VISIBLE);
        else
            textViewNotFound.setVisibility(View.GONE);
    }

    private void requestEnterpriseList() {
        if(originalListEnterprise.size() == 0) {
            enterpriseAdapter = new EnterpriseAdapter(originalListEnterprise, this);
            recyclerView.setAdapter(enterpriseAdapter);
            progressBar.setVisibility(View.VISIBLE);
            currentCall = new RetrofitConfig(this).getEnterpriseService().getAllEnterprises();
            currentCall.enqueue(firstListCallback());
        }
    }

    private Callback<EnterpriseResponse> firstListCallback() {
        return new Callback<EnterpriseResponse>() {
            @Override
            public void onResponse(Call<EnterpriseResponse> call, Response<EnterpriseResponse> response) {
                progressBar.setVisibility(View.GONE);

                if(response.isSuccessful() && response.body() != null) {
                    if(response.body().getListEnterprises() != null && response.body().getListEnterprises().size() > 0) {
                        originalListEnterprise = response.body().getListEnterprises();
                        updateRecyclerView(originalListEnterprise);
                    }
                } else {
                    showMessage(response.message());
                }
            }

            @Override
            public void onFailure(Call<EnterpriseResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                showMessage(t.getMessage());
            }
        };
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        // Setting search view
        menuItemSearch = menu.findItem(R.id.search);
        searchView = (SearchView) menuItemSearch.getActionView();
        searchView.setOnQueryTextListener(onSearchListener());
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                updateRecyclerView(originalListEnterprise);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_logout:
                logout();
                goToLogin();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        SharedPreferences sharedPreferences = getSharedPreferences(
                getString(R.string.auth_pref),
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.access_token_key), null);
        editor.putString(getString(R.string.client_key), null);
        editor.putString(getString(R.string.uid_key), null);
        editor.commit();
    }

    private SearchView.OnQueryTextListener onSearchListener() {
        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressBar.setVisibility(View.VISIBLE);
                currentCall = new RetrofitConfig(getBaseContext()).getEnterpriseService().getEnterprises(query);
                currentCall.enqueue(enterpriseListCallback());
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        };
    }

    private Callback<EnterpriseResponse> enterpriseListCallback() {
        return new Callback<EnterpriseResponse>() {
            @Override
            public void onResponse(Call<EnterpriseResponse> call, Response<EnterpriseResponse> response) {
                progressBar.setVisibility(View.GONE);

                if(response.isSuccessful() && response.body() != null) {
                    if(response.body().getListEnterprises() != null && response.body().getListEnterprises().size() > 0)
                        filteredListEnterprise = response.body().getListEnterprises();
                    else
                        filteredListEnterprise.clear();

                    updateRecyclerView(filteredListEnterprise);

                } else {
                    showMessage(response.message());
                }
            }

            @Override
            public void onFailure(Call<EnterpriseResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                showMessage(t.getMessage());
            }
        };
    }

    @Override
    public void onItemClick(View view, int position) {
        EnterpriseModel enterprise = enterpriseAdapter.getCurrentList().get(position);
        Intent intent = new Intent(this, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("id", enterprise.getId());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onLongItemClick(View view, int position) {}
}

