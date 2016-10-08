//Abhinay Balusu
//Swarna Durga Vallurupalli

package com.abhinaybalusu.weatherapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class CityWeatherActivity extends AppCompatActivity implements GetWeatherDataAsyncTask.GetContext{

    ListView hourlyDataListView;
    String cityStr;
    String stateString;
    ArrayList temperaturesList;
    public final static String WEATHER_DATA = "WeatherList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_weather);

        cityStr = getIntent().getExtras().getString("city");
        stateString = getIntent().getExtras().getString("state");

        temperaturesList = new ArrayList();

        isConnected();

    }

    @Override
    public Context getContext() {
        return this;
    }
    private Boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nf = cm.getActiveNetworkInfo();
        if (nf != null && nf.isConnected()) {
            String url = getIntent().getExtras().getString("weatherURL");
            new GetWeatherDataAsyncTask(this).execute(url);
        } else {
            Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.fav_item_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.fav_item)
        {
            Gson gson = new Gson();
            SharedPreferences Prefs = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor prefsEditor = Prefs.edit();
            ArrayList<FavWeather> wList;

            if(Prefs.getString("favlist",null) != null)
            {
                String jsonText = Prefs.getString("favlist", null);
                //String[] text = gson.fromJson(jsonText, String[].class);
                Type type = new TypeToken<ArrayList<FavWeather>>(){}.getType();
                wList = gson.fromJson(jsonText, type);
                //wList = gson.fromJson(jsonText,ArrayList.class);
            }
            else
            {
                //Set the values
                wList = new ArrayList<FavWeather>();

            }

            Date date = new Date();
            SimpleDateFormat formattedDate = new SimpleDateFormat("MM/dd/yy");
            String newDate = formattedDate.format(date);

            FavWeather favW = null;

            boolean isAlreadyAddedToFav = false;

            for(FavWeather fw: wList)
            {
                if(fw.getCity().toString().equalsIgnoreCase(cityStr) && fw.getState().toString().equalsIgnoreCase(stateString))
                {
                    isAlreadyAddedToFav = true;
                    favW = fw;
                    favW.setCity(cityStr);
                    favW.setState(stateString);
                    favW.setDate(newDate);
                    favW.setTemperature(temperaturesList.get(0).toString());
                }
            }
            if(isAlreadyAddedToFav)
            {
                Toast.makeText(getApplicationContext(),"Already Added to Favourites",Toast.LENGTH_SHORT).show();
            }
            else
            {
                favW = new FavWeather();
                favW.setCity(cityStr);
                favW.setState(stateString);
                favW.setDate(newDate);
                favW.setTemperature(temperaturesList.get(0).toString());

                wList.add(favW);
                Toast.makeText(getApplicationContext(),"Successfully Added to Favourites",Toast.LENGTH_SHORT).show();
            }
            String jsonText = gson.toJson(wList);
            prefsEditor.putString("favlist", jsonText);
            prefsEditor.commit();



        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setupData(final ArrayList<Weather> weatherArrayList) {


        TextView currentLocTV = (TextView)findViewById(R.id.currentLocationTextView);
        currentLocTV.setText(cityStr+","+stateString);

        if(weatherArrayList == null)
        {
            Toast.makeText(getApplicationContext(),"No cities match your query",Toast.LENGTH_SHORT).show();

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    finish();
                }
            }, 5000);
        }
        else
        {
            for(int t=0;t<weatherArrayList.size();t++)
            {
                temperaturesList.add(Double.parseDouble(weatherArrayList.get(t).getTemperature()));
            }

            final String maxTemp = String.valueOf(Collections.max(temperaturesList));
            final String minTemp = String.valueOf(Collections.min(temperaturesList));

            hourlyDataListView = (ListView)findViewById(R.id.hourlyDataListView);

            CustomAdapter arrayAdapter = new CustomAdapter(this,R.layout.row_item_layout,weatherArrayList);

            hourlyDataListView.setAdapter(arrayAdapter);
            arrayAdapter.setNotifyOnChange(true);

            hourlyDataListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                    Intent intent = new Intent(CityWeatherActivity.this,DetailsActivity.class);
                    intent.putExtra(WEATHER_DATA,weatherArrayList.get(position));
                    intent.putExtra("city",cityStr);
                    intent.putExtra("state",stateString);
                    intent.putExtra("max",maxTemp);
                    intent.putExtra("min",minTemp);
                    startActivity(intent);

                }
            });
        }
    }
}
