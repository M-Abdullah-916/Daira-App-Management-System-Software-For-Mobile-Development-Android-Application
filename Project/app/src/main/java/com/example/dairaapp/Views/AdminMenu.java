package com.example.dairaapp.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

import com.example.dairaapp.R;

public class AdminMenu extends AppCompatActivity {

    CardView eventsCard, manageMentorCard, assignEventsCard, registrationsCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);

        eventsCard = findViewById(R.id.eventscard);
        manageMentorCard = findViewById(R.id.managementorcard);
        assignEventsCard = findViewById(R.id.assigneventscard);
        registrationsCard = findViewById(R.id.viewregcard);

        eventsCard.setOnClickListener(v -> {
            Intent intent = new Intent(AdminMenu.this,EventPanel.class);
            startActivity(intent);
        });

        manageMentorCard.setOnClickListener(v -> {
            Intent intent = new Intent(AdminMenu.this,MentorPanel.class);
            startActivity(intent);
        });

    }
}