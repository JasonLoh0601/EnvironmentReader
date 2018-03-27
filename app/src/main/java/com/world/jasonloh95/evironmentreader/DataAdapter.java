package com.world.jasonloh95.evironmentreader;


import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


import android.content.Intent;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.io.FileWriter;

import javax.net.ssl.HttpsURLConnection;


/**
 * Created by JasonLoh95 on 26/3/2018.
 */

public class DataAdapter extends AsyncTask<Void,Void,Void>{
    private static final String TAG = "MyActivity";

    //declare the value
    String p;
    public String west ,west1 ;
    public String north , north1 ;
    public  String south , south1 ;
    public String east ,east1;
    public String central  , central1 ;
    public String national ,national1 ;
    public String timeStamps ;
    String line;
    String readings;
    boolean check1 = true;
    private Context mContext;

    //get context to run function.
    public DataAdapter(Context context){
        mContext = context;
    }

    //get the value from other class for later use.
     public void getProcess(String p){
         this.p=p;
     }

     //this is the function use to get psi and PM25 from api.
    @Override
    protected  Void  doInBackground(Void... voids) {

        try {
            //set url where i want get resource.
            URL url = new URL ("https://api.data.gov.sg/v1/environment/psi");

            //try to connect to internet and get the data.
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();

            //read the url resource.
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            //get all the data and store in line
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line + "\n");

                // for developer Log to check the value is correct or not
                Log.i(TAG,  line);

                //Use JsonObject to get the api data such as "west" : 17. i need get the value "17".
                JSONObject jsonObject = new JSONObject(line);
                //search and get the information which one in items.
                JSONArray getData = new JSONArray(jsonObject.getString("items"));

                //read all data using for loop
                for (int i = 0; i < getData.length(); i++) {
                    JSONObject getDataItem = getData.getJSONObject(i);
                    // get the data which call timestamp in api.
                    this.timeStamps= getDataItem.getString("timestamp");

                    //psi and pm25 are in readings part so i set the searching area in readings
                    readings = getDataItem.getString("readings");
                    Log.i(TAG, timeStamps);
                    Log.i(TAG, readings);
                }

                //search the JSONObject which call pm25_twenty_four_hourly and get the data .
                String a = "pm25_twenty_four_hourly";
                JSONObject jsonObject1= new JSONObject(readings);

                String getData1 = jsonObject1.getString(a);
                JSONObject jsonObject2 = new JSONObject(getData1);

                //get data from pm25_twenty_four_hourly and store in String value.
                west = jsonObject2.getString("west");
                national = jsonObject2.getString("national");
                east = jsonObject2.getString("east");
                central = jsonObject2.getString("central");
                south = jsonObject2.getString("south");
                north = jsonObject2.getString("north");
                Log.i(TAG, getData1);

                //search the JSONObject which psi_twenty_four_hourly and get the data .
                String b = "psi_twenty_four_hourly";
                String getData2 = jsonObject1.getString(b);
                JSONObject jsonObject3 = new JSONObject(getData2);

                //get data from pm25_twenty_four_hourly and store in String value.
                west1 = jsonObject3.getString("west");
                national1 = jsonObject3.getString("national");
                east1 = jsonObject3.getString("east");
                central1 = jsonObject3.getString("central");
                south1 = jsonObject3.getString("south");
                north1 = jsonObject3.getString("north");

            }
            } catch (IOException e1) {
            e1.printStackTrace();
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        return null;
        }


    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //change th time format.
        timeStamps = timeStamps.replace("-","/");
        timeStamps = timeStamps.replace("T"," ");
        timeStamps = timeStamps.replace("+08:00"," ");


        // set textview value for home, PM25, psi class. therefore the value can be display to user.
        if (p == "home") {
            home.result.setText("Last Result: " +timeStamps);
            home.psi.setText(central1);
            home.pm25.setText(central);
        } else if (p == "PM25") {
                PM25.result.setText("Last Result: " +timeStamps);
                PM25.west.setText(west);
                PM25.north.setText(north);
                PM25.national.setText(national);
                PM25.east.setText(east);
                PM25.south.setText(south);

        } else if (p == "psi") {
            psi.result.setText("Last Result: " +timeStamps);
            psi.west.setText(west1);
            psi.north.setText(north1);
            psi.national.setText(national1);
            psi.east.setText(east1);
            psi.south.setText(south1);
        }


    }

}
