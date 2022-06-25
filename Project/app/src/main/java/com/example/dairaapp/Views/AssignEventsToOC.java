package com.example.dairaapp.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.dairaapp.Model.Event;
import com.example.dairaapp.Presenter.DAOEvent;
import com.example.dairaapp.Presenter.DAOMentor;
import com.example.dairaapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.UUID;

public class AssignEventsToOC extends AppCompatActivity {

    EditText eventName,ocName,assignDate;
    String event, oc, date;
    Button addBtn;
    ProgressBar pb;
    boolean status = false;

    private FirebaseStorage storage;
    private StorageReference storageReference;

    DAOMentor dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_events_to_oc);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        eventName = findViewById(R.id.assignoceventname);
        ocName = findViewById(R.id.assignocname);
        assignDate = findViewById(R.id.assignoceventdate);

        addBtn = findViewById(R.id.addeventbtn);
        pb = findViewById(R.id.addeventpb);
        dao = new DAOMentor();

        addBtn.setOnClickListener(v -> {
            assignEvent();
        });
    }

    private void assignEvent() {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading Image");

        event = eventName.getText().toString().trim();
        oc = ocName.getText().toString().trim();
        date = assignDate.getText().toString().trim();

        if(validate(eventName,ocName,assignDate)){
            HashMap<String,String> values = new HashMap<>();
            values.put("K1",event);
            values.put("K2",oc);
            values.put("K3",date);
            dao.assignEvents(values);
            Toast.makeText(this, "Event Assigned", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,MentorMenu.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Event Not Assigned", Toast.LENGTH_SHORT).show();
        }


    }
    private boolean validate(EditText name,EditText description,EditText date) {
        String nameVal = name.getText().toString();
        String descriptionVal = description.getText().toString();
        String dateVal = date.getText().toString();

        if(nameVal.isEmpty()){
            name.setError("Please enter the name");
            name.requestFocus();
            return false;
        }
        if(descriptionVal.isEmpty()){
            description.setError("Please enter the description");
            description.requestFocus();
            return false;
        }
        if(dateVal.isEmpty()){
            date.setError("Please enter the date");
            date.requestFocus();
            return false;
        }
        return true;
    }
}