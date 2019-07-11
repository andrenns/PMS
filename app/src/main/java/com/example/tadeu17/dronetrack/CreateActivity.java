package com.example.tadeu17.dronetrack;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateActivity extends AppCompatActivity {

    EditText nameEt, locationEt, ageEt, usernameEt, passwordEt, confPasswordEt;
    String nameE,locationE,usernameE,ageE,passwordE,confPassE;
    AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        nameEt = findViewById(R.id.name_create);
        locationEt = findViewById(R.id.location_create);
        ageEt = findViewById(R.id.age_create);
        usernameEt = findViewById(R.id.username_create);
        passwordEt = findViewById(R.id.password_create);
        confPasswordEt = findViewById(R.id.confirm_password_create);
         alertDialog = new android.support.v7.app.AlertDialog.Builder(this).create();
        alertDialog.setTitle("Create Status");


    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    public void createAccount (View v)
    {
        nameE = nameEt.getText().toString();
        locationE = locationEt.getText().toString();
        ageE = ageEt.getText().toString();
        usernameE = usernameEt.getText().toString();
        passwordE = passwordEt.getText().toString();
        confPassE = confPasswordEt.getText().toString();

        if(nameE == null || locationE == null || ageE==null|| usernameE==null || passwordE==null||confPassE==null)
        {
            Toast.makeText(this,"Some fields remanied blank!Please fill all the fields", Toast.LENGTH_LONG);
        }
        else
        {
            if(passwordE.equals(confPassE))
            {
                String type = "create";
                BackgroundWorker backgroundWorker = new BackgroundWorker(this,passwordEt,confPasswordEt);
                backgroundWorker.execute(type,nameE,locationE,ageE,usernameE,passwordE);

            }
            else
            {

                alertDialog.setMessage("Password does not match!");
                alertDialog.show();
                passwordEt.setText("");
                confPasswordEt.setText("");
            }
        }

    }
}
