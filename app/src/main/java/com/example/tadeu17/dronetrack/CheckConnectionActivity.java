package com.example.tadeu17.dronetrack;


import android.content.Context;
import android.content.Intent;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CheckConnectionActivity extends AppCompatActivity {

    private Context context;
    String ssid;
    String ssidc;
    TextView connected_Text;
    WifiManager wifiManager;
    WifiInfo wifiInfo;
    EditText camera_ssid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_connection);
        camera_ssid = findViewById(R.id.camera_ssid);

        connected_Text = findViewById(R.id.device_selected);
        Intent mIntent = getIntent();
        int item_selected = mIntent.getIntExtra("item_selected", 0);

        if(item_selected==0)
        {
            ssidc ="GP54775119";
        }
        else if(item_selected==1)
        {
            ssidc = "NOMERASP";
        }

        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        wifiInfo = wifiManager.getConnectionInfo();
        if (wifiInfo.getSupplicantState() == SupplicantState.COMPLETED) {
            ssid = wifiInfo.getSSID().replace("SSID: ","").replaceAll("\"","");
        }


        if(ssid!=null) {
            if (ssid.equals(ssidc))
            {
                connected_Text.setText("Connected Sucessfuly");
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.update_connection_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id==R.id.update_connection)
        {
            wifiInfo = wifiManager.getConnectionInfo();
            if (wifiInfo.getSupplicantState() == SupplicantState.COMPLETED) {
                ssid = wifiInfo.getSSID().replace("SSID: ","").replaceAll("\"","");

            }

            if(ssid!=null)
            {
                if (ssid.equals(ssidc))
                {
                    connected_Text.setText("Connected Sucessfuly");
                }
                else
                {
                    connected_Text.setText("Not connected yet");
                }
            }
            else
            {
                connected_Text.setText("Not connected yet");
            }

        }
        return super.onOptionsItemSelected(item);
    }


    public void buttonStartClean(View v)
    {



        if(ssid!=null)
        {
            if (ssid.equals(ssidc))
            {
                startActivity(new Intent(this, MainMenuActivity.class));
                finish();
            }
        }
    }

    public void updateCameraName(View v)
    {

        ssidc = camera_ssid.getText().toString();

    }



}
