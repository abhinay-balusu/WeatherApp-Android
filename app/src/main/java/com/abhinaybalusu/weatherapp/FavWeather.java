//Abhinay Balusu
//Swarna Durga Vallurupalli

package com.abhinaybalusu.weatherapp;

/**
 * Created by abhinaybalusu on 10/7/16.
 */
public class FavWeather {

    String city, state, date, temperature;

    public FavWeather(String city, String state, String date, String temperature) {
        this.city = city;
        this.state = state;
        this.date = date;
        this.temperature = temperature;
    }
    public FavWeather()
    {

    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
