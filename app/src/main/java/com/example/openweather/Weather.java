package com.example.openweather;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.openweather.databinding.FragmentWeatherBinding;
import com.example.openweather.model.WeatherModel;
import com.example.openweather.remote.APIService;
import com.example.openweather.remote.RetroClass;
import com.tbruyelle.rxpermissions2.RxPermissions;


import java.util.Objects;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * A simple {@link Fragment} subclass.
 */
public class Weather extends Fragment {

    private Disposable disposable;
    private FragmentWeatherBinding weatherBinding;
    private RxPermissions rxPermissions;

    public Weather() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        weatherBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather, container, false);

        rxPermissions = new RxPermissions(Weather.this);

        return weatherBinding.getRoot();
    }

    @SuppressLint("CheckResult")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rxPermissions
                .request(Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe(granted -> {
                    if (granted) {
                        getData();
                        Log.d("Main", "Permission is granted");
                    } else {
                        Log.d("Main", "Permission is not granted");
                    }
                });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }

    private void getData() {

        weatherBinding.progress.setVisibility(View.VISIBLE);

        //Getting the Location Start
        LocationManager locationManager = (LocationManager) Objects.requireNonNull(getActivity()).getSystemService(Context.LOCATION_SERVICE);

        @SuppressLint("MissingPermission") Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        String Latitude = String.valueOf(location.getLatitude());
        String Longitude = String.valueOf(location.getLongitude());

        //Getting the Location End

        //Getting the data from Retrofit
        APIService apiService = RetroClass.getApiService();

        Single<WeatherModel> modelSingle = apiService.getWeatherModel(Latitude, Longitude, "b6907d289e10d714a6e88b30761fae22")
                .subscribeOn(Schedulers.io())//Will run on background
                .observeOn(AndroidSchedulers.mainThread());//Will run on main thread

        modelSingle.subscribe(new SingleObserver<WeatherModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onSuccess(WeatherModel weatherModel) {
//                Log.d("Weather", String.valueOf(weatherModel.getMain().getTemp()));
                weatherBinding.setWeather(weatherModel);
                Glide.with(Objects.requireNonNull(getActivity())).load("http://openweathermap.org/img/w/" + weatherModel.getWeather().get(0).getIcon() + ".png").into(weatherBinding.image);

                weatherBinding.progress.setVisibility(View.GONE);
            }

            @Override
            public void onError(Throwable e) {
                weatherBinding.progress.setVisibility(View.GONE);
            }
        });
        //Getting the data from Retrofit
    }
}
