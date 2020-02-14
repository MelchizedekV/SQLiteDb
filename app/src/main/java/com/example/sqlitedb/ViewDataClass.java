package com.example.sqlitedb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ViewDataClass extends AppCompatActivity {

    DbAdapter dbAdapter;
    Cursor cursor;
    RecyclerView recyclerView;
    List<NameListPojo> nameList =new ArrayList<>();
    Button delete;
    String name;
    String Phno;
    SQliteHelper sQliteHelper;
    RecyclerViewAdapter recyclerViewAdapter;
    Locale locale;
    Configuration config;
    SharedPreferences sharedPreferences;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data_class);

        dbAdapter=new DbAdapter(ViewDataClass.this);
        recyclerView =findViewById(R.id.namesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        delete=findViewById(R.id.deleteBtn);
        sQliteHelper=new SQliteHelper(this);
        sharedPreferences =getSharedPreferences(this.getPackageName(),MODE_PRIVATE);



        ViewDataDb();






        deleteBtnListner();





    }

    private void changeLanguage() {

        locale =new  Locale("hi");
        Locale.setDefault(locale);
        config = new Configuration();
        if (Build.VERSION.SDK_INT >= 19) {
            config.setLocale(locale);
        } else {
            config.locale = locale;
        }
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        Locale.setDefault(locale);


        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putString("languyage","hi");
        editor.commit();
    }

    public void ViewDataDb(){
       cursor =dbAdapter.getData();
       nameList.clear();
       while (cursor.moveToNext())
       {
           name =cursor.getString(cursor.getColumnIndex(SQliteHelper.USER_NAME));
           Phno =cursor.getString(cursor.getColumnIndex(SQliteHelper.PHONE_NO));

           NameListPojo nameListPojo =new NameListPojo();
           nameListPojo.setName(name);
           nameListPojo.setPhno(Phno);


           nameList.add(nameListPojo);


       }
       recyclerViewAdapter =new RecyclerViewAdapter(this,nameList);
       recyclerView.setAdapter(recyclerViewAdapter);
       recyclerViewAdapter.notifyDataSetChanged();
   }


    private void deleteBtnListner() {

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                deleteData();

               changeLanguage();

            }
        });

    }

    public void deleteData(){

         SQLiteDatabase sqLiteDatabase =sQliteHelper.getWritableDatabase();

         String[] whereArgs = {nameList.get(recyclerViewAdapter.clickedPosition).name};

         sqLiteDatabase.delete(SQliteHelper.TABLE_NAME,SQliteHelper.USER_NAME+" = ?",whereArgs);

         ViewDataDb();



    }
}
