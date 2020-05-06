package com.example.blood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class main extends AppCompatActivity {

    Button donate;
    Button need;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        donate = findViewById(R.id.donate);
        need = findViewById(R.id.need);




        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent donateIntent = new Intent(main.this,Donate.class);
                startActivity(donateIntent);

            }
        });


        need.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent needIntent = new Intent(main.this,before_need.class);
                startActivity(needIntent);
            }
        });


    }
}
