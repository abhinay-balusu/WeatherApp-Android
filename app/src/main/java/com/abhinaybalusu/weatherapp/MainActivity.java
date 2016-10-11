//Abhinay Balusu
//Swarna Durga Vallurupalli

package com.abhinaybalusu.weatherapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView noFavListLabel;
    ListView favListView;
    TextView favTextViewLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText cityEditText = (EditText)findViewById(R.id.cityEditText);
        final EditText stateEditText = (EditText)findViewById(R.id.stateEditText);

        Button submitButton = (Button)findViewById(R.id.submitButton);

        favListView = (ListView)findViewById(R.id.favListView);

        noFavListLabel = (TextView)findViewById(R.id.noFavLabelTextView);

        favTextViewLabel = (TextView)findViewById(R.id.favTextViewLabel);
        noFavListLabel.setVisibility(View.INVISIBLE);
        favListView.setVisibility(View.INVISIBLE);
        favTextViewLabel.setVisibility(View.INVISIBLE);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(cityEditText.getText().toString().equals("") || stateEditText.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Please Enter All The Details",Toast.LENGTH_SHORT).show();
                }
//                else if(!(stateEditText.getText().toString().matches("[a-zA-Z]")) && !(stateEditText.getText().toString().matches("[a-zA-Z]"))){
//
//                    Toast.makeText(getApplicationContext(),"Please Enter Valid Details",Toast.LENGTH_SHORT).show();
//                }
                else
                {
                    String city = cityEditText.getText().toString();
                    String state = stateEditText.getText().toString();
                    String formattedCity = city.replace(" ", "_");
                    String urlString = "http://api.wunderground.com/api/465b67f838778897/hourly/q/"+state+"/"+formattedCity+".json";
                    Intent intent = new Intent(MainActivity.this,CityWeatherActivity.class);
                    intent.putExtra("weatherURL",urlString);
                    intent.putExtra("city",city);
                    intent.putExtra("state",state);
                    startActivity(intent);

                }

            }
        });

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        final Gson gson = new Gson();
        SharedPreferences Prefs = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor prefsEditor = Prefs.edit();
        ArrayList<FavWeather> wList = null;

        if(Prefs.getString("favlist",null) != null)
        {
            String jsonText = Prefs.getString("favlist", null);
            //String[] text = gson.fromJson(jsonText, String[].class);
            Type type = new TypeToken<ArrayList<FavWeather>>(){}.getType();
            wList = gson.fromJson(jsonText, type);
            //wList = (ArrayList<FavWeather>) gson.fromJson(jsonText,ArrayList.class);

            if(wList.size() == 0)
            {
                noFavListLabel.setVisibility(View.VISIBLE);
                favListView.setVisibility(View.INVISIBLE);
                favTextViewLabel.setVisibility(View.INVISIBLE);
            }
            else
            {
                noFavListLabel.setVisibility(View.INVISIBLE);
                favListView.setVisibility(View.VISIBLE);
                favTextViewLabel.setVisibility(View.VISIBLE);

                FavCustomAdapter arrayAdapter = new FavCustomAdapter(this,R.layout.row_item_fav_layout,wList);

                favListView.setAdapter(arrayAdapter);


                final ArrayList<FavWeather> finalWList = wList;
                favListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                        if(finalWList != null)
                        {
                            finalWList.remove(position);

                            String jsonText = gson.toJson(finalWList);
                            prefsEditor.putString("favlist", jsonText);
                            prefsEditor.commit();

                            FavCustomAdapter arrayAdapter = new FavCustomAdapter(MainActivity.this,R.layout.row_item_fav_layout,finalWList);

                            favListView.setAdapter(arrayAdapter);

                            Toast.makeText(getApplicationContext(),"Successfully Deleted",Toast.LENGTH_SHORT).show();

                            if(finalWList.size()==0)
                            {
                                noFavListLabel.setVisibility(View.VISIBLE);
                                favTextViewLabel.setVisibility(View.INVISIBLE);
                                favListView.setVisibility(View.INVISIBLE);
                            }
                        }
                        else
                        {
                            noFavListLabel.setVisibility(View.VISIBLE);
                            favListView.setVisibility(View.INVISIBLE);
                            favTextViewLabel.setVisibility(View.INVISIBLE);
                        }
                        return false;
                    }
                });

            }
        }


    }
}
