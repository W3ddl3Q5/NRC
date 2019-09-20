package com.example.imp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ImageButton idk;
        idk = findViewById(R.id.nav_locker);
        ImageButton can;
        can = findViewById(R.id.runing);

        idk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Main2Activity.this, start_screen.class);
                Main2Activity.this.startActivity(myIntent);
            }
        });

        can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Main2Activity.this, MainActivity.class);
                Main2Activity.this.startActivity(myIntent);
            }
        });
    }
}
