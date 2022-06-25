package com.example.dairaapp.Presenter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dairaapp.R;

public class DeleteSubEventVH extends RecyclerView.ViewHolder {
    public TextView name,date;
    ImageView imageView;
    ConstraintLayout layoutId;

    public DeleteSubEventVH(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.subeventnamedetail);
        date = itemView.findViewById(R.id.subeventdatedetail);
        imageView = itemView.findViewById(R.id.subeventimg);
        layoutId = itemView.findViewById(R.id.subeventitem);
    }
}
