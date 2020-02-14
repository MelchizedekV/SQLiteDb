package com.example.sqlitedb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button add_data,viewBtn,update;
    DbAdapter dbAdapter;
    EditText nameEdtxt,PhnoEdtxt;
    String name,Phno,updateName;
    Locale locale;
    Configuration config;

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add_data=findViewById(R.id.add_data);
        nameEdtxt=findViewById(R.id.name_ed_txt);
        PhnoEdtxt=findViewById(R.id.phn_edt_txt);
        viewBtn=findViewById(R.id.viewBtn);
        update =findViewById(R.id.update);
        sharedPreferences =getSharedPreferences(this.getPackageName(),MODE_PRIVATE);

         String language = sharedPreferences.getString("language","");

         changeLanguage(language);
         dbAdapter=new DbAdapter(this);
         insertDataListner();
         viewBtnListner();
         updateBtnListner();
    }

    private void updateBtnListner()
    {

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updateName =nameEdtxt.getText().toString();
                int id = dbAdapter.updateData(updateName);

                if(id <= 0){
                    Toast.makeText(MainActivity.this,id+"Data is not changed successfully",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,id+"Data is  changed successfully",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void changeLanguage(String language)
    {
        locale =new Locale(language);
        Locale.setDefault(locale);
        config = new Configuration();
        if (Build.VERSION.SDK_INT >= 19) {
            config.setLocale(locale);
        } else {
            config.locale = locale;
        }
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        Locale.setDefault(locale);

    }

    private void viewBtnListner()
    {
        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent navigate =new Intent(MainActivity.this,ViewDataClass.class);
                startActivity(navigate);


                      }
        });
    }

    private void insertDataListner()
    {
        add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=nameEdtxt.getText().toString();
                Phno=PhnoEdtxt.getText().toString();
                Long id= dbAdapter.insetData(name,Phno);

                if(id <= 0){
                    Toast.makeText(MainActivity.this,id+"Data is not inserted successfully",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,id+"Data is  inserted successfully",Toast.LENGTH_LONG).show();
                }



            }
        });
    }
}
