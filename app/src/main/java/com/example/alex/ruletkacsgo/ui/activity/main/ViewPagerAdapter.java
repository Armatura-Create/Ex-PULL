package com.example.alex.ruletkacsgo.ui.activity.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.alex.ruletkacsgo.ui.fragment.chat.ChatFragment;
import com.example.alex.ruletkacsgo.ui.fragment.crash.CrashFragment;
import com.example.alex.ruletkacsgo.ui.fragment.roullet.RoulletGameFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[]{"Roulette", "Crash", "Chat"};

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new CrashFragment();
        } else if (position == 1) {
            return new RoulletGameFragment();
        } else return new ChatFragment();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
