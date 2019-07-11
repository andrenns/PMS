package com.example.tadeu17.dronetrack;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class SignInActivity extends AppCompatActivity {

    EditText usernameEt, passwordEt;
    String username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        usernameEt = findViewById(R.id.username_login);
        passwordEt = findViewById(R.id.password);

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }


    public void performLogin(View v)
    {
        username = usernameEt.getText().toString();
        password = passwordEt.getText().toString();
        String type = "login";
        BackgroundWorker bW = new BackgroundWorker(this);
        bW.execute(type,username, password);

    }

}



