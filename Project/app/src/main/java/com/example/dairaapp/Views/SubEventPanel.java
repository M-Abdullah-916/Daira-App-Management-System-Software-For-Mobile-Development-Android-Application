package com.example.dairaapp.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.dairaapp.Presenter.RecyclerViewShowEvent;
import com.example.dairaapp.Presenter.RecyclerViewShowSubEvent;
import com.example.dairaapp.Presenter.RecyclerViewUpdateEvent;
import com.example.dairaapp.Presenter.RecyclerViewUpdateSubEvent;
import com.example.dairaapp.R;

public class SubEventPanel extends AppCompatActivity {

    CardView addSubEvent, updateSubEvent, readSubEvent, deleteSubEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_event_panel);

        addSubEvent = findViewById(R.id.addSubEventCard);
        updateSubEvent = findViewById(R.id.updateSubEventCard);
        readSubEvent = findViewById(R.id.readSubEventCard);
        deleteSubEvent = findViewById(R.id.deleteSubEventCard);

        addSubEvent.setOnClickListener(v -> {
            Intent intent = new Intent(SubEventPanel.this,AddSubEvent.class);
            startActivity(intent);
        });

        readSubEvent.setOnClickListener(v -> {
            Intent intent = new Intent(SubEventPanel.this, RecyclerViewShowSubEvent.class);
            startActivity(intent);
        });

        updateSubEvent.setOnClickListener(v -> {
            Intent intent = new Intent(SubEventPanel.this, RecyclerViewUpdateSubEvent.class);
            startActivity(intent);
        });

        deleteSubEvent.setOnClickListener(v -> {
            Intent intent = new Intent(SubEventPanel.this, DeleteSubEvent.class);
            startActivity(intent);
        });
    }

    public void GoBack(View view) {
        Intent intent = new Intent(SubEventPanel.this, MentorMenu.class);
        startActivity(intent);
    }
}