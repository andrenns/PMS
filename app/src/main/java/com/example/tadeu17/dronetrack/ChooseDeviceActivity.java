package com.example.tadeu17.dronetrack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


public class ChooseDeviceActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private int item_selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_device);

        Spinner spinner = findViewById(R.id.camera_spinner);
        spinner.setOnItemSelectedListener(this);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.camera_options, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       item_selected = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void buttonStartConnection (View v)
    {
        if(item_selected==0)
        {
            Intent myIntent = new Intent(this, CheckConnectionActivity.class);
            myIntent.putExtra("item_selected", item_selected);
            startActivity(myIntent);
            finish();
        }
        else if(item_selected==1)
        {
            Intent myIntent = new Intent(this, CheckConnectionActivity.class);
            myIntent.putExtra("item_selected", item_selected);
            startActivity(myIntent);
            finish();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Please select an option to continue", Toast.LENGTH_LONG);
        }
    }

}
