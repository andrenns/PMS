package com.example.tadeu17.dronetrack;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class SQLiteHelper extends SQLiteOpenHelper {


    public SQLiteHelper(@androidx.annotation.Nullable Context context, @androidx.annotation.Nullable String name, @androidx.annotation.Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    public void queryData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        database.execSQL(sql);
    }


    public void insertData(int user_id,String name, String location, String observation, byte[] image){
        SQLiteDatabase database = getReadableDatabase();
        String sql = "INSERT INTO PICTURE VALUES (?, ?, ?, ?, ?)";

        SQLiteStatement statement = database.compileStatement (sql);
        statement.clearBindings();

        statement.bindLong(0,user_id);
        statement.bindString(1,name);
        statement.bindString(2,location);
        statement.bindString(3,observation);
        statement.bindBlob(4, image);
        statement.executeInsert();



    }

    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);

    }




    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
