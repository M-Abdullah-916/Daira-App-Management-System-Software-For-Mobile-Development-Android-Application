package com.example.dairaapp.Presenter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dairaapp.Model.Mentor;
import com.example.dairaapp.Model.OC;
import com.example.dairaapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class OCDeleteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    ArrayList<OC> list = new ArrayList<>();
    DAOOc dao;
    DatabaseReference dbReference;

    public OCDeleteAdapter(Context context) {
        this.context = context;
    }

    public void setItems(ArrayList<OC> oc){
        list.addAll(oc);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.occardsingle,parent,false);
        return new DeleteOcVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        DeleteOcVH vh = (DeleteOcVH) holder;
        OC oc = list.get(position);
        dao = new DAOOc();
        vh.name.setText(oc.getName());
        vh.email.setText(oc.getEmail());
        vh.password.setText(oc.getPassword());

        dbReference = FirebaseDatabase.getInstance().getReference("Users").child("OCs");

        vh.layoutId.setOnClickListener(v->{
            AlertDialog.Builder builder = new AlertDialog.Builder(((DeleteOcVH) holder).name.getContext());
            builder.setTitle("Are you Sure?");
            builder.setMessage("Deleted Data can't be undo");

            int index = position;

            builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    FirebaseDatabase.getInstance().getReference("Users").child("OCs").child(list.get(index).getKey()).removeValue();

                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(context, "Cancelled", Toast.LENGTH_SHORT).show();
                }
            });
            builder.show();
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}

