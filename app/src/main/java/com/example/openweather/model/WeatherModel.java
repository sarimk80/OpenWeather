package com.example.openweather.model;

import java.util.List;

public class WeatherModel {


    private Coord coord;//": {
    //        "lon": -0.13,
//                "lat": 51.51
//    },
    List<Weather> weather;//": [
    //        {
//            "id": 300,
//                "main": "Drizzle",
//                "description": "light intensity drizzle",
//                "icon": "09d"
//        }
//],
    private String base;//": "stations",
    private Main main;//": {
    //        "temp": 280.32,
//                "pressure": 1012,
//                "humidity": 81,
//                "temp_min": 279.15,
//                "temp_max": 281.15
//    },
    private long visibility;//": 10000,
    private Wind wind;//":

    //    {
//        "speed":4.1,
//            "deg":80
//    },
    private Clouds clouds;//":
    //
//    {
//        "all":90
//    },
    private long dt;//":1485789600,
    private Sys sys;//":
    //
//    {
//        "type":1,
//            "id":5091,
//            "message":0.0103,
//            "country":"GB",
//            "sunrise":1485762037,
//            "sunset":1485794875
//    },
    private long id;//":2643743,
    private String name;//":"London",
    private int cod;//":200

    public WeatherModel(Coord coord, List<Weather> weather, String base, Main main, long visibility, Wind wind, Clouds clouds, long dt, Sys sys, long id, String name, int cod) {
        this.coord = coord;
        this.weather = weather;
        this.base = base;
        this.main = main;
        this.visibility = visibility;
        this.wind = wind;
        this.clouds = clouds;
        this.dt = dt;
        this.sys = sys;
        this.id = id;
        this.name = name;
        this.cod = cod;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public long getVisibility() {
        return visibility;
    }

    public void setVisibility(long visibility) {
        this.visibility = visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }
}
