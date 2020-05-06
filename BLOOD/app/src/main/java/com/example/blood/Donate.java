package com.example.blood;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Donate extends AppCompatActivity {

    private static  final int REQUEST_LOCATION=1;
    EditText donate_date;
    EditText blood_g;
    EditText name;
    CheckBox male,female;
    EditText age;
    EditText mobile;
    EditText health_pro;

    Button send;

    String gender;

    String url="https://shivaiiii.000webhostapp.com/send.php";

    String last_year;

    String str_blood,str_name,str_donate_date,str_age,str_mobile,str_health_pro,l_pro,gd,lt,lon;

    CheckBox surgery,chikungunya,dengue,no_problem;
    LocationManager locationManager;
    String latitude,longitude;
    TextView show_latitude,show_logitude;

    Button getlocationBtn;

    EditText l,g;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_donate);

        // deleration of all
        blood_g = findViewById(R.id.ed_blood);
        name=findViewById(R.id.ed_name);
        donate_date=findViewById(R.id.ed_donate);
        // check box
        male = findViewById(R.id.male);
        female=findViewById(R.id.female);
        send=findViewById(R.id.getsubmit);

        if (male.isChecked())
            gender = "male";
        if (female.isChecked())
            gender = "female";



        age=findViewById(R.id.ed_age);
        mobile=findViewById(R.id.ed_mobile);
        health_pro=findViewById(R.id.ed_health);

        //check box
        surgery=findViewById(R.id.surgery);
        chikungunya=findViewById(R.id.chikungunya);
        dengue=findViewById(R.id.dengue);
        no_problem=findViewById(R.id.no_problem);

        show_latitude=findViewById(R.id.ed_letitude);
        show_logitude=findViewById(R.id.ed_logitude);

        l=findViewById(R.id.l);
        g=findViewById(R.id.g);

        getlocationBtn=findViewById(R.id.getLocation);


        //Add permission

        ActivityCompat.requestPermissions(this,new String[]
                {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);


        getlocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                locationManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);

                //Check gps is enable or not

                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
                {
                    //Write Function To enable gps

                    OnGPS();
                }
                else
                {
                    //GPS is already On then

                    getLocation();
                }
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                g.setText(g_check());
                l.setText(l_check());
                send_date();

            }
        });





    }



    public String g_check(){
        String var;
        if (male.isChecked())
            gender = "male";
        if (female.isChecked())
            gender = "female";
        return gender;


    }

    public String l_check(){
        if (surgery.isChecked())
            last_year = "surgery";
        if (chikungunya.isChecked())
            last_year="chikungunya";
        if (dengue.isChecked())
            last_year="dengue";
        if (no_problem.isChecked())
            last_year="no";

        return last_year;

    }


    private void getLocation() {

        //Check Permissions again

        if (ActivityCompat.checkSelfPermission(Donate.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Donate.this,

                Manifest.permission.ACCESS_COARSE_LOCATION) !=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }
        else
        {
            Location LocationGps= locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location LocationNetwork=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Location LocationPassive=locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

            if (LocationGps !=null)
            {
                double lat=LocationGps.getLatitude();
                double longi=LocationGps.getLongitude();

                latitude=String.valueOf(lat);
                longitude=String.valueOf(longi);


                show_latitude.setText(latitude);
                show_logitude.setText(longitude);
            }
            else if (LocationNetwork !=null)
            {
                double lat=LocationNetwork.getLatitude();
                double longi=LocationNetwork.getLongitude();

                latitude=String.valueOf(lat);
                longitude=String.valueOf(longi);

                show_latitude.setText(latitude);
                show_logitude.setText(longitude);
            }
            else if (LocationPassive !=null)
            {
                double lat=LocationPassive.getLatitude();
                double longi=LocationPassive.getLongitude();

                latitude=String.valueOf(lat);
                longitude=String.valueOf(longi);


                show_latitude.setText(latitude);
                show_logitude.setText(longitude);
            }
            else
            {
                Toast.makeText(this, "Can't Get Your Location", Toast.LENGTH_SHORT).show();
            }

            //Thats All Run Your App
        }

    }


    private void OnGPS() {

        final AlertDialog.Builder builder= new AlertDialog.Builder(this);

        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });
        final AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }


    public void send_date() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");

        if(blood_g.getText().toString().equals("")){
            Toast.makeText(this, "Enter Blood Group", Toast.LENGTH_SHORT).show();
        }
        else if(name.getText().toString().equals("")){
            Toast.makeText(this, "Enter name", Toast.LENGTH_SHORT).show();
        }
        else if(donate_date.getText().toString().equals("")){
            Toast.makeText(this, "Enter your date of donation", Toast.LENGTH_SHORT).show();
        }
        else if(age.getText().toString().equals("")){
            Toast.makeText(this, "Enter Age", Toast.LENGTH_SHORT).show();
        }
        else if(mobile.getText().toString().equals("")){
            Toast.makeText(this, "Enter mobile number", Toast.LENGTH_SHORT).show();
        }
        else if(health_pro.getText().toString().equals("")){
            Toast.makeText(this, "Enter health problem if you have", Toast.LENGTH_SHORT).show();
        }
        else if(show_latitude.getText().toString().equals("")){
            Toast.makeText(this, "Enter health problem if you have", Toast.LENGTH_SHORT).show();
        }
        else if(show_logitude.getText().toString().equals("")){
            Toast.makeText(this, "Enter health problem if you have", Toast.LENGTH_SHORT).show();
        }


        else {

            progressDialog.show();

             str_blood= blood_g.getText().toString().trim();
            str_name = name.getText().toString().trim();
            str_donate_date = donate_date.getText().toString().trim();
            str_age= age.getText().toString().trim();
            str_mobile= mobile.getText().toString().trim();
            str_health_pro= health_pro.getText().toString().trim();
            l_pro=l.getText().toString().trim();
            gd=g.getText().toString().trim();
            lt=show_latitude.getText().toString().trim();
            lon=show_logitude.getText().toString().trim();

            url = url + "?name="+str_name + "&blood_g=" + str_blood + "?donate_date" + str_donate_date + "?age"+ str_age + "?mobile"+str_mobile+"?health_pro"+str_health_pro +"?last_year"+l_pro+"?gender"+gd + "?latitude"+lt +"?logitude"+lon;


            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    blood_g.setText("");
                    name.setText("");
                    donate_date.setText("");
                    age.setText("");
                    mobile.setText("");
                    health_pro.setText("");
                    l.setText("");
                    g.setText("");
                    show_latitude.setText("");
                    show_logitude.setText("");
                    Toast.makeText(Donate.this, response, Toast.LENGTH_SHORT).show();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(Donate.this, error.getMessage(),Toast.LENGTH_SHORT).show();

                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();

                    params.put("name",str_name);
                    params.put("blood_g",str_blood);
                    params.put("donate_date",str_donate_date);
                    params.put("age",str_age);
                    params.put("mobile",str_mobile);
                    params.put("health_pro",str_health_pro);
                    params.put("last_year",l_pro);
                    params.put("gender",gd);
                    params.put("latitude",lt);
                    params.put("longitude",lon);
                    return params;

                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(Donate.this);
            requestQueue.add(request);






        }






    }

   }
