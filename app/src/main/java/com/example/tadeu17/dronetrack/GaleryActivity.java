package com.example.tadeu17.dronetrack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GaleryActivity extends AppCompatActivity {


    private static final String TAG = "GaleryActivity";
    private RequestQueue mQueue;
    DownloadListPhotos dLP = new DownloadListPhotos();
    List<listItem> photosGaleryList = new ArrayList<>();
    ArrayList<String> name = new ArrayList<>();
    listItem photo;
    private ArrayList<String> urls = new ArrayList<>();
    private int user_id;
    private String username;
    private String location;
    Globals g = Globals.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galery);

        user_id=g.getUserId();
        username = g.getName();
        location = g.getLocation();
        Log.d(TAG, "onCreate: started!");
        mQueue= Volley.newRequestQueue(this);
        dLP.jsonParse(mQueue);
        name = getIntent().getStringArrayListExtra("name");
        fillArray();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.download_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id==R.id.download_pictures)
        {
            Intent intent = new Intent(this, WaintingDownload.class);
            intent.putExtra("LIST", (Serializable) photosGaleryList);
            Toast.makeText(getApplicationContext(), "Download", Toast.LENGTH_LONG).show();
            this.startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }



    public void fillArray(){
        String url;
        Log.d(TAG, "fillArray: preparing array");

        for (int i = name.size() -1; i >=0 ; i--) {

            url = "http://10.5.5.9:8080/videos/DCIM/100GOPRO/" + name.get(i);
            photo = new listItem(user_id,username, location, url);
            photosGaleryList.add(photo);

        }

        initReceyclerView();

    }

    private void initReceyclerView(){
        
        Log.d(TAG, "initReceyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.galeryRecyclerView);
        galeryAdapter adapter = new galeryAdapter(photosGaleryList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

}

