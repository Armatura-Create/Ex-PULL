package com.example.alex.ruletkacsgo.ui.activity.main;

import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;

import com.example.alex.ruletkacsgo.R;
import com.example.alex.ruletkacsgo.databinding.ActivityMainBinding;

public class MainPresenter implements MainContract.EventListener {
    private MainContract.View mView;
    private ActivityMainBinding mBinding;

    MainPresenter(MainContract.View view, ActivityMainBinding binding) {
        mView = view;
        mBinding = binding;
    }

    @Override
    public void clickNavigation(int id) {
        switch (id) {
            case R.id.action_map:
                Snackbar.make(mBinding.bottomNavigation, "Map", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.action_mail:
                Snackbar.make(mBinding.bottomNavigation, "Mail", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.action_dial:
                Snackbar.make(mBinding.bottomNavigation, "Dial", Snackbar.LENGTH_SHORT).show();
                break;


        }

    }
}
