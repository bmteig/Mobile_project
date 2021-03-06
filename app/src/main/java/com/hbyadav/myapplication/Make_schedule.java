package com.hbyadav.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.tourguide.R;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Make_schedule extends AppCompatActivity {
    Spinner classSelect, daySelect;
    ArrayAdapter adapterSpinner, days;
    String courses[] = {"ANCH", "BIOL", "COMP-SCI", "ENGL", "HIST", "MATH", "PHYS"};    // course type list to choose from
    String weekdays[] = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"}; // day list to choose from
    StudentbaseAdapter studentAdapter;
    Student_display student_display = new Student_display();
    public String username = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_schedule);
        getSupportActionBar().setTitle(R.string.schedule);
        studentAdapter = new StudentbaseAdapter(this);
        studentAdapter = studentAdapter.open();
        username = student_display.Username();
        classSelect = (Spinner) findViewById(R.id.classSelector);
        daySelect = (Spinner) findViewById(R.id.daySelector);

        adapterSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, courses);
        assert classSelect != null;
        classSelect.setAdapter(adapterSpinner);     // assign course subject array to spinner for user selection

        days = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, weekdays);
        assert classSelect != null;
        daySelect.setAdapter(days);             // assign day array to spinner for user selection

        Button btn = (Button) findViewById(R.id.saveBUTTON_SCHEDULE);
        assert btn != null;

        // alert to see if user wants to add another class
        final AlertDialog.Builder builder = new AlertDialog.Builder(Make_schedule.this);
        builder.setTitle(R.string.finishT);
        builder.setMessage(R.string.finishM);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // direct user to main hub
                Intent intent = new Intent(Make_schedule.this, Student_display.class);
                intent.putExtra("username", username);
                Log.i("Make_schedule", "User pressed yes - direct to hub.");
                startActivity(intent);
            }
        });
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Do nothing, allow user to stay in make schedule activity
                Log.i("Make_schedule", "User pressed no - do nothing");
            }
        });
        AlertDialog dialog = builder.create();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // save class to database
                saveSchedule(v);
                // show alert to see if user wants to add another class
                builder.show();
            }
        });
    }

    private void saveSchedule(View v) {
        // get selected/entered fields
        String daySelected = daySelect.getSelectedItem().toString();
        String classSelected = classSelect.getSelectedItem().toString();
        EditText editText = (EditText) findViewById(R.id.subjectName);
        String className = editText.getText().toString();
        // get time value
        TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);
        int hour = timePicker.getCurrentHour();
        int min = timePicker.getCurrentMinute();
        // format time value appropriately
        String designation = "AM";
        if (hour > 12) {
            hour -= 12;
            designation = "PM";
        }
        String Hour = String.valueOf(hour);
        String Min = String.valueOf(min);
        if (Min.length() == 1) {Min = "0" + Min;}
        String time = Hour + ":" + Min + designation;
        // save to DB
        studentAdapter.Schedule(classSelected, className, time, daySelected, username);
        Toast.makeText(getBaseContext(), "Schedule Saved", Toast.LENGTH_SHORT).show();
    }
}
