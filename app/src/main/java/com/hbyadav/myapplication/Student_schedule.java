package com.hbyadav.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.android.tourguide.R;
public class Student_schedule extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_schedule);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new Schedule_fragment()).commit();
    }
}
