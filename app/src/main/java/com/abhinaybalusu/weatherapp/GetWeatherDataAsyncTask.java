//Abhinay Balusu
//Swarna Durga Vallurupalli

package com.abhinaybalusu.weatherapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by abhinaybalusu on 10/6/16.
 */
public class GetWeatherDataAsyncTask extends AsyncTask<String, Void,ArrayList<Weather>>{

    GetContext activity;
    ProgressDialog pd;
    public GetWeatherDataAsyncTask(GetContext activity) {
        this.activity = activity;
    }

    @Override
    protected ArrayList<Weather> doInBackground(String... params) {

        BufferedReader br = null;

        try {
            URL url = new URL(params[0]);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("GET");

            br=new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line= "";

            while ((line=br.readLine())!=null){
                sb.append(line+"\n");
            }

            return parseJsonData.parseJsonDataAndReturnArrayList(sb.toString());


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd=new ProgressDialog(activity.getContext());
        pd.setMessage("Loading Hourly Data");
        pd.setMax(100);
        pd.setCancelable(false);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.show();
    }

    @Override
    protected void onPostExecute(ArrayList<Weather> weathers) {
        super.onPostExecute(weathers);
        pd.dismiss();
        activity.setupData(weathers);
    }

    public static interface GetContext {
        public Context getContext();
        public void setupData(ArrayList<Weather> weatherArrayList);
    }
}
