package com.example.alex.ruletkacsgo.ui.activity.settings;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.alex.ruletkacsgo.R;
import com.example.alex.ruletkacsgo.databinding.ActivitySettingsBinding;

public class SettingsActivity extends AppCompatActivity {

    private ActivitySettingsBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_settings);

        mBinding.toolbarSettings.setNavigationOnClickListener(v -> onBackPressed());

    }
}
