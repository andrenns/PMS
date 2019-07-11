package com.example.tadeu17.dronetrack;

import android.app.ProgressDialog;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.util.ByteArrayBuffer;

public class WaintingDownload extends AppCompatActivity {

    listItem item;
    List<listItem> photos = new ArrayList<>();
    String url;
    ArrayList<String> urls = new ArrayList<>();
    OkHttpClient client;
    Request deleteActual;
    Response response;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wainting_download);
        client = new OkHttpClient();

        Intent i = getIntent();
        photos = (List<listItem>) i.getSerializableExtra("LIST");

            for(int j=0; j<photos.size();j++)
            {
                item = photos.get(j);
                url = item.getImgUrl();
                urls.add(url);
            }
        new ImageDownloader().execute(urls);
    }



    public void insertImage(int i, byte[] img1) {
        item = photos.get(i);
        StartMenuActivity.sqLiteHelper.insertData(item.getUser_id(),item.getUserLI(),item.getLocationLI(),item.getDescriptionLI(),img1);
    }

    private class ImageDownloader extends AsyncTask<ArrayList<String>, Void, Void> {

        private ProgressDialog progressDialog;

        @Override
        protected Void doInBackground(ArrayList<String>... param) {

            HttpURLConnection connection = null;
            URL imageUrl = null;
            int n =0;

            ArrayList<String> passed = param[0];

            for(int j=0; j<passed.size();j++) {

                url = passed.get(j);
                n = j;


                try {
                    imageUrl = new URL(url);
                } catch (Exception e) {
                    Log.d("ImageManager", "Error: " + e.toString());
                }

                try {
                    // Initialize a new http url connection
                    connection = (HttpURLConnection) imageUrl.openConnection();

                    // Connect the http url connection
                    connection.connect();

                    // Get the input stream from http url connection
                    InputStream inputStream = connection.getInputStream();

                    // Initialize a new BufferedInputStream from InputStream
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

                    // Convert BufferedInputStream to Bitmap object
                    Bitmap bmp = BitmapFactory.decodeStream(bufferedInputStream);
                    bmp = Bitmap.createScaledBitmap(bmp, bmp.getWidth() / 3, bmp.getHeight() / 3, false);

                    ByteArrayOutputStream stream = new ByteArrayOutputStream();

                    bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);


                    byte[] byteArray = stream.toByteArray();

                    insertImage(n, byteArray);

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    // Disconnect the http url connection
                    connection.disconnect();
                }


                String urlDelete = "http://10.5.5.9/gp/gpControl/command/storage/delete?p=" + url.substring(33);
                deleteActual = new Request.Builder()
                        .url(urlDelete)
                        .build();
                try {
                    response = client.newCall(deleteActual).execute();
                }
                catch(IOException e)
                {
                    Toast.makeText(getApplicationContext(),"Failure!", Toast.LENGTH_LONG).show();
                }

            }
            return null;
        }

        @Override
        protected void onPreExecute() {

            progressDialog = ProgressDialog.show(WaintingDownload.this,
                    "Wait", "Downloading Images");


        }

        @Override
        protected void onPostExecute(Void result) {
            progressDialog.dismiss();
            finish();


        }

    }
}
