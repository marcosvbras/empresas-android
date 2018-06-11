package com.marcosvbras.empresas.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.marcosvbras.empresas.EnterpriseApplication;
import com.marcosvbras.empresas.R;
import com.marcosvbras.empresas.models.api.UserModel;
import com.marcosvbras.empresas.presenters.HomePresenter;
import com.marcosvbras.empresas.views.adapters.EnterpriseAdapter;
import com.marcosvbras.empresas.listeners.RecyclerViewTouchConfig;
import com.marcosvbras.empresas.listeners.OnRecyclerViewTouchListener;
import com.marcosvbras.empresas.models.domain.Enterprise;
import com.marcosvbras.empresas.views.interfaces.HomeView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends MvpAppCompatActivity implements HomeView, OnRecyclerViewTouchListener {

    @InjectPresenter
    HomePresenter homePresenter;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private List<Enterprise> originalListEnterprise;
    private LinearLayoutManager linearLayoutManager;
    private EnterpriseAdapter enterpriseAdapter;
    private ProgressBar progressBar;
    private MenuItem menuItemSearch;
    private TextView textViewNotFound;
    private AlertDialog.Builder alertDialog;
    private boolean firstCall = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        originalListEnterprise = new ArrayList<>();
        bindViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        homePresenter.requestEnterprises(null);
    }

    @Override
    public void onInvalidAuthentication() {
        UserModel.deleteCredentials(this);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void bindViews() {
        setSupportActionBar(findViewById(R.id.top_toolbar));
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        textViewNotFound = findViewById(R.id.text_view_not_found);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addOnItemTouchListener(new RecyclerViewTouchConfig(getBaseContext(), recyclerView, this));
        alertDialog = new AlertDialog.Builder(this);
        enterpriseAdapter = new EnterpriseAdapter(originalListEnterprise, this);
        recyclerView.setAdapter(enterpriseAdapter);
    }

    @Override
    public void updateRecyclerView(List<Enterprise> list) {
        if (firstCall && list != null) {
            originalListEnterprise = list;
            firstCall = false;
        }

        enterpriseAdapter.updateItems(list);
        textViewNotFound.setVisibility(list.size() == 0 ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showErrorDialog(String message) {
        alertDialog
                .setMessage(message)
                .setPositiveButton(getString(R.string.ok), null)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        // Setting search view
        menuItemSearch = menu.findItem(R.id.search);
        searchView = (SearchView) menuItemSearch.getActionView();
        searchView.setOnQueryTextListener(onSearchListener());
        searchView.setOnCloseListener(() -> {
            updateRecyclerView(originalListEnterprise);
            return false;
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_logout:
                onInvalidAuthentication();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private SearchView.OnQueryTextListener onSearchListener() {
        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                homePresenter.requestEnterprises(newText);
                return true;
            }
        };
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(View view, int position) {
        Enterprise enterprise = enterpriseAdapter.getCurrentList().get(position);
        Intent intent = new Intent(this, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("id", enterprise.getId());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onLongItemClick(View view, int position) {}
}

