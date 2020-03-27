package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static java.lang.System.in;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView2;

    public void showWeather(View view){

        Weather task = new Weather();
        task.execute("https://samples.openweathermap.org/data/2.5/weather?q=London&appid=b6907d289e10d714a6e88b30761fae22");



    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
        textView2 = (TextView) findViewById(R.id.textView2);

    }


    public class Weather extends AsyncTask<String,Void, String>{
        @Override
        protected String doInBackground(String... urls) {
            try {

                String result="";
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = (InputStream) connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(in);

                int data = inputStreamReader.read();

                while(data!=-1){
                    result+=(char) data;
                    data=inputStreamReader.read();
                }

                Log.i("dasdas",result);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            String message = "";

            try {
                JSONObject jsonObject = new JSONObject(result);


                String weatherInfo = jsonObject.getString("weather");

                JSONArray arr = new JSONArray(weatherInfo);

                for(int i=0;i<arr.length();i++){
                    JSONObject jsonPart = arr.getJSONObject(i);

                    String main="";
                    String description="";

                    main = jsonPart.getString("main");
                    description = jsonPart.getString("description");

                    if (main != "" && description != "") {

                        message += main + ": " + description + "\r\n";

                    }
                }
                if (message != "") {

                    textView2.setText(message);

                } else {

                    Toast.makeText(getApplicationContext(), "Could not find weather", Toast.LENGTH_LONG);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }



        }
    }


}


