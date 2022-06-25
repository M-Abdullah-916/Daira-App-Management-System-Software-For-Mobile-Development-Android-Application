package com.example.dairaapp.Presenter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dairaapp.Model.Event;
import com.example.dairaapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class EventUpdateAdpater extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private Context context;
    ArrayList<Event> list = new ArrayList<>();
    DAOEvent dao;
    private StorageReference reference;
    DatabaseReference dbReference;

    public EventUpdateAdpater(Context context) {
        this.context = context;
    }

    public void setItems(ArrayList<Event> event){
        list.addAll(event);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.eventitem,parent,false);
        return new UpdateEventVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        UpdateEventVH vh = (UpdateEventVH) holder;
        Event event = list.get(position);
        dao = new DAOEvent();
        vh.name.setText(event.getEventName());
        vh.date.setText(event.getEventDate());
        dbReference = FirebaseDatabase.getInstance().getReference("Events");

        vh.layoutId.setOnClickListener(v->{
            Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.updateeventitemlayout);

            EditText editName = dialog.findViewById(R.id.eventupdatename);
            EditText editDescription = dialog.findViewById(R.id.eventupdatedesc);
            EditText editDate = dialog.findViewById(R.id.eventupdatedate);
            ImageView imageView = dialog.findViewById(R.id.eventimageupdate);
            Button updateBtn = dialog.findViewById(R.id.updateeventbtn);

            editName.setText(list.get(position).getEventName());
            editDescription.setText(list.get(position).getEventDesc());
            editDate.setText(list.get(position).getEventDate());
            setImage(position,imageView);

            updateBtn.setOnClickListener(w->{
                String eventName="";
                String eventDesc="";
                String eventDate="";

                if(!editName.getText().toString().equals(""))
                    eventName = editName.getText().toString();
                else
                    editName.setError("Enter an Event Name");

                if(!editDescription.getText().toString().equals(""))
                    eventDesc = editDescription.getText().toString();
                else
                    editDescription.setError("Enter Description");

                if(!editDate.getText().toString().equals(""))
                    eventDate = editDate.getText().toString();
                else
                    editDate.setError("Enter a Date");

                Event updatedEvent = new Event();
                updatedEvent.setEventName(eventName);
                updatedEvent.setEventDesc(eventDesc);
                updatedEvent.setEventDate(eventDate);

                dbReference.child(list.get(position).getKey()).child("eventName").setValue(eventName);
                dbReference.child(list.get(position).getKey()).child("eventDesc").setValue(eventDesc);
                dbReference.child(list.get(position).getKey()).child("eventDate").setValue(eventDate);

            });

            dialog.show();

        });


        try {
            final File localFile = File.createTempFile(event.getImageKey(),"jpeg");
            reference = FirebaseStorage.getInstance().getReference().child("images/" + event.getImageKey());
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

    public void setImage(int position,ImageView imageView){
        try {
            final File localFile = File.createTempFile(list.get(position).getImageKey(),"jpeg");
            reference = FirebaseStorage.getInstance().getReference().child("images/" + list.get(position).getImageKey());
            reference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    imageView.setImageBitmap(bitmap);
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
