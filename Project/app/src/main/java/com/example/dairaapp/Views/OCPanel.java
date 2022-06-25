package com.example.dairaapp.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

import com.example.dairaapp.R;

public class OCPanel extends AppCompatActivity {

    CardView addOcCard, updateOcCard, readOcCard, deleteOcCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocpanel);

        addOcCard = findViewById(R.id.addMentorCard);
        updateOcCard = findViewById(R.id.updateMentorCard);
        readOcCard = findViewById(R.id.readMentorCard);
        deleteOcCard = findViewById(R.id.deleteMentorCard);

        addOcCard.setOnClickListener(v->{
            Intent intent = new Intent(OCPanel.this,AddOC.class);
            startActivity(intent);
        });

        readOcCard.setOnClickListener(v->{
            Intent intent = new Intent(OCPanel.this,ShowOc.class);
            startActivity(intent);
        });

        deleteOcCard.setOnClickListener(v->{
            Intent intent = new Intent(OCPanel.this,DeleteOC.class);
            startActivity(intent);
        });

        updateOcCard.setOnClickListener(v->{
            Intent intent = new Intent(OCPanel.this,UpdateOc.class);
            startActivity(intent);
        });
    }


}