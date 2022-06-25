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

import com.example.dairaapp.Model.Event;
import com.example.dairaapp.Model.OC;
import com.example.dairaapp.Model.SubEvent;
import com.example.dairaapp.R;
import com.example.dairaapp.Views.UpdateOc;
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

public class OCUpdateAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    ArrayList<OC> list = new ArrayList<>();
    DAOOc dao;
    private StorageReference reference;
    DatabaseReference dbReference;

    public OCUpdateAdapter(Context context) {
        this.context = context;
    }

    public void setItems(ArrayList<OC> oc){
        list.addAll(oc);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.occardsingle,parent,false);
        return new UpdateOcVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        UpdateOcVH vh = (UpdateOcVH) holder;
        OC event = list.get(position);
        dao = new DAOOc();
        vh.name.setText(event.getName());
        vh.email.setText(event.getEmail());
        vh.password.setText(event.getPassword());
        dbReference = FirebaseDatabase.getInstance().getReference("OCs");

        vh.layoutId.setOnClickListener(v->{
            Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.updateocitemlayout);

            EditText editName = dialog.findViewById(R.id.ocupdatename);
            EditText editEmail = dialog.findViewById(R.id.ocupdateemail);
            EditText editPassword = dialog.findViewById(R.id.ocupdatepassword);
            Button updateBtn = dialog.findViewById(R.id.ocupdatebutton);

            editName.setText(list.get(position).getName());
            editEmail.setText(list.get(position).getEmail());
            editPassword.setText(list.get(position).getPassword());


            updateBtn.setOnClickListener(w->{
                String ocName="";
                String ocEmail="";
                String ocPassword="";

                if(!editName.getText().toString().equals(""))
                    ocName = editName.getText().toString();
                else
                    editName.setError("Enter an OC Name");

                if(!editEmail.getText().toString().equals(""))
                    ocEmail = editEmail.getText().toString();
                else
                    editEmail.setError("Enter Email");

                if(!editPassword.getText().toString().equals(""))
                    ocPassword = editPassword.getText().toString();
                else
                    editPassword.setError("Enter a Password");

                OC updatedEvent = new OC();
                updatedEvent.setName(ocName);
                updatedEvent.setEmail(ocEmail);
                updatedEvent.setPassword(ocPassword);

                dbReference.child(list.get(position).getKey()).child("OcName").setValue(ocName);
                dbReference.child(list.get(position).getKey()).child("OcEmail").setValue(ocEmail);
                dbReference.child(list.get(position).getKey()).child("OcPassword").setValue(ocPassword);

            });

            dialog.show();

        });



    }
    @Override
    public int getItemCount() {
        return list.size();
    }
}


