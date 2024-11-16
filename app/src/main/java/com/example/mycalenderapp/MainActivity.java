package com.example.mycalenderapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private CalendarView calendarView;
    private EditText dateInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        calendarView = findViewById(R.id.calendarView);
        dateInput = findViewById(R.id.dateInput);
        Button goToDateButton = findViewById(R.id.goToDateButton);

        goToDateButton.setOnClickListener(v -> {
            String strDate = dateInput.getText().toString();
            if (validDate(strDate)){
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
                try {
                    Date date = format.parse(strDate);
                    if (date != null) {
                        calendarView.setDate(date.getTime(), true, true);
                    }
                } catch (ParseException e) {
                    Toast.makeText(this, "Invalid format", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(this, "Invalid date", Toast.LENGTH_SHORT).show();
            }
        });

        // Inside MainActivity.java
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;

                // Open EventActivity with the selected date
                Intent intent = new Intent(MainActivity.this, EventActivity.class);
                intent.putExtra("selectedDate", selectedDate);
                startActivity(intent);
            }
        });



    }

    private boolean validDate(String strDate) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH);
        format.setLenient(false);
        try{
            Date date = format.parse(strDate);
            return  true;
        }
        catch (ParseException e){
            return false;
        }

    }
}