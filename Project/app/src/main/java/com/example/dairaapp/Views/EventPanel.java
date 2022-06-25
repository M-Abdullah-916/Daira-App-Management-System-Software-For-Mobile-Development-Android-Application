package com.example.dairaapp.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.dairaapp.Presenter.RecyclerViewShowEvent;
import com.example.dairaapp.Presenter.RecyclerViewUpdateEvent;
import com.example.dairaapp.R;

public class EventPanel extends AppCompatActivity {

    CardView addEvent, updateEvent, readEvent, deleteEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_panel);

        addEvent = findViewById(R.id.addEventCard);
        updateEvent = findViewById(R.id.updateEventCard);
        readEvent = findViewById(R.id.readEventCard);
        deleteEvent = findViewById(R.id.deleteEventCard);

        addEvent.setOnClickListener(v -> {
            Intent intent = new Intent(EventPanel.this,AddEvent.class);
            startActivity(intent);
        });

        readEvent.setOnClickListener(v -> {
            Intent intent = new Intent(EventPanel.this, RecyclerViewShowEvent.class);
            startActivity(intent);
        });

        updateEvent.setOnClickListener(v -> {
            Intent intent = new Intent(EventPanel.this, RecyclerViewUpdateEvent.class);
            startActivity(intent);
        });

        deleteEvent.setOnClickListener(v -> {
            Intent intent = new Intent(EventPanel.this, DeleteEvent.class);
            startActivity(intent);
        });
    }

    public void GoBack(View view) {
        Intent intent = new Intent(EventPanel.this, AdminMenu.class);
        startActivity(intent);
    }
}