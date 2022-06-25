package com.example.dairaapp.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.example.dairaapp.R;
import com.example.dairaapp.SplashScreen;

public class Dashboard extends AppCompatActivity {

    CardView adminCard,mentorCard,committeCard,participantCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        adminCard = findViewById(R.id.admincard);
        mentorCard = findViewById(R.id.mentorcard);
        committeCard = findViewById(R.id.committecard);
        participantCard = findViewById(R.id.participantcard);

        adminCard.setOnClickListener(v -> {
            Intent intent = new Intent(Dashboard.this, AdminLogin.class);
            startActivity(intent);
        });

        mentorCard.setOnClickListener(v -> {
            Intent intent = new Intent(Dashboard.this, MentorMenu.class);
            startActivity(intent);
        });

        committeCard.setOnClickListener(v -> {
            Intent intent = new Intent(Dashboard.this, SplashScreen.class);
            startActivity(intent);
        });

        participantCard.setOnClickListener(v -> {
            Intent intent = new Intent(Dashboard.this, SplashScreen.class);
            startActivity(intent);
        });
    }
}