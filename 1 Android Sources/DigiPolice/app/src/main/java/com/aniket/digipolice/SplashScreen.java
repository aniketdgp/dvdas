package com.aniket.digipolice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    private final int WAIT_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                //Simulating a long running task
                //this.Sleep(1000);
                /* Create an Intent that will start the ProfileData-Activity. */
                Intent mainIntent = new Intent(SplashScreen.this,Login.class);
                startActivity(mainIntent);
                SplashScreen.this.finish();
            }
        },WAIT_TIME);
    }
}
