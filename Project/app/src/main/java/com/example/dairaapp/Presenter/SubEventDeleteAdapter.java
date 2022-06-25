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
import com.example.dairaapp.Model.SubEvent;
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

public class SubEventDeleteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    ArrayList<SubEvent> list = new ArrayList<>();
    DAOSubEvent dao;
    private StorageReference reference;
    DatabaseReference dbReference;

    public SubEventDeleteAdapter(Context context) {
        this.context = context;
    }

    public void setItems(ArrayList<SubEvent> event){
        list.addAll(event);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.subeventitem,parent,false);
        return new DeleteSubEventVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        DeleteSubEventVH vh = (DeleteSubEventVH) holder;
        SubEvent event = list.get(position);
        dao = new DAOSubEvent();
        vh.name.setText(event.getSubEventName());
        vh.date.setText(event.getSubEventDate());
        dbReference = FirebaseDatabase.getInstance().getReference("SubEvents");

        vh.layoutId.setOnClickListener(v->{
            AlertDialog.Builder builder = new AlertDialog.Builder(((DeleteSubEventVH) holder).name.getContext());
            builder.setTitle("Are you Sure?");
            builder.setMessage("Deleted Data can't be undo");

            int index = position;

            builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    FirebaseDatabase.getInstance().getReference("SubEvents").child(list.get(index).getKey()).removeValue();

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


        try {
            final File localFile = File.createTempFile(event.getSubImageKey(),"jpeg");
            reference = FirebaseStorage.getInstance().getReference().child("images/" + event.getSubImageKey());
            reference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    vh.imageView.setImageBitmap(bitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}

