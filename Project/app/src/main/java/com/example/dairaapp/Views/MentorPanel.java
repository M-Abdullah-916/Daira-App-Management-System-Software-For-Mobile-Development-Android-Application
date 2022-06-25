package com.example.dairaapp.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

import com.example.dairaapp.Model.Mentor;
import com.example.dairaapp.R;

public class MentorPanel extends AppCompatActivity {

    CardView addMentorCard, updateMentorCard, readMentorCard, deleteMentorCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_panel);

        addMentorCard = findViewById(R.id.addMentorCard);
        updateMentorCard = findViewById(R.id.updateMentorCard);
        readMentorCard = findViewById(R.id.readMentorCard);
        deleteMentorCard = findViewById(R.id.deleteMentorCard);

        addMentorCard.setOnClickListener(v->{
            Intent intent = new Intent(MentorPanel.this,AddMentor.class);
            startActivity(intent);
        });

        readMentorCard.setOnClickListener(v->{
            Intent intent = new Intent(MentorPanel.this,ShowMentor.class);
            startActivity(intent);
        });


    }


}