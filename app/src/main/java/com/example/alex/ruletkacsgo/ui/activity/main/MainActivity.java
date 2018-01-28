package com.example.alex.ruletkacsgo.ui.activity.main;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.alex.ruletkacsgo.R;
import com.example.alex.ruletkacsgo.databinding.ActivityMainBinding;
import com.example.alex.ruletkacsgo.ui.activity.settings.SettingsActivity;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity
        implements MainContract.View, AHBottomNavigation.OnTabSelectedListener, ViewPager.OnPageChangeListener {
    private static final int SIGN_IN_REQUEST_CODE = 100;
    private ActivityMainBinding mBinding;
    private View mDecorView;
    private MainPresenter mPresenter;
    private AHBottomNavigationItem prevMenuItem;

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

        mDecorView = getWindow().getDecorView();

        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.crash, R.drawable.ic_menu_camera, R.color.colorAccent);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.roulette, R.drawable.ic_menu_camera, R.color.colorAccent);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.chat, R.drawable.ic_menu_camera, R.color.colorAccent);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.shop, R.drawable.ic_menu_camera, R.color.colorAccent);
        AHBottomNavigationItem item5 = new AHBottomNavigationItem(R.string.profile, R.drawable.ic_menu_camera, R.color.colorAccent);

        mBinding.bottomNavigation.addItem(item1);
        mBinding.bottomNavigation.addItem(item2);
        mBinding.bottomNavigation.addItem(item3);
        mBinding.bottomNavigation.addItem(item4);
        mBinding.bottomNavigation.addItem(item5);

        mBinding.bottomNavigation.setNotification("1", 2);

        mBinding.bottomNavigation.setOnTabSelectedListener(this);

        mBinding.bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_HIDE);

        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
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
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SIGN_IN_REQUEST_CODE) {
            if(resultCode == RESULT_OK) {
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
        switch (position) {
            case 0: {
                mBinding.pager.setCurrentItem(0);
                break;
            }
            case 1: {
                mBinding.pager.setCurrentItem(1);
                break;
            }

            case 2: {
                mBinding.pager.setCurrentItem(2);
                mBinding.bottomNavigation.setNotification("", 2);
                break;
            }
            case 3: {
                mBinding.pager.setCurrentItem(3);
                break;
            }
            case 4: {
                mBinding.pager.setCurrentItem(4);
                mBinding.bottomNavigation.setNotification("1", 2);
                break;
            }
        }
        return true;
    }
}
