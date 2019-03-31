package com.hana.tracerstudy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

public class LoginBy extends AppCompatActivity {

    AppPreferences appPreferences = new AppPreferences();

    CardView alumni, dosen;
    int key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_by);

        alumni = findViewById(R.id.alumni);
        dosen = findViewById(R.id.dosen);

        alumni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appPreferences.setLevel(getApplicationContext(),"level","alumni");
                startActivity(new Intent(getApplicationContext(), Login.class));

            }
        });

        dosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appPreferences.setLevel(getApplicationContext(),"level","dosen");
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });
    }
}
