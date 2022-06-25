package com.example.dairaapp.Presenter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

public class SubEventUpdateAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    ArrayList<SubEvent> list = new ArrayList<>();
    DAOSubEvent dao;
    private StorageReference reference;
    DatabaseReference dbReference;

    public SubEventUpdateAdapter(Context context) {
        this.context = context;
    }

    public void setItems(ArrayList<SubEvent> event){
        list.addAll(event);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.subeventitem,parent,false);
        return new UpdateSubEventVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        UpdateSubEventVH vh = (UpdateSubEventVH) holder;
        SubEvent event = list.get(position);
        dao = new DAOSubEvent();
        vh.name.setText(event.getSubEventName());
        vh.date.setText(event.getSubEventParent());
        dbReference = FirebaseDatabase.getInstance().getReference("SubEvents");

        vh.layoutId.setOnClickListener(v->{
            Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.updatesubeventitemlayout);

            EditText editName = dialog.findViewById(R.id.subeventupdatename);
            EditText editDescription = dialog.findViewById(R.id.subeventupdatedesc);
            EditText editDate = dialog.findViewById(R.id.subeventupdatedate);
            ImageView imageView = dialog.findViewById(R.id.subeventimageupdate);
            Button updateBtn = dialog.findViewById(R.id.subupdateeventbtn);

            editName.setText(list.get(position).getSubEventName());
            editDescription.setText(list.get(position).getSubEventDesc());
            editDate.setText(list.get(position).getSubEventParent());
            setImage(position,imageView);

            updateBtn.setOnClickListener(w->{
                String eventName="";
                String eventDesc="";
                String eventDate="";

                if(!editName.getText().toString().equals(""))
                    eventName = editName.getText().toString();
                else
                    editName.setError("Enter a Sub-Event Name");

                if(!editDescription.getText().toString().equals(""))
                    eventDesc = editDescription.getText().toString();
                else
                    editDescription.setError("Enter Description");

                if(!editDate.getText().toString().equals(""))
                    eventDate = editDate.getText().toString();
                else
                    editDate.setError("Enter a Date");

                SubEvent updatedEvent = new SubEvent();
                updatedEvent.setSubEventName(eventName);
                updatedEvent.setSubEventDesc(eventDesc);
                updatedEvent.setSubEventParent(eventDate);

                dbReference.child(list.get(position).getKey()).child("subEventName").setValue(eventName);
                dbReference.child(list.get(position).getKey()).child("subEventDesc").setValue(eventDesc);
                dbReference.child(list.get(position).getKey()).child("subEventDate").setValue(eventDate);

            });

            dialog.show();

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

    public void setImage(int position,ImageView imageView){
        try {
            final File localFile = File.createTempFile(list.get(position).getSubImageKey(),"jpeg");
            reference = FirebaseStorage.getInstance().getReference().child("images/" + list.get(position).getSubImageKey());
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

