package com.example.tadeu17.dronetrack;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class DownloadedActivity extends AppCompatActivity {

    private static final String TAG = "DownloadedActivity";
    List<listItem> photosGaleryList = new ArrayList<>();
    Cursor cursor;
    int flagMenu = 0;
    Globals g = Globals.getInstance();
    ArrayList<listItem> insertList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloaded);
        Bundle extras = getIntent().getExtras();
        if(extras!=null)
        {
            flagMenu = extras.getInt("flagMenu");
        }
        Log.d(TAG, "onCreate:"+flagMenu);
        cursor = StartMenuActivity.sqLiteHelper.getData("SELECT * FROM PICTURE");
        fillArray();

    }

    @Override
    protected void onPause() {
     super.onPause();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       if(flagMenu==1)
       {
            getMenuInflater().inflate(R.menu.new_report_menu, menu);
       }
           getMenuInflater().inflate(R.menu.insert_data_online, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id==R.id.new_report)
        {
            startActivity(new Intent(this, CheckConnectionActivity.class));
        }
        else if(id == R.id.insert_data)
        {
            BWInsertOnlineDB bwInsertOnlineDB = new BWInsertOnlineDB();
           bwInsertOnlineDB.execute(insertList);
        }

        return super.onOptionsItemSelected(item);
    }

    private void fillArray()
    {
        listItem photo;
        Log.d(TAG, "fillArray: preparing array");

        photosGaleryList.clear();
if(cursor==null) {
    Toast.makeText(getApplicationContext(), "base de dados vazia", Toast.LENGTH_LONG).show();
}
else
{
    while (cursor.moveToNext())
    {
        int user_id= cursor.getInt(0);
        String name = cursor.getString(1);
        String location = cursor.getString(2);
        String description = cursor.getString(3);
        byte[] img = cursor.getBlob(4);
        if(user_id == g.getUserId())
        {
            photo = new listItem(user_id, name, location, null);
            photosGaleryList.add(photo);
            photo.setImgB(img);
            photo = new listItem(user_id, img, location);
            insertList.add(photo);
        }

    }
}
        initReceyclerView();



    }

    private void initReceyclerView(){

        Log.d(TAG, "initReceyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.downloadedRecyclerView);
        downloadedAdapter adapter = new downloadedAdapter(photosGaleryList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public class BWInsertOnlineDB extends AsyncTask<ArrayList<listItem>,Void,Void> {

        private ProgressDialog progressDialog;

        @Override
        protected Void doInBackground(ArrayList<listItem>... arrayLists) {

            ArrayList<listItem> passed = arrayLists[0];
            listItem item;

            for(int i=0; i < passed.size();i++)
            {
                item=passed.get(i);

                String insert_url = "http://drone.tigerwhale.com/insert.php";

                int id = item.getUser_id();
                String location = item.getLocationLI();
                byte[] imgB = item.getImgB();
                String user_id = Integer.toString(id);
                String img = Base64.getEncoder().encodeToString(imgB);
                try
                {
                    URL url = new URL(insert_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                    String post_data = URLEncoder.encode("user_id","UTF-8")+"="+URLEncoder.encode(user_id,"UTF-8")+"&"
                            +URLEncoder.encode("location","UTF-8")+"="+URLEncoder.encode(location,"UTF-8")+"&"
                            +URLEncoder.encode("img","UTF-8")+"="+URLEncoder.encode(img,"UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                StringBuilder sb = new StringBuilder();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String json;
                while((json=bufferedReader.readLine())!=null)
                {
                    sb.append(json+"\n");
                }
                httpURLConnection.disconnect();
               // return sb.toString().trim();
                }
                catch (MalformedURLException e)
                {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPreExecute() {

            progressDialog = ProgressDialog.show(DownloadedActivity.this,
                    "Wait", "Uploading Images");


        }

        @Override
        protected void onPostExecute(Void result) {
            progressDialog.dismiss();
        }
    }
}


