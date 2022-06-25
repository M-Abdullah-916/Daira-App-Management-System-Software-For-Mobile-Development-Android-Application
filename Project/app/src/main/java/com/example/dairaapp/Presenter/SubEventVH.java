package com.example.dairaapp.Presenter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dairaapp.R;

public class SubEventVH extends RecyclerView.ViewHolder {
    public TextView name,date;
    ImageView imageView;

    public SubEventVH(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.subeventnamedetail);
        date = itemView.findViewById(R.id.subeventdatedetail);
        imageView = itemView.findViewById(R.id.subeventimg);

    }
}
