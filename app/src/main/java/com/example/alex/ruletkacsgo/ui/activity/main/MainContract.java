package com.example.alex.ruletkacsgo.ui.activity.main;

import android.content.Context;

/**
 * Created by alex on 16.12.17.
 */

public interface MainContract {
    interface View {
        Context getContext();
    }

    interface EventListener {
        void clickNavigation(int id);
    }
}
