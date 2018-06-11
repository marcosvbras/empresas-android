package com.marcosvbras.empresas.views.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.marcosvbras.empresas.views.listeners.BaseViewModelCallback;
import com.marcosvbras.empresas.R;
import com.marcosvbras.empresas.databinding.ActivityHomeBinding;
import com.marcosvbras.empresas.models.api.UserModel;
import com.marcosvbras.empresas.viewmodels.HomeViewModel;
import com.marcosvbras.empresas.views.adapters.EnterpriseAdapter;
import com.marcosvbras.empresas.views.listeners.RecyclerViewTouchConfig;
import com.marcosvbras.empresas.views.listeners.OnRecyclerViewTouchListener;
import com.marcosvbras.empresas.models.domain.Enterprise;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity implements OnRecyclerViewTouchListener, BaseViewModelCallback {

    private SearchView searchView;
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
//        recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.addOnItemTouchListener(new RecyclerViewTouchConfig(getBaseContext(), recyclerView, this));
        enterpriseAdapter = new EnterpriseAdapter(originalListEnterprise);
//        recyclerView.setAdapter(enterpriseAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        // Setting search view
        MenuItem menuItemSearch = menu.findItem(R.id.search);
        searchView = (SearchView) menuItemSearch.getActionView();
        searchView.setOnQueryTextListener(onSearchListener());
        searchView.setOnCloseListener(() -> {
            activityHomeBinding.getViewModel().requestEnterprises(null);
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
    public void showErrorDialog(String message) {
        showErrorDialog(message);
    }

    @Override
    public void showErrorDialog(int message) {
        showErrorDialog(getString(message));
    }

    @Override
    public void onItemClick(View view, int position) {
        Enterprise enterprise = enterpriseAdapter.getCurrentList().get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("id", enterprise.getId());
        startNewActivity(DetailActivity.class, bundle, false);
    }

    @Override
    public void onLongItemClick(View view, int position) {}
}

