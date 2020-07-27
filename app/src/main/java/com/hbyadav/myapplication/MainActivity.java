package com.hbyadav.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;
import com.android.tourguide.R;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sign(View view) {
        Intent intent = new Intent(this, Student_sign.class);
        startActivity(intent);
    }

    public void login(View view) {
        Intent intent = new Intent(this, Student.class);
        startActivity(intent);
    }
}
