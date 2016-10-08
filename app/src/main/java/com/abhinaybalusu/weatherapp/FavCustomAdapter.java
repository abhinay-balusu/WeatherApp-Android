package com.abhinaybalusu.weatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhinaybalusu on 10/7/16.
 */
//Abhinay Balusu
//Swarna Durga Vallurupalli

public class FavCustomAdapter extends ArrayAdapter<FavWeather>{

    ArrayList<FavWeather> mData;
    Context mContext;
    int mResource;

    public FavCustomAdapter(Context context, int resource, ArrayList<FavWeather> objects) {
        super(context, resource, objects);

        this.mContext = context;
        this.mData = objects;
        this.mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource,parent,false);
        }

        FavWeather favWeather = (FavWeather)mData.get(position);

        TextView currentLocTextView = (TextView) convertView.findViewById(R.id.currentLocationTextView_fav);
        TextView tempTextView = (TextView) convertView.findViewById(R.id.temperatureTextView_fav);
        TextView dateTextView = (TextView) convertView.findViewById(R.id.dateTextView_fav);

        currentLocTextView.setText(favWeather.getCity()+", "+favWeather.getState());
        tempTextView.setText(favWeather.getTemperature()+"°"+" F");
        dateTextView.setText("Updated On: "+favWeather.getDate());

        return convertView;
    }
}
