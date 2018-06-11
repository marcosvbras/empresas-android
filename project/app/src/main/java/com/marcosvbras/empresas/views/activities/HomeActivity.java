package com.marcosvbras.empresas.views.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.genius.groupie.GroupAdapter;
import com.marcosvbras.empresas.HomeViewModelCallback;
import com.marcosvbras.empresas.R;
import com.marcosvbras.empresas.databinding.ActivityHomeBinding;
import com.marcosvbras.empresas.models.api.UserModel;
import com.marcosvbras.empresas.viewmodels.HomeViewModel;
import com.marcosvbras.empresas.views.adapters.EnterpriseAdapter;
import com.marcosvbras.empresas.listeners.RecyclerViewTouchConfig;
import com.marcosvbras.empresas.listeners.OnRecyclerViewTouchListener;
import com.marcosvbras.empresas.models.domain.Enterprise;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity implements HomeViewModelCallback, OnRecyclerViewTouchListener {

    private SearchView searchView;
    private RecyclerView recyclerView;
    private List<Enterprise> originalListEnterprise;
    private EnterpriseAdapter enterpriseAdapter;
    private boolean firstCall = true;
    private ActivityHomeBinding activityHomeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HomeViewModel homeViewModel = new HomeViewModel(this);
        activityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        activityHomeBinding.setViewModel(homeViewModel);
        activityHomeBinding.executePendingBindings();
        originalListEnterprise = new ArrayList<>();
        config();
    }

    @Override
    protected void onResume() {
        super.onResume();
        activityHomeBinding.getViewModel().requestEnterprises(null);
    }

    private void config() {
        setSupportActionBar(findViewById(R.id.top_toolbar));
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.addOnItemTouchListener(new RecyclerViewTouchConfig(getBaseContext(), recyclerView, this));
        enterpriseAdapter = new EnterpriseAdapter(originalListEnterprise);
        recyclerView.setAdapter(enterpriseAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        // Setting search view
        MenuItem menuItemSearch = menu.findItem(R.id.search);
        searchView = (SearchView) menuItemSearch.getActionView();
        searchView.setOnQueryTextListener(onSearchListener());
        searchView.setOnCloseListener(() -> {
            onSearchResponse(originalListEnterprise);
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
                activityHomeBinding.getViewModel().requestEnterprises(newText);
                return true;
            }
        };
    }

    @Override
    public void onInvalidAuthentication() {
        UserModel.deleteCredentials();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onSearchResponse(List<Enterprise> list) {
        if (firstCall && list != null) {
            originalListEnterprise = list;
            firstCall = false;
        }

        enterpriseAdapter.updateItems(list);
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

