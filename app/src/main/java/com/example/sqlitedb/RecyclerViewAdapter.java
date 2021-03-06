package com.example.sqlitedb;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    List<NameListPojo> nameList;
    Activity activity;
    static int clickedPosition =-1;

    public RecyclerViewAdapter(Activity activity, List<NameListPojo> nameList) {

       this.nameList =nameList;
       this.activity =activity;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View view = layoutInflater.inflate(R.layout.name_list_single_view, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

//        holder.name.setText(nameList.get(position).name);
        holder.Phno.setText(nameList.get(position).Phno);

        holder.chckBox.setText(nameList.get(position).name);


        if (clickedPosition == position)
            holder.chckBox.setChecked(true);
        else
            holder.chckBox.setChecked(false);

    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,Phno;
        CheckBox chckBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            Phno =itemView.findViewById(R.id.phneTxt);
            chckBox=itemView.findViewById(R.id.chckBox);

            chckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                    clickedPosition = getAdapterPosition();


                }
            });
        }
    }
}
