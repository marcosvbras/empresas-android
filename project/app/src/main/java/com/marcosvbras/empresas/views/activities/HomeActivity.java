package com.marcosvbras.empresas.views.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.marcosvbras.empresas.R;
import com.marcosvbras.empresas.databinding.ActivityHomeBinding;
import com.marcosvbras.empresas.viewmodels.HomeViewModel;

public class HomeActivity extends BaseActivity {

    private SearchView searchView;
    private ActivityHomeBinding activityHomeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HomeViewModel homeViewModel = new HomeViewModel(this);
        activityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        activityHomeBinding.setViewModel(homeViewModel);
        activityHomeBinding.executePendingBindings();
        setToolbar(R.id.top_toolbar,null, false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        activityHomeBinding.getViewModel().requestEnterprises(null);
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
                EnterpriseApp.getInstance().deleteCredentials();
                openActivity(LoginActivity.class, true);
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

}

