//Abhinay Balusu
//Swarna Durga Vallurupalli

package com.abhinaybalusu.weatherapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by abhinaybalusu on 10/6/16.
 */
public class parseJsonData {

    static ArrayList<Weather> weatherArrayList;

    public static ArrayList parseJsonDataAndReturnArrayList(String jsonData)
    {

        try {
            JSONObject jsonObj = new JSONObject(jsonData);
            JSONArray results = jsonObj.getJSONArray("hourly_forecast");

            weatherArrayList = new ArrayList<Weather>();

            for(int i=0;i<results.length();i++)
            {
                Weather w = new Weather();

                JSONObject jsonWeather = results.getJSONObject(i);

                if(jsonWeather.getJSONObject("FCTTIME") != null)
                {
                    JSONObject timeObj = jsonWeather.getJSONObject("FCTTIME");
                    String time = timeObj.getString("civil");
                    w.setFcttime(time);
                }

                if(jsonWeather.getJSONObject("temp") != null)
                {
                    JSONObject tempObj = jsonWeather.getJSONObject("temp");
                    String temp = tempObj.getString("english");
                    w.setTemperature(temp);
                }
                if(jsonWeather.getJSONObject("dewpoint") != null)
                {
                    JSONObject dewpointObj = jsonWeather.getJSONObject("dewpoint");
                    String dewpoint = dewpointObj.getString("english");
                    w.setDewpoint(dewpoint);
                }
                if(jsonWeather.getString("condition") != null)
                {
                    String cloud = jsonWeather.getString("condition");
                    w.setClouds(cloud);
                }
                if(jsonWeather.getString("icon_url") != null)
                {
                    String iconURL = jsonWeather.getString("icon_url");
                    w.setIconURL(iconURL);
                }
                if(jsonWeather.getJSONObject("wspd") != null)
                {
                    JSONObject wSpeedObj = jsonWeather.getJSONObject("wspd");
                    String wSpeed = wSpeedObj.getString("english");
                    w.setwSpeed(wSpeed);
                }
                if(jsonWeather.getJSONObject("wdir") != null)
                {
                    JSONObject wDirectionObj = jsonWeather.getJSONObject("wdir");
                    String wDir = wDirectionObj.getString("degrees")+"\u00b0"+wDirectionObj.getString("dir");
                    w.setwDir(wDir);
                }
                if(jsonWeather.getString("wx") != null)
                {
                    //JSONObject climateTypeObj = jsonWeather.getJSONObject("wx");
                    String climateType = jsonWeather.getString("wx");
                    w.setClimateType(climateType);
                }
                if(jsonWeather.getString("humidity") != null)
                {
                    String humidity = jsonWeather.getString("humidity");
                    w.setHumidity(humidity);
                }

                if(jsonWeather.getJSONObject("feelslike") != null)
                {
                    JSONObject feelslikeObj = jsonWeather.getJSONObject("feelslike");
                    String feelslike = feelslikeObj.getString("english");
                    w.setFeelslike(feelslike);
                }
                if(jsonWeather.getJSONObject("mslp") != null)
                {
                    JSONObject mslpObj = jsonWeather.getJSONObject("mslp");
                    String mslp = mslpObj.getString("english");
                    w.setPressure(mslp);
                }

                weatherArrayList.add(w);

            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

        return weatherArrayList;
    }
}
