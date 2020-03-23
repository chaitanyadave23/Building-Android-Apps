package com.example.eggtimerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    SeekBar seekBar;
    Boolean isActive = false;
    public void updatetimmer(int p){
        int min = (int) p/60;
        int sec = p-min*60;
        if(sec<10){
            textView.setText(min+":0"+sec);
        }
        else{
            textView.setText(min+":"+sec);
        }
    }

    public void controlButton(View view){
        isActive = true;
        seekBar.setEnabled(false);

        Log.i("Finished", "buttonclicklded" );
        CountDownTimer countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updatetimmer((int) millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                textView.setText("00:00");
                MediaPlayer mp = MediaPlayer.create(getApplicationContext(),R.raw.airhorn);
                mp.start();
                seekBar.setEnabled(true);
            }
        }.start();


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = (SeekBar) findViewById(R.id.seekBar2);
        seekBar.setMax(600);
        seekBar.setProgress(30);
        textView = (TextView) findViewById(R.id.textView);





        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updatetimmer(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

}
