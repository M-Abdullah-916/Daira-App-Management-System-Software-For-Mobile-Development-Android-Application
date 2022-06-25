package com.example.dairaapp.Views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.dairaapp.Model.Event;
import com.example.dairaapp.Presenter.DAOEvent;
import com.example.dairaapp.Presenter.RecyclerViewUpdateEvent;
import com.example.dairaapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class AddEvent extends AppCompatActivity {

    EditText eventName,eventDesc, eventDate;
    String name, description, date;
    Button addBtn;
    ImageView eventImage;
    ProgressBar pb;
    boolean status = false;

    public Uri imageUri;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    DAOEvent dao;
    Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        eventName = findViewById(R.id.eventname);
        eventDesc = findViewById(R.id.eventdesc);
        eventDate = findViewById(R.id.eventdate);

        addBtn = findViewById(R.id.addeventbtn);
        pb = findViewById(R.id.addeventpb);
        dao = new DAOEvent();

        eventImage = findViewById(R.id.eventimage);



        eventImage.setOnClickListener(v->{
            selectImage();
        });

        addBtn.setOnClickListener(v -> {
            uploadPicture();
        });

    }

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode== RESULT_OK && data!=null && data.getData()!=null){
            imageUri = data.getData();
            eventImage.setImageURI(imageUri);

        }
    }

    private void uploadPicture() {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading Image");

        name = eventName.getText().toString().trim();
        Log.d("TAG",name);
        description = eventDesc.getText().toString().trim();
        date = eventDate.getText().toString().trim();


        final String randomKey = UUID.randomUUID().toString();
        StorageReference riverref = storageReference.child("images/" + randomKey);

        if(imageUri != null){
            validate(eventName,eventDesc,eventDate);
            pd.show();
            riverref.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    pd.dismiss();
                    Snackbar.make(findViewById(R.id.eventimage),"Image Uploaded",Snackbar.LENGTH_LONG).show();
                    event = new Event(name,description,date,randomKey);

                    dao.add(event);
                    Toast.makeText(AddEvent.this, "Event Added", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddEvent.this,EventPanel.class);
                    startActivity(intent);
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddEvent.this, "Failed to Upload Image", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                            double progressPercent = (100.00 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                            pd.setMessage("Percentage: " + (int) progressPercent + "%");
                        }
                    });
        }

    }

    private void validate(EditText name,EditText description,EditText date) {
        String nameVal = name.getText().toString();
        String descriptionVal = description.getText().toString();
        String dateVal = date.getText().toString();

        if(nameVal.isEmpty()){
            name.setError("Please enter the name");
            name.requestFocus();
            return;
        }
        if(descriptionVal.isEmpty()){
            description.setError("Please enter the description");
            description.requestFocus();
            return;
        }
        if(dateVal.isEmpty()){
            date.setError("Please enter the date");
            date.requestFocus();
            return;
        }
    }

    public void GoBack(View view) {
        Intent intent = new Intent(AddEvent.this, EventPanel.class);
        startActivity(intent);
    }
}