package com.example.alex.ruletkacsgo.ui.activity.main;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.alex.ruletkacsgo.R;
import com.example.alex.ruletkacsgo.databinding.ActivityMainBinding;
import com.example.alex.ruletkacsgo.ui.fragment.chat.ChatFragment;
import com.example.alex.ruletkacsgo.ui.fragment.crash.CrashFragment;
import com.example.alex.ruletkacsgo.ui.fragment.myprofile.MyProfileFragment;
import com.example.alex.ruletkacsgo.ui.fragment.roullet.RouletteFragment;
import com.example.alex.ruletkacsgo.ui.fragment.shop.ShopFragment;
import com.example.alex.ruletkacsgo.utils.FragmentsAdapter;

import java.util.HashMap;

import static com.example.alex.ruletkacsgo.utils.StaticValues.FRAGMENT_CHAT;
import static com.example.alex.ruletkacsgo.utils.StaticValues.FRAGMENT_CRASH;
import static com.example.alex.ruletkacsgo.utils.StaticValues.FRAGMENT_PROFILE;
import static com.example.alex.ruletkacsgo.utils.StaticValues.FRAGMENT_ROULETTE;
import static com.example.alex.ruletkacsgo.utils.StaticValues.FRAGMENT_SHOP;

public class MainPresenter implements MainContract.EventListener {
    private MainContract.View mView;
    private ActivityMainBinding mBinding;

    private Fragment crashFragment;
    private Fragment profileFragment;
    private Fragment chatFragment;
    private Fragment rouletteFragment;
    private Fragment shopFragment;

    private HashMap<String, Fragment> frMap;

    MainPresenter(MainContract.View view, ActivityMainBinding binding) {
        mView = view;
        mBinding = binding;
    }


    @Override
    public void setUpViewPager(ViewPager pager) {
        if (mView.getContext() instanceof AppCompatActivity) {
            AppCompatActivity activity = (AppCompatActivity) mView.getContext();
            FragmentsAdapter adapter = new FragmentsAdapter(activity.getSupportFragmentManager());
            crashFragment = CrashFragment.newInstance();
            rouletteFragment = RouletteFragment.newInstance();
            chatFragment = ChatFragment.newInstance();
            shopFragment = ShopFragment.newInstance();
            profileFragment = MyProfileFragment.newInstance();

            setUpFragments();

            adapter.addFragment(frMap.get(FRAGMENT_CRASH));
            adapter.addFragment(frMap.get(FRAGMENT_ROULETTE));
            adapter.addFragment(frMap.get(FRAGMENT_CHAT));
            adapter.addFragment(frMap.get(FRAGMENT_SHOP));
            adapter.addFragment(frMap.get(FRAGMENT_PROFILE));

            pager.setAdapter(adapter);
        }
    }

    public HashMap<String, Fragment> getFragmentsMap() {
        return frMap;
    }


    private void setUpFragments() {
        frMap = new HashMap<>();
        frMap.put(FRAGMENT_CRASH, crashFragment);
        frMap.put(FRAGMENT_ROULETTE, rouletteFragment);
        frMap.put(FRAGMENT_CHAT, chatFragment);
        frMap.put(FRAGMENT_SHOP, shopFragment);
        frMap.put(FRAGMENT_PROFILE, profileFragment);
    }
}
