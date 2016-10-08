//Abhinay Balusu
//Swarna Durga Vallurupalli

package com.abhinaybalusu.weatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by abhinaybalusu on 10/7/16.
 */
public class CustomAdapter extends ArrayAdapter<Weather> {

    List<Weather> mData;
    Context mContext;
    int mResource;

    public CustomAdapter(Context context, int resource, List<Weather> objects) {
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

        TextView hourTextView = (TextView) convertView.findViewById(R.id.hourTextView);
        TextView climateTypeTextView = (TextView) convertView.findViewById(R.id.climateTypeTextView);
        TextView tempTextView = (TextView) convertView.findViewById(R.id.temperatureTextView);
        ImageView weatherImageView = (ImageView) convertView.findViewById(R.id.weatherImageView);

        Weather weather = mData.get(position);

        hourTextView.setText(weather.getFcttime());
        climateTypeTextView.setText(weather.getClimateType());
        tempTextView.setText(weather.getTemperature()+"\u00b0"+"F");

        Picasso.with(mContext).load(weather.getIconURL()).into(weatherImageView);

        return convertView;
    }
}
