package com.example.tadeu17.dronetrack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DownloadActivity extends AppCompatActivity {

    private TextView mListaTexto;
    private RequestQueue mQueue;
    private DownloadListPhotos dLP = new DownloadListPhotos();
    private ArrayList<String> name = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        mQueue= Volley.newRequestQueue(this);
        dLP.jsonParse( mQueue);
        name = dLP.getListaFotos();

    }

    public void showPhotos(View view){

       Intent i = new Intent(this, GaleryActivity.class);
       i.putStringArrayListExtra("name", name);
       startActivity(i);
        finish();
    }




}
