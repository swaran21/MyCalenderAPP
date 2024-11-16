package com.example.mycalenderapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class EventActivity extends AppCompatActivity {

    private EditText eventTitle;
    private EditText eventDescription;
    private TimePicker eventTimePicker;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.event);

        eventDescription = findViewById(R.id.eventDescription);
        TextView eventDateText = findViewById(R.id.eventDateText);
        eventTitle = findViewById(R.id.eventTitle);
        eventTimePicker = findViewById(R.id.eventTimePicker);
        Button saveEventButton = findViewById(R.id.saveEventButton);

        String selectedDate = getIntent().getStringExtra("selectedDate");
        if(selectedDate!=null){
            eventDateText.setText("Date: "+selectedDate);
        }
        saveEventButton.setOnClickListener(view -> saveEvent());

    }

    private void saveEvent() {
        String title = eventTitle.getText().toString();
        String description = eventDescription.getText().toString();
        int hour = eventTimePicker.getHour();
        int minute = eventTimePicker.getMinute();

        if(title.isEmpty() || description.isEmpty()){
            Toast.makeText(this, "Fill all the content", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "Event saved on time "+hour+" hrs "+minute+" minutes", Toast.LENGTH_SHORT).show();


    }
}
