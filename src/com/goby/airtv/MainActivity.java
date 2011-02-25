package com.goby.airtv;

import com.goby.airtv.airplay.AirplayManager;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Log.d("MainActivity", "start");
        
        // Start the Airplay Manager
        AirplayManager manager = new AirplayManager();
        manager.start();
    }
}