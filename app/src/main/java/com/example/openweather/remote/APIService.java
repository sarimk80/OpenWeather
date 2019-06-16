package com.example.openweather.remote;

import com.example.openweather.model.WeatherModel;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET("weather")
    Single<WeatherModel> getWeatherModel(@Query("lat") String lat, @Query("lon") String lon, @Query("appid") String appid);
}
