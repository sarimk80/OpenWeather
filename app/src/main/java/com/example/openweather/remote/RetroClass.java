package com.example.openweather.remote;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClass {

    private static final String BASE_URL = "https://samples.openweathermap.org/data/2.5/";

    private static Retrofit getRetroInstance() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static APIService getApiService() {
        return getRetroInstance().create(APIService.class);
    }
}
