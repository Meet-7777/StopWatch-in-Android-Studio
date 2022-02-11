package com.hfad.stopwatch;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
    private boolean WasRunning;
    //Number of seconds displayed on the stopwatch.
    private int seconds = 0;
    //Is the stopwatch running?
    private boolean running;
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            WasRunning = savedInstanceState.getBoolean("WasRunning");
        }
        runTimer();
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
         savedInstanceState.putInt("seconds",seconds);
         savedInstanceState.putBoolean("running",running);
         savedInstanceState.putBoolean("WasRunning",WasRunning);
    }
//    the activity stops when not in focus
    protected void onPause(){
        super.onPause();
        WasRunning = running;
        running = false;
    }
//    the activity starts when in focus
    protected void onResume(){
        super.onResume();
        if (WasRunning){
        running = true;}
    }
    //Reset the stopwatch when the Reset button is clicked.
    public void onClickReset( View view){
        seconds = 0;
        running = false;

    }
    //Stop the stopwatch running when the Stop button is clicked.
    public void onClickStop(View view){
     running = false;
    }
    //Start the stopwatch running when the Start button is clicked.
    public void onClickStart(View view){
     running = true;
    }
    public void runTimer(){
        final TextView timeView = findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable()
        {
       @Override
            public void run(){
           int hours = seconds/3600;
           int minutes = (seconds%3600)/60;
           int secs = (seconds%60);
//         String time = String.format("%d:%02d:%02d",hours,minutes,secs);
           @SuppressLint("DefaultLocale") String time = String.format("%d:%02d:%02d",hours, minutes, secs);
           timeView.setText(time);
           if (running){
               seconds ++;
           }
           handler.postDelayed(this,1000);
       }
        });
    }


}