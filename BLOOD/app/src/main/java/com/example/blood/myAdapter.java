package com.example.blood;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class myAdapter extends ArrayAdapter<Employes> {

    TextView distance;


    String d;

    Context context ;
    List<Employes> arrayListEmployee;

    public myAdapter(@NonNull Context context, List<Employes> arrayListEmployee) {
        super(context,R.layout.custom_list_item,arrayListEmployee);

        this.context=context;
        this.arrayListEmployee=arrayListEmployee;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item,null,true);


        TextView tvname=view.findViewById(R.id.txt_name);



        distance=view.findViewById(R.id.ed_distance);






        tvname.setText(arrayListEmployee.get(position).getName());
        Log.i("Shiv chutiya", "/" +arrayListEmployee.get(position).getName()  );
        distance.setText(Float.toString(arrayListEmployee.get(position).getDistance()));











        return view;
    }





}








