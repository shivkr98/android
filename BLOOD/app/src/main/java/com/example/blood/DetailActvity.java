package com.example.blood;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActvity extends AppCompatActivity {

    TextView name, blood_g, donate_date, age, moibile, health_pro, last_year;
    int position;
    ImageButton btcall;
    String ph = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_actvity);

        name = findViewById(R.id.txtname);
        blood_g = findViewById(R.id.txtblood_g);
        donate_date = findViewById(R.id.txtdonate_date);
        age = findViewById(R.id.txtage);
        moibile = findViewById(R.id.txtmobile);
        health_pro = findViewById(R.id.txthealth_pro);
        last_year = findViewById(R.id.txtlast_year);

        btcall = findViewById(R.id.bt_call);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        name.setText("Name = " + Need.employesArrayList.get(position).getName());
        blood_g.setText("blood group = " + Need.employesArrayList.get(position).getBlood_g());
        donate_date.setText("donation date = " + Need.employesArrayList.get(position).getDonate_date());
        age.setText("age = " + Need.employesArrayList.get(position).getAge());
        ph =  Need.employesArrayList.get(position).getMobile();
        moibile.setText("Mobile = " +ph);
        health_pro.setText("health problem = " + Need.employesArrayList.get(position).getHealth_pro());
        last_year.setText("Last year problem = " + Need.employesArrayList.get(position).getLast_year());


        btcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + ph));
                Log.i("pppp", "/" + ph);
                if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //  Activity#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for Activity#requestPermissions for more details.
                    return;
                }
                startActivity(intent);


            }
        });








    }






}
