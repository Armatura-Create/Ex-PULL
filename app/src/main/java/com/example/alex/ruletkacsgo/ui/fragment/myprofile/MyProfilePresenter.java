package com.example.alex.ruletkacsgo.ui.fragment.myprofile;

import android.app.Fragment;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.alex.ruletkacsgo.ui.activity.settings.SettingsActivity;

/**
 * Created by arthur on 01.02.18.
 */

public class MyProfilePresenter extends Fragment implements MyProfileContract {
    @Override
    public void onButtonClick(Button button) {
        button.setOnClickListener(view -> startActivity(new Intent(getActivity(), SettingsActivity.class)));
    }
}
