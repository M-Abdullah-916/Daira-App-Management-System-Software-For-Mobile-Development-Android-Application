package com.example.dairaapp.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

import com.example.dairaapp.R;

public class MentorMenu extends AppCompatActivity {

    CardView subEventsCard, manageOCCard, ocAssignEventsCard, mentorRegistrationsCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_menu);

        subEventsCard = findViewById(R.id.subeventscard);
        manageOCCard = findViewById(R.id.ocmanagementorcard);
        ocAssignEventsCard = findViewById(R.id.ocassigneventscard);
        mentorRegistrationsCard = findViewById(R.id.mentorviewregcard);

        subEventsCard.setOnClickListener(v -> {
            Intent intent = new Intent(MentorMenu.this,SubEventPanel.class);
            startActivity(intent);
        });

        manageOCCard.setOnClickListener(v -> {
            Intent intent = new Intent(MentorMenu.this,OCPanel.class);
            startActivity(intent);
        });

        ocAssignEventsCard.setOnClickListener(v -> {
            Intent intent = new Intent(MentorMenu.this,AssignEventsToOC.class);
            startActivity(intent);
        });
    }
}