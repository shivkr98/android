package com.example.blood;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity {

    EditText ed_username,ed_email,ed_password;
    String str_name,str_email,str_password;
    String url = "https://shivaiiii.000webhostapp.com/register.php";
    Button rg;
    TextView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ed_email = findViewById(R.id.Email);
        ed_username = findViewById(R.id.name);
        ed_password = findViewById(R.id.password);
        rg = findViewById(R.id.btn_regist);
        back = findViewById(R.id.back_login);


        rg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToLogin();
            }
        });

    }

    public void moveToLogin() {

        startActivity(new Intent(getApplicationContext(),login.class));
        finish();
    }

    public void Register() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");


        if(ed_username.getText().toString().equals("")){
            Toast.makeText(this, "Enter Username", Toast.LENGTH_SHORT).show();
        }
        else if(ed_email.getText().toString().equals("")){
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
        }
        else if(ed_password.getText().toString().equals("")){
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        }
        else{

            progressDialog.show();
            str_name = ed_username.getText().toString().trim();
            str_email = ed_email.getText().toString().trim();
            str_password = ed_password.getText().toString().trim();


            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    ed_username.setText("");
                    ed_email.setText("");
                    ed_password.setText("");
                    Toast.makeText(register.this, response, Toast.LENGTH_SHORT).show();
                }
            },new Response.ErrorListener(){

                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(register.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();

                    params.put("name",str_name);
                    params.put("email",str_email);
                    params.put("password",str_password);
                    return params;

                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(register.this);
            requestQueue.add(request);


        }

    }
}