package com.example.alex.ruletkacsgo.client;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by alex on 16.12.17.
 */

class RetrofitClient {

    private static Retrofit retrofit;

    /**
     * @method readTimeout/writeTimeout is optional by server rules
     *         readTimeout/writeTimeout не обязательны для настройки - зависит от правил сервера
     * @return global Singleton class for client-server requests
     */
    private static Retrofit getRetrofitInstance() {
//        final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        final OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
//                .addInterceptor(loggingInterceptor) //TODO remove inspector in realise
                .build();

        if (retrofit == null)
            retrofit = new Retrofit.Builder()
                    .baseUrl("#").client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        return retrofit;
    }

    static RulesPullAPI getAPI() {
        return getRetrofitInstance().create(RulesPullAPI.class);
    }
}
