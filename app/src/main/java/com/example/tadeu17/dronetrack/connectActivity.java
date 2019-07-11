package com.example.tadeu17.dronetrack;

import android.content.ContextWrapper;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;

public class connectActivity extends AppCompatActivity {


    OkHttpClient client;
    Request takePhoto;
    Request turnOff;
    Request photoMode;
    Response response;
    ContextWrapper con;
    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);
        client = new OkHttpClient();
        takePhoto = new Request.Builder()
                .url("http://10.5.5.9/gp/gpControl/command/shutter?p=1")
                .build();


        turnOff = new Request.Builder()
                .url("http://10.5.5.9/gp/gpControl/command/system/sleep")
                .build();

        photoMode = new Request.Builder()
                .url("http://10.5.5.9/gp/gpControl/command/mode?p=1")
                .build();
        con = new ContextWrapper(getApplicationContext());
        StrictMode.setThreadPolicy(policy);
    }





    public void turnON(View view){
        WakeOnLan.wakeup("10.5.5.255", "064169123770");
          }


    public void takePhoto(View view)
    {
        try {
            response = client.newCall(takePhoto).execute();
        }
        catch(IOException e)
        {
            Toast.makeText(con,"Failure!", Toast.LENGTH_LONG).show();
        }
    }

    public void turnOFF(View view){
        try {
            response = client.newCall(turnOff).execute();
        }
        catch(IOException e)
        {
            Toast.makeText(con,"Failure!", Toast.LENGTH_LONG).show();

        }
    }

    public void photoMode(View view)
    {
        try {
            response = client.newCall(photoMode).execute();
        }
        catch(IOException e)
        {
            Toast.makeText(con,"Failure!", Toast.LENGTH_LONG).show();

        }
    }
}
