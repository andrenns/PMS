package com.example.tadeu17.dronetrack;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    int create_status = 0;
    AlertDialog alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_menu);
        alertDialog = new android.support.v7.app.AlertDialog.Builder(this).create();
        alertDialog.setTitle("Create Account Status");
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            create_status = extras.getInt("create_status");
        }

        if(create_status==1)
        {
            alertDialog.setMessage("Account Created sucessfully! Log in to start using the app");
            alertDialog.show();
        }

    }



    public void loginScreen (View v)
    {
        startActivity(new Intent(this, SignInActivity.class));

    }

    public void createScreen (View v)
    {
        startActivity(new Intent(this, CreateActivity.class ));

    }

    public void guestScreen (View v)
    {
        startActivity(new Intent(this, WelcomeActivity.class));

    }


}

