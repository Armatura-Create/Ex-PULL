package com.example.alex.ruletkacsgo.ui.activity.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
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
import com.ogaclejapan.smarttablayout.SmartTabLayout;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ActivityMainBinding mBinding;
    private SmartTabLayout mTabLayout;
    private View mDecorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Убераем панель уведомлений
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mDecorView = getWindow().getDecorView();
        mTabLayout = findViewById(R.id.tab_main);

        mBinding.navLeft.setNavigationItemSelectedListener(this);

        //set viewpager adapter
        mBinding.viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        mTabLayout.setViewPager(mBinding.viewPager);
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
    public void onBackPressed() {
        if (mBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            mBinding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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

        switch (id) {
            case R.id.nav_shop:
                Snackbar.make(mBinding.drawerLayout, "Shop", Snackbar.LENGTH_SHORT).show();
                break;

            case R.id.nav_deposit:
                Snackbar.make(mBinding.drawerLayout, "Deposit", Snackbar.LENGTH_SHORT).show();
                break;

            case R.id.nav_referrals:
                Snackbar.make(mBinding.drawerLayout, "Referrals", Snackbar.LENGTH_SHORT).show();
                break;

            case R.id.nav_top:
                Snackbar.make(mBinding.drawerLayout, "Top", Snackbar.LENGTH_SHORT).show();
                break;

            case R.id.nav_crash:
                mBinding.viewPager.setCurrentItem(0);
                Snackbar.make(mBinding.drawerLayout, "Crash", Snackbar.LENGTH_SHORT).show();
                break;

            case R.id.nav_roulette:
                mBinding.viewPager.setCurrentItem(1);
                Snackbar.make(mBinding.drawerLayout, "Roulette", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.nav_chat:
                mBinding.viewPager.setCurrentItem(2);
                Snackbar.make(mBinding.drawerLayout, "Roulette", Snackbar.LENGTH_SHORT).show();
                break;
        }

        mBinding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
