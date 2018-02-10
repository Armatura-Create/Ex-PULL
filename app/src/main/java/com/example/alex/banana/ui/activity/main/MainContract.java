package com.example.alex.banana.ui.activity.main;

import android.content.Context;
import android.support.v4.view.ViewPager;

/**
 * Created by alex on 16.12.17.
 */

public interface MainContract {
    interface View {
        Context getContext();
    }

    interface EventListener {
        void setUpViewPager(ViewPager pager);
    }
}
