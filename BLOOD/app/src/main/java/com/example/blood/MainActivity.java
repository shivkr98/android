package com.example.blood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
    private  int SLEEP_TIMER=3 ;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        logolauncher logolauncher = new logolauncher();
        logolauncher.start();


    }

    private class logolauncher extends Thread{
        public void run(){
            try {
                sleep(1000*SLEEP_TIMER);
            }catch(InterruptedException  e) {
                e.printStackTrace();
            }

            // we use intant when we move from one activity to another activity
            Intent intent = new Intent(MainActivity.this,main_next.class);
            startActivity(intent);

            // after this we have to distro this activity
            MainActivity.this.finish();

        }
    }

}
