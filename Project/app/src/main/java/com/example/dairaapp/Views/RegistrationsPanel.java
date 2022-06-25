package com.example.dairaapp.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;

import com.example.dairaapp.R;

public class RegistrationsPanel extends AppCompatActivity {

    CardView mentorCard, participantCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrations_panel);

        mentorCard = findViewById(R.id.mentorRegCard);
        participantCard = findViewById(R.id.participantRegCard);
    }
}