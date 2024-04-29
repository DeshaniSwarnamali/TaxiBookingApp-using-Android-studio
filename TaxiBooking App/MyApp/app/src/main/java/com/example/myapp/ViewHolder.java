package com.example.myapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {

   public TextView nametext,agetext;
    public ViewHolder(@NonNull View itemview){
        super(itemview);
        nametext = itemview.findViewById(R.id.textNameId);
        agetext = itemview.findViewById(R.id.textAgeId);


    }

}
