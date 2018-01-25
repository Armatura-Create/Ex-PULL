package com.example.alex.ruletkacsgo.ui.activity.main;

import android.support.v4.view.ViewPager;

/**
 * Created by alex on 25.01.18.
 */

public interface IFragmentListener extends ViewPager.OnPageChangeListener {
    void replaceFragment(String name);
}