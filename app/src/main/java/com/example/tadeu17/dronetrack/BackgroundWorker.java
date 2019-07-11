package com.example.tadeu17.dronetrack;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

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

public class BackgroundWorker extends AsyncTask<String,Void,String> {

    Context context;
    AlertDialog alertDialog;
    String type;
    EditText passwordEt, confPassEt;

    public BackgroundWorker(Context context,EditText passwordEt, EditText confPassEt)
    {
        this.passwordEt=passwordEt;
        this.confPassEt=confPassEt;
        this.context=context;
    }
    public BackgroundWorker(Context context)
    {
        this.context=context;
    }
    @Override
    protected String doInBackground(String... params) {

        type = params[0];
        String login_url = "http://drone.tigerwhale.com/login.php";
        String create_url = "http://drone.tigerwhale.com/create.php";

        if(type.equals("login"))
        {
            try
            {
                String user_name = params[1];
                String password = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
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
                return sb.toString().trim();
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }
        else if(type.equals("create")) {
            try {
                String name = params[1];
                String location = params[2];
                String age = params[3];
                String username = params[4];
                String password = params[5];

                URL url = new URL(create_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&"
                        + URLEncoder.encode("location", "UTF-8") + "=" + URLEncoder.encode(location, "UTF-8") + "&"
                        + URLEncoder.encode("age", "UTF-8") + "=" + URLEncoder.encode(age, "UTF-8") + "&"
                        + URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&"
                        + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                StringBuilder sb = new StringBuilder();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String json;
                while ((json = bufferedReader.readLine()) != null) {
                    sb.append(json + "\n");
                }
                httpURLConnection.disconnect();
                return sb.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
    }

    @Override
    protected void onPostExecute(String result) {
        if(type.equals("login")) {
            try {

                JSONArray jsonArray = new JSONArray(result);

                int id = 0;
                int login_status = 0;
                String name = "";

                JSONObject jsonObject = jsonArray.getJSONObject(0);
                id = jsonObject.getInt("id");


                login_status = jsonObject.getInt("login_status");

                if (login_status == 1) {
                    name = jsonObject.getString("name");
                    Globals g = Globals.getInstance();
                    g.setName(name);
                    g.setUserId(id);
                    Intent i = new Intent(context, WelcomeActivity.class);
                    Bundle extras = new Bundle();
                    extras.putString("name",name);
                    extras.putInt("flagMenu",1);
                    i.putExtras(extras);
                   // i.putExtra("name", name);
                    context.startActivity(i);

                } else {
                    alertDialog.setMessage("Login not sucessfull");
                    alertDialog.show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("create"))
        {
            try {

                JSONArray jsonArray = new JSONArray(result);
                JSONObject jsonObject = jsonArray.getJSONObject(0);
               int create_status = 0;

                 create_status = jsonObject.getInt("create_status");

                if(create_status==1)
                {
                    Toast.makeText(context,"Account created", Toast.LENGTH_LONG);
                    Intent i = new Intent(context, LoginActivity.class);
                    i.putExtra("create_status", create_status);
                    context.startActivity(i);
                    //context.startActivity(new Intent(context, LoginActivity.class));
                }
                else if(create_status == 0)
                {
                    alertDialog.setMessage("Username already in use");
                    alertDialog.show();
                    passwordEt.setText("");
                    confPassEt.setText("");
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
