package com.example.dairaapp.Presenter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dairaapp.Model.Event;
import com.example.dairaapp.Model.Mentor;
import com.example.dairaapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MentorDeleteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    ArrayList<Mentor> list = new ArrayList<>();
    DAOMentor dao;
    DatabaseReference dbReference;

    public MentorDeleteAdapter(Context context) {
        this.context = context;
    }

    public void setItems(ArrayList<Mentor> mentor){
        list.addAll(mentor);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.singlementor,parent,false);
        return new DeleteMentorVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        DeleteMentorVH vh = (DeleteMentorVH) holder;
        Mentor mentor = list.get(position);
        dao = new DAOMentor();
        vh.name.setText(mentor.getName());
        vh.email.setText(mentor.getEmail());
        vh.password.setText(mentor.getPassword());

        dbReference = FirebaseDatabase.getInstance().getReference("Users").child("Mentors");

        vh.layoutId.setOnClickListener(v->{
            AlertDialog.Builder builder = new AlertDialog.Builder(((DeleteMentorVH) holder).name.getContext());
            builder.setTitle("Are you Sure?");
            builder.setMessage("Deleted Data can't be undo");

            int index = position;

            builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    FirebaseDatabase.getInstance().getReference("Users").child("Mentors").child(list.get(index).getKey()).removeValue();

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
