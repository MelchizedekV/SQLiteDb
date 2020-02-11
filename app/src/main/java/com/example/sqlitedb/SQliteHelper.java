package com.example.sqlitedb;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SQliteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "DataBase";
    public static final String TABLE_NAME = "SampleData";
    public static final int VERSION = 3;
    public static final String USER_ID = "_id";
    public static final String USER_NAME = "Name";
    public static final String PHONE_NO = "PHONENO";

    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
            " ("+USER_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+USER_NAME+" VARCHAR(255) ,"+ PHONE_NO+" VARCHAR(225));";

    private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;


    SQliteHelper(Context context)
    {
        super(context,DATABASE_NAME,null,VERSION);

    }





    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        sqLiteDatabase.execSQL(CREATE_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        // onUpdate need to be called whenever database version is changing

          sqLiteDatabase.execSQL(DROP_TABLE);

          onCreate(sqLiteDatabase);


    }
}
