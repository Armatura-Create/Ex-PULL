package com.example.alex.ruletkacsgo.ui.fragment.myprofile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.alex.ruletkacsgo.R;
import com.example.alex.ruletkacsgo.ui.activity.settings.SettingsActivity;

/**
 * Created by alex on 25.01.18.
 */

public class MyProfileFragment extends android.support.v4.app.Fragment implements MyProfileContract.View {

    private Button button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile, container, false);

        button = view.findViewById(R.id.btn_settings);


        button.setOnClickListener(view1 -> {
            startActivity(new Intent(getActivity(), SettingsActivity.class));
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public static MyProfileFragment newInstance() {
        return new MyProfileFragment();
    }


}

