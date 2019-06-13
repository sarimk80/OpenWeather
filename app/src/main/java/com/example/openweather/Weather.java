package com.example.openweather;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.AndroidException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.openweather.databinding.FragmentWeatherBinding;
import com.example.openweather.model.WeatherModel;
import com.example.openweather.remote.APIService;
import com.example.openweather.remote.RetroClass;

import io.reactivex.Scheduler;
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

    public Weather() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        weatherBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather, container, false);

        return weatherBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        APIService apiService = RetroClass.getApiService();

        Single<WeatherModel> modelSingle = apiService.getWeatherModel("London,uk", "b6907d289e10d714a6e88b30761fae22")
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread());

        modelSingle.subscribe(new SingleObserver<WeatherModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onSuccess(WeatherModel weatherModel) {
//                Log.d("Weather", String.valueOf(weatherModel.getMain().getTemp()));
                weatherBinding.setWeather(weatherModel);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}
