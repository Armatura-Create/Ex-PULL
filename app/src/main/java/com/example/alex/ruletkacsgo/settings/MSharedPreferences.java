package com.example.alex.ruletkacsgo.settings;

import android.content.SharedPreferences;

/**
 * Created by alex on 16.12.17.
 */

public class MSharedPreferences {

    private static final String USER_INFO = "user_info";

    private static MSharedPreferences loader;
    private SharedPreferences sharedPref;

//    private MSharedPreferences() {
//        sharedPref = MApplication.getInstance().getApplicationContext().getSharedPreferences(SHARED_PACKAGE, Context.MODE_PRIVATE);
//    }

    public static MSharedPreferences getInstance() {
        if (loader == null) loader = new MSharedPreferences();
        return loader;
    }


    public void setUserInfo(String userInfo) {
        sharedPref.edit().putString(USER_INFO, userInfo).apply();
    }

    public String getUserInfo() {
        return sharedPref.getString(USER_INFO, null);
    }

}
