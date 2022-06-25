package com.example.dairaapp.Presenter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dairaapp.R;

public class UpdateOcVH extends RecyclerView.ViewHolder{

    public TextView name,email,password;
    ConstraintLayout layoutId;

    public UpdateOcVH(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.ocnamedetail);
        email = itemView.findViewById(R.id.ocemaildetail);
        password = itemView.findViewById(R.id.ocpassworddetail);
        layoutId = itemView.findViewById(R.id.occardsingle);
    }
}
