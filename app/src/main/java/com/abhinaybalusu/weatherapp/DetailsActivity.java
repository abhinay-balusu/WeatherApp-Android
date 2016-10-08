//Abhinay Balusu
//Swarna Durga Vallurupalli

package com.abhinaybalusu.weatherapp;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Weather wData = (Weather) getIntent().getExtras().get(CityWeatherActivity.WEATHER_DATA);

        TextView currentLocTV = (TextView)findViewById(R.id.currentLocTV_Details);

        ImageView wImageView = (ImageView)findViewById(R.id.wImageView_Details);
        TextView tempTextView_Details = (TextView)findViewById(R.id.tempTextView_Details);
        TextView climateTypeTextView_Details = (TextView)findViewById(R.id.climateTypeTextView_Details);
        TextView maxTempTextView_Details = (TextView)findViewById(R.id.maxTempTextView_Details);
        TextView minTempTextView_Details = (TextView)findViewById(R.id.minTempTextView_Details);
        TextView feelslikeTextView_Details = (TextView)findViewById(R.id.feelslikeTextView_Details);
        TextView humidityTextView_Details = (TextView)findViewById(R.id.humidityTextView_Details);
        TextView dewpointTextView_Details = (TextView)findViewById(R.id.dewpointTextView_Details);
        TextView pressureTextView_Details = (TextView)findViewById(R.id.pressureTextView_Details);
        TextView cloudsTextView_Details = (TextView)findViewById(R.id.cloudsTextView_Details);
        TextView windsTextView_Details = (TextView)findViewById(R.id.windsTextView_Details);

        currentLocTV.setText(getIntent().getExtras().getString("city")+","+getIntent().getExtras().getString("state")+" "+"("+wData.getFcttime()+")");

        Picasso.with(this).load(wData.getIconURL()).into(wImageView);
        tempTextView_Details.setText(wData.getTemperature()+"\u00b0"+"F");
        climateTypeTextView_Details.setText(wData.getClimateType());
        maxTempTextView_Details.setText(getIntent().getExtras().getString("max")+" Fahrenheit");
        minTempTextView_Details.setText(getIntent().getExtras().getString("min")+" Fahrenheit");
        feelslikeTextView_Details.setText(wData.getFeelslike()+" Fahrenheit");
        humidityTextView_Details.setText(wData.getHumidity()+"%");
        dewpointTextView_Details.setText(wData.getDewpoint()+" Fahrenheit");
        pressureTextView_Details.setText(wData.getPressure()+" hpa");
        cloudsTextView_Details.setText(wData.getClouds());
        windsTextView_Details.setText(wData.getwSpeed()+" mph"+","+wData.getwDir());


    }
}
