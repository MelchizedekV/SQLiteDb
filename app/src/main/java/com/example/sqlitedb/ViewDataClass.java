package com.example.sqlitedb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class ViewDataClass extends AppCompatActivity {

    DbAdapter dbAdapter;
    Cursor cursor;
    RecyclerView recyclerView;
    List<NameListPojo> nameList =new ArrayList<>();
    Button delete;
    String name;
    String Phno;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data_class);

        dbAdapter=new DbAdapter(ViewDataClass.this);
        recyclerView =findViewById(R.id.namesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        delete=findViewById(R.id.deleteBtn);

        cursor =dbAdapter.getData();

        while (cursor.moveToNext())
        {
            name =cursor.getString(cursor.getColumnIndex(SQliteHelper.USER_NAME));
            Phno =cursor.getString(cursor.getColumnIndex(SQliteHelper.PHONE_NO));

            NameListPojo nameListPojo =new NameListPojo();
            nameListPojo.setName(name);
            nameListPojo.setPhno(Phno);


            nameList.add(nameListPojo);
        }

        RecyclerViewAdapter recyclerViewAdapter =new RecyclerViewAdapter(this,nameList);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();


        deleteBtnListner();





    }

    private void deleteBtnListner() {

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                

            }
        });

    }
}
