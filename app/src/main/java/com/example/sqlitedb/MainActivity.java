package com.example.sqlitedb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button add_data,viewBtn;
    DbAdapter dbAdapter;
    EditText nameEdtxt,PhnoEdtxt;
    String name,Phno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add_data=findViewById(R.id.add_data);
        nameEdtxt=findViewById(R.id.name_ed_txt);
        PhnoEdtxt=findViewById(R.id.phn_edt_txt);
        viewBtn=findViewById(R.id.viewBtn);



        dbAdapter=new DbAdapter(this);
        insertDataListner();
        viewBtnListner();
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
