//Abhinay Balusu
//Swarna Durga Vallurupalli

package com.abhinaybalusu.weatherapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by abhinaybalusu on 10/6/16.
 */
public class Weather implements Parcelable{

    String fcttime, temperature, dewpoint, clouds, iconURL, wSpeed, wDir, climateType, humidity, feelslike, pressure;

    public Weather(String fcttime, String temperature, String dewpoint, String clouds, String iconURL, String wSpeed, String wDir, String climateType, String humidity, String feelslike, String pressure) {
        this.fcttime = fcttime;
        this.temperature = temperature;
        this.dewpoint = dewpoint;
        this.clouds = clouds;
        this.iconURL = iconURL;
        this.wSpeed = wSpeed;
        this.wDir = wDir;
        this.climateType = climateType;
        this.humidity = humidity;
        this.feelslike = feelslike;
        this.pressure = pressure;
    }

    public Weather()
    {

    }
    protected Weather(Parcel in) {
        fcttime = in.readString();
        temperature = in.readString();
        dewpoint = in.readString();
        clouds = in.readString();
        iconURL = in.readString();
        wSpeed = in.readString();
        wDir = in.readString();
        climateType = in.readString();
        humidity = in.readString();
        feelslike = in.readString();
        pressure = in.readString();
    }

    public static final Creator<Weather> CREATOR = new Creator<Weather>() {
        @Override
        public Weather createFromParcel(Parcel in) {
            return new Weather(in);
        }

        @Override
        public Weather[] newArray(int size) {
            return new Weather[size];
        }
    };

    public String getFcttime() {
        return fcttime;
    }

    public void setFcttime(String fcttime) {
        this.fcttime = fcttime;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getDewpoint() {
        return dewpoint;
    }

    public void setDewpoint(String dewpoint) {
        this.dewpoint = dewpoint;
    }

    public String getClouds() {
        return clouds;
    }

    public void setClouds(String clouds) {
        this.clouds = clouds;
    }

    public String getIconURL() {
        return iconURL;
    }

    public void setIconURL(String iconURL) {
        this.iconURL = iconURL;
    }

    public String getwSpeed() {
        return wSpeed;
    }

    public void setwSpeed(String wSpeed) {
        this.wSpeed = wSpeed;
    }

    public String getwDir() {
        return wDir;
    }

    public void setwDir(String wDir) {
        this.wDir = wDir;
    }

    public String getClimateType() {
        return climateType;
    }

    public void setClimateType(String climateType) {
        this.climateType = climateType;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getFeelslike() {
        return feelslike;
    }

    public void setFeelslike(String feelslike) {
        this.feelslike = feelslike;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }




    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fcttime);
        dest.writeString(temperature);
        dest.writeString(dewpoint);
        dest.writeString(clouds);
        dest.writeString(iconURL);
        dest.writeString(wSpeed);
        dest.writeString(wDir);
        dest.writeString(climateType);
        dest.writeString(humidity);
        dest.writeString(feelslike);
        dest.writeString(pressure);
    }
}
