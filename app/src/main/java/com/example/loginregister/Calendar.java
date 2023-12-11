  package com.example.loginregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Calendar extends AppCompatActivity {

    private CalendarView calendarView;
    private EditText editText;
    private String stringDateSelected;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarView = findViewById(R.id.calendarView);
        editText=findViewById(R.id.editText);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int i, int i1, int i2) {
                stringDateSelected=Integer.toString(i)+Integer.toString(i1+1)+Integer.toString(i2);
                calenderClicked();
            }
        });


        databaseReference= FirebaseDatabase.getInstance().getReference("Calendar");
    }
    private void calenderClicked(){

        databaseReference.child(stringDateSelected).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null){
                    editText.setText(snapshot.getValue().toString());
                }else {
                    editText.setText("NO EVENT FOUND!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    public void buttonSaveEvent(View view) {
        String eventText = editText.getText().toString();

        if (eventText.isEmpty()) {
            Toast.makeText(this, "Please enter an Event", Toast.LENGTH_SHORT).show();
        } else {
            databaseReference.child(stringDateSelected).setValue(eventText);
            Toast.makeText(this, "Event added: " + eventText, Toast.LENGTH_SHORT).show();
        }
    }

    public void signs(View view){
        FirebaseAuth.getInstance().signOut();
        Intent I= new Intent(getApplicationContext(), Login.class);
        startActivity(I);
        finish();
    }
}