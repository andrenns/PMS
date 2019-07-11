package com.example.tadeu17.dronetrack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    TextView userName;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        userName = findViewById(R.id.nome_utilizador);
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            name = extras.getString("name");

        }

        userName.setText(name);


    }

    public void menuPrincipal(View v)
    {

        Intent i = new Intent(this, MapsActivity.class);
        i.putExtra("flagWelcome",1);
        startActivity(i);
        finish();
    }

}
