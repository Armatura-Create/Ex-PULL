package com.example.alex.banana.ui.fragment.myprofile;

/**
 * Created by arthur on 01.02.18.
 */

public class MyProfilePresenter implements MyProfileContract.EventListener {
    private MyProfileContract.View mView;

    public MyProfilePresenter(MyProfileFragment myProfileFragment) {
        mView = myProfileFragment;
    }
}
