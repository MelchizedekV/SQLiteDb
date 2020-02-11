package com.example.sqlitedb;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;

public class DbAdapter
{
    String name;
    String phNo;
    SQliteHelper sQliteHelper;

    public DbAdapter(Activity mainActivity) {

        sQliteHelper=new SQliteHelper(mainActivity);

    }


    public long  insetData(String name, String phno){


        SQLiteDatabase sqLiteDatabase =sQliteHelper.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put(sQliteHelper.USER_NAME,name);
        contentValues.put(sQliteHelper.PHONE_NO,phno);
        Long id =sqLiteDatabase.insert(sQliteHelper.TABLE_NAME,null,contentValues);
        return id;
    }


    public Cursor  getData(){

        SQLiteDatabase sqLiteDatabase =sQliteHelper.getWritableDatabase();
        String columns []={sQliteHelper.USER_ID,sQliteHelper.USER_NAME,sQliteHelper.PHONE_NO};
        Cursor cursor=sqLiteDatabase.query(sQliteHelper.TABLE_NAME,columns,null,null,null,null,null);


        return cursor;
    }





}
