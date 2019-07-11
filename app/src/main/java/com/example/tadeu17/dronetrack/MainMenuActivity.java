package com.example.tadeu17.dronetrack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

    }

   /* @Override
    protected void onPause() {
        super.onPause();
        startActivity((new Intent(this,LoginActivity.class)));
        finish();
    }*/

    public void location(View view){startActivity((new Intent(this,MapsActivity.class)));}

    public void connectActivity(View view){startActivity(new Intent(this,connectActivity.class));}

    public void downloadActivity(View v)
    {
        Intent i = new Intent(this, DownloadedActivity.class);
        i.putExtra("flagMenu",2);
        startActivity(i);
    }

    public void logout(View v)
    {
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void info(View v)
    {
        startActivity(new Intent(this, AboutActivity.class));
    }

    public void galery(View v){ startActivity(new Intent(this, DownloadedActivity.class));}
}
