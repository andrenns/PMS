package com.example.tadeu17.dronetrack;

import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DownloadListPhotos {

    private ArrayList<String> listaFotos = new ArrayList<>();

    public void jsonParse(RequestQueue mQueue){

        String url = "http://10.5.5.9/gp/gpMediaList";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("media");
                    JSONObject media = jsonArray.getJSONObject(0);
                    JSONArray jsonArray1 = media.getJSONArray("fs");

                    for(int i=0; i<jsonArray1.length(); i++){
                        JSONObject fotos = jsonArray1.getJSONObject(i);

                        String fotoNome =  fotos.getString("n");
                        String jpg = ".JPG";
                        if(fotoNome.endsWith(jpg)) {
                            listaFotos.add(fotoNome);

                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }

    public void writeNPhotos(TextView mListaTexto){

        for(int i=0; i<listaFotos.size();i++) {
            mListaTexto.append(listaFotos.get(i) + "\n");

        }
    }

    public ArrayList<String> getListaFotos() {
        return listaFotos;
    }

}
