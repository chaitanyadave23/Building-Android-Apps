package com.example.downloadimage;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    public void download(View view) throws ExecutionException, InterruptedException {

        ImageDownloader task = new ImageDownloader();
        Bitmap myImage = task.execute("https://upload.wikimedia.org/wikipedia/en/a/aa/Bart_Simpson_200px.png").get();
        imageView.setImageBitmap(myImage);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView2);
    }

    class ImageDownloader extends AsyncTask<String,Void, Bitmap>{


        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();//Firing the http get request: After we have created the url and have created a HttpURLConnection object, we have to actually fire a request. It can explicitly be done by connect() method. It is rather implicitly done whenever we try to use any response message such as getOutputStream() etc

                InputStream inputStream = connection.getInputStream(); //gets all the data in one go
                Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);
                return myBitmap;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return null;
        }


    }


}
