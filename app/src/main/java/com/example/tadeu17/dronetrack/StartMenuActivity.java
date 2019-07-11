package com.example.tadeu17.dronetrack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartMenuActivity extends AppCompatActivity {

    public static SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);


        sqLiteHelper = new SQLiteHelper(this, "Database.sqlite",null,1);

        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS PICTURE(Id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, location VARCHAR, observation VARCHAR, image BLOG)");



    }

    public void mudarEcra(View v){
        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }
}
