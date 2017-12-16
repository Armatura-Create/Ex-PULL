package com.example.alex.ruletkacsgo.client;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alex on 16.12.17.
 */

public class Request {
    private static Request request;

    public static Request getInstance() {
        if (request == null) request = new Request();
        return request;
    }

//    public void updateUserInfo(UpdateUserInfoModel user, final ILoadingStatus<String> loader) {
//        Call<UserInfoModel> call;
//        String token = MSharedPreferences.getInstance().getKey();
//        call = RetrofitClient.getAPI().updateUser(token, user);
//        call.enqueue(new Callback<UserInfoModel>() {
//            @Override
//            public void onResponse(Call<UserInfoModel> call, Response<UserInfoModel> response) {
//                if (response.isSuccessful()) {
//                    loader.onSuccess("Body: " + response.body().toString());
//                } else {
//                    loader.onFailure("Code: " + response.code()
//                            + "\nMessage" + response.message());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<UserInfoModel> call, Throwable t) {
//                loader.onFailure(CONNECTION_ERROR);
//            }
//        });
//    }
}
