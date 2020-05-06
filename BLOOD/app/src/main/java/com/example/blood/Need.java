package com.example.blood;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Double.parseDouble;

public class Need extends AppCompatActivity {

    private static final int REQUEST_LOCATION = 1;
     String latitude;
     String longitude;

    TextView show_latitude, show_logitude;
    LocationManager locationManager;

    RequestQueue requestQueue;

    ListView listView;
    myAdapter adapter;
    Employes employes;
    String data;
    String b;

    TextView text;
    String t;

    String url = "https://shivaiiii.000webhostapp.com/try.php";

    public static ArrayList<Employes> employesArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_need);

        text = findViewById(R.id.text);
        show_latitude = findViewById(R.id.ed_letitude);
        show_logitude = findViewById(R.id.ed_logitude);
        listView = findViewById(R.id.myListView);

        requestQueue =Volley.newRequestQueue(getApplicationContext());

        Intent intent = getIntent();
         data = intent.getStringExtra("data");
        latitude = intent.getStringExtra("latitude");
        longitude = intent.getStringExtra("longitude");

        text.setText(data);


        retrieveData();
        adapter = new myAdapter(Need.this, employesArrayList);
        listView.setAdapter(adapter);


    }




    public void retrieveData() {




        StringRequest request = new StringRequest(Request.Method.POST, url ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        employesArrayList.clear();
                        try {
                            Log.i("jaiiiii", "/" + url);
                            JSONObject jsonObject =new JSONObject(response);
                            Log.i("ooooo", "/" + jsonObject);
                            String success= jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("data");


                            Log.i("blood", "/" + data);


                            if (success.equals("1")){

                                for(int i=0;i<jsonArray.length();i++){

                                    JSONObject object = jsonArray.getJSONObject(i);


                                    String name=  object.getString("name");

                                    String blood_g =  object.getString("blood_g");
                                    Log.i("jaiiiii", "/" + blood_g+"/");
                                    String donate_date=  object.getString("donate_date");
                                    String age=  object.getString("age");
                                    String mobile=  object.getString("mobile");
                                    String health_pro=  object.getString("health_pro");
                                    String last_year=  object.getString("last_year");
                                    String gender=  object.getString("gender");
                                    String lt=  object.getString("latitude");
                                    String lng=  object.getString("longitude");
                                    Log.i("Jo hamara kat raha", "/"+ latitude+"/"+ longitude+"/"+"/"+ lt+"/"+ lng+"/");

                                    String  distance1 =Distance(parseDouble(latitude),parseDouble(longitude), parseDouble(lt), parseDouble(lng));
                                    Log.i("Jo hamara Jada ", "/"+ distance1+"/");

                                    float distance = (float) parseDouble(distance1);
                                    Log.i("Jo distance", "/"+ distance);

                                    if( data.equals(blood_g)) {
                                        Log.i("jaiiiii", "/" + name);


                                        employes = new Employes(name, blood_g, donate_date, age, mobile, health_pro, last_year, gender, lt, lng,distance);

                                        employesArrayList.add(employes);
                                        Collections.sort(employesArrayList, new Comparator<Employes>() {
                                            @Override
                                            public int compare(Employes o1, Employes o2) {
                                                if (o1.getDistance() > o2.getDistance()) {
                                                    return 1;
                                                }
                                                else if (o1.getDistance() <  o2.getDistance()) {
                                                    return -1;
                                                }
                                                else {
                                                    return 0;
                                                }
                                            }
                                        });
                                        int m=0;
                                        for(Employes j:employesArrayList){
                                            Log.i("mein hu "+m, j.getName() + "  " + j.getDistance());
                                            m++;
                                        }
                                        adapter.notifyDataSetChanged();


                                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                                                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                                                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                                                CharSequence[] dialogItem = {"View data"};
                                                builder.setTitle(employesArrayList.get(position).getName());
                                                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int i) {

                                                        switch (i) {
                                                            case 0:
                                                                startActivity(new Intent(getApplicationContext(),DetailActvity.class).putExtra("position",position));


                                                                break;
                                                            case 1:

                                                                break;
                                                            case 2:

                                                                break;
                                                        }

                                                    }
                                                });
                                                builder.create().show();
                                            }
                                        });






                                    }
                                    else {
                                        Log.i("nahiii", "/" + name);
                                    }

                                }

                            }



                        }catch (JSONException e){
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Need.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);



    }

    public String Distance(double lat1,double lng1, double lat2, double lng2) {
        Location l1 = new Location("One");
        l1.setLatitude(lat1);
        l1.setLongitude(lng1);
        Location l2 = new Location("Two");
        l2.setLatitude(lat2);
        l2.setLongitude(lng2);

        float distance = l1.distanceTo(l2);

        distance = distance / 1000.0f;
        String dist = String.valueOf(distance);
        return dist;
    }




}