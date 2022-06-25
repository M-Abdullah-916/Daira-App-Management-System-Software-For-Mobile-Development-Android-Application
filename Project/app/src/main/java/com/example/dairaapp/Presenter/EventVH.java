package com.example.dairaapp.Presenter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dairaapp.R;

public class EventVH extends RecyclerView.ViewHolder {

    public TextView name,date;
    ImageView imageView;

    public EventVH(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.eventnamedetail);
        date = itemView.findViewById(R.id.eventdatedetail);
        imageView = itemView.findViewById(R.id.eventimg);

    }
}
