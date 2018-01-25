package com.example.alex.ruletkacsgo.ui.activity.main;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.alex.ruletkacsgo.R;
import com.example.alex.ruletkacsgo.databinding.ActivityMainBinding;
import com.example.alex.ruletkacsgo.ui.activity.settings.SettingsActivity;
import com.example.alex.ruletkacsgo.utils.StaticValues;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainContract.View, BottomNavigationView.OnNavigationItemSelectedListener, IFragmentListener {
    private ActivityMainBinding mBinding;
    private View mDecorView;
    private MainPresenter mPresenter;
    private MenuItem prevMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Убераем панель уведомлений
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //Создаем объект Presenter
        mPresenter = new MainPresenter(this, mBinding);
        mPresenter.setUpViewPager(mBinding.pager);

        mBinding.pager.addOnPageChangeListener(this);
        mBinding.pager.setOffscreenPageLimit(5);
        mBinding.bottomNavigation.setOnNavigationItemSelectedListener(listener);

        mDecorView = getWindow().getDecorView();

        mBinding.bottomNavigation.setOnNavigationItemSelectedListener(this);

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

    /**
     * Called when pointer capture is enabled or disabled for the current window.
     *
     * @param hasCapture True if the window has pointer capture.
     */
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    @Override
    public void onBackPressed() {
            super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            case R.id.action_logout:
                Toast.makeText(this, "Log Out", Toast.LENGTH_SHORT).show();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        mPresenter.clickNavigation(id);
        return true;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void replaceFragment(String name) {
        switch (name) {
            case StaticValues.FRAGMENT_CRASH: {
                mBinding.pager.setCurrentItem(0);
                break;
            }
            case StaticValues.FRAGMENT_ROULETTE: {
                mBinding.pager.setCurrentItem(1);
                break;
            }
            case StaticValues.FRAGMENT_CHAT: {
                mBinding.pager.setCurrentItem(2);
                break;
            }
            case StaticValues.FRAGMENT_SHOP: {
                mBinding.pager.setCurrentItem(3);
                break;
            }
            case StaticValues.FRAGMENT_PROFILE: {
                mBinding.pager.setCurrentItem(4);
                break;
            }
        }
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener listener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.crash: {
                    mBinding.pager.setCurrentItem(0);
                    break;
                }
                case R.id.roulette: {
                    mBinding.pager.setCurrentItem(1);
                    break;
                }

                case R.id.chat: {
                    mBinding.pager.setCurrentItem(2);
                    break;
                }
                case R.id.shop: {
                    mBinding.pager.setCurrentItem(3);
                    break;
                }
                case R.id.profile: {
                    mBinding.pager.setCurrentItem(4);
                    break;
                }
            }
            return true;
        }

    };
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (prevMenuItem != null) prevMenuItem.setChecked(false);
        else mBinding.bottomNavigation.getMenu().getItem(0).setChecked(false);
        mBinding.bottomNavigation.getMenu().getItem(position).setChecked(true);
        prevMenuItem = mBinding.bottomNavigation.getMenu().getItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
