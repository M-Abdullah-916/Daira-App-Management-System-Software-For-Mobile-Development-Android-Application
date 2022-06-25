package com.example.dairaapp.Presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dairaapp.Model.SubEvent;
import com.example.dairaapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SubEventListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    ArrayList<SubEvent> list = new ArrayList<>();
    private StorageReference reference;

    public SubEventListAdapter(Context context){
        this.context = context;
    }

    public void setItems(ArrayList<SubEvent> event){
        list.addAll(event);
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.subeventitem,parent,false);
        return new SubEventVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        SubEventVH vh = (SubEventVH) holder;
        SubEvent event = list.get(position);
        vh.name.setText(event.getSubEventName());
        vh.date.setText(event.getSubEventParent());
        try {
            Log.d("TAG",event.getSubImageKey());
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


