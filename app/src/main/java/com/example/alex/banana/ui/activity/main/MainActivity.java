package com.example.alex.banana.ui.activity.main;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;
import com.example.alex.banana.R;
import com.example.alex.banana.databinding.ActivityMainBinding;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity
        implements MainContract.View, AHBottomNavigation.OnTabSelectedListener, ViewPager.OnPageChangeListener {
    private static final int SIGN_IN_REQUEST_CODE = 100;
    private ActivityMainBinding mBinding;
    private View mDecorView;
    private AHBottomNavigationAdapter navigationAdapter;
    private MainPresenter mPresenter;
    private int[] tabColors;
    private boolean tapOnBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Убераем панель уведомлений
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        tapOnBar = true;

        tabColors = getApplicationContext().getResources().getIntArray(R.array.tab_colors);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //Создаем объект Presenter
        mPresenter = new MainPresenter(this, mBinding);
        mPresenter.setUpViewPager(mBinding.pager);

        mBinding.pager.addOnPageChangeListener(this);
        mBinding.pager.setOffscreenPageLimit(5);

        mDecorView = getWindow().getDecorView();

        navigationAdapter = new AHBottomNavigationAdapter(this, R.menu.menu_bottom_navigation);
        navigationAdapter.setupWithBottomNavigation(mBinding.bottomNavigation, tabColors);

        mBinding.bottomNavigation.setNotification("1", 2);

        mBinding.bottomNavigation.setOnTabSelectedListener(this);

        mBinding.bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_HIDE);
        mBinding.bottomNavigation.setTranslucentNavigationEnabled(true);
        mBinding.bottomNavigation.setColored(true);
        mBinding.bottomNavigation.setAccentColor(getResources().getColor(R.color.orange_special));
        mBinding.bottomNavigation.setInactiveColor(getResources().getColor(R.color.white));
        mBinding.bottomNavigation.setSelectedBackgroundVisible(true);

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            // Start sign in/sign up activity
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .build(),
                    SIGN_IN_REQUEST_CODE
            );
        } else {
            // User is already signed in. Therefore, display
            // a welcome Toast
            Toast.makeText(this,
                    "Welcome " + FirebaseAuth.getInstance()
                            .getCurrentUser()
                            .getDisplayName(),
                    Toast.LENGTH_LONG)
                    .show();

            // Load chat room contents
            displayChatMessages();
        }

    }

    private void displayChatMessages() {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGN_IN_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this,
                        "Successfully signed in. Welcome!",
                        Toast.LENGTH_LONG)
                        .show();
                displayChatMessages();
            } else {
                Toast.makeText(this,
                        "We couldn't sign you in. Please try again later.",
                        Toast.LENGTH_LONG)
                        .show();

                // Close the app
                finish();
            }
        }

    }

    //Скрывает системные панели
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            mDecorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    public Context getContext() {
        return this;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        Log.e("onPageSelected: ", String.valueOf(position));
        mBinding.bottomNavigation.setCurrentItem(position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onTabSelected(int position, boolean wasSelected) {
        Log.e("onTabSelected: ", String.valueOf(position));
        tapOnBar = false;
        mBinding.pager.setCurrentItem(position, true);
        if (position == 2) {
            mBinding.bottomNavigation.setNotification("", 2);
        } else {
            mBinding.bottomNavigation.setNotification("1", 2);
        }
        return true;
    }

}
