package com.example.blood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

public class SlideAdapter  extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;





    public SlideAdapter(main_next main_next){
        this.context = main_next;
    }




    public  int[] slide_image ={

            R.drawable.s1,
            R.drawable.money,
            R.drawable.hero,
            R.drawable.map,
            R.drawable.date
    };


    public String[] slide_headings = new String[]{

            "BLOOD",
            "100% FREE",
            "Join Our Life Saving Squad",
            "Find Blood Donot",
            "Select Donation Date"
    };

    public String[] slide_descs = {

            "\t Blood Donation Platform \n Is Now \n  Social medial platform for \tBlood Donation",
            "We never take money and We never make money for blood \n \n Kindly report if any person asking for money through email",
            "Calling hundreds of Blood Donot is \nno more required using our \n platform",

            "Earn Simply Blood Certificate by \n Searching Blood Donots for others \n under SimplyBlood Ambassador \n Program (SAP)",
            "Join our Revalutionary Blood \n Donation platform by choosing \n any date and location. we will \n find a person near you to whom \n you HELP DIRECTLY"
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (ConstraintLayout) o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {


        layoutInflater =  (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container,false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image);
        TextView slideHeading = (TextView) view.findViewById(R.id.slide_heading);
        TextView slideDescription = (TextView) view.findViewById(R.id.slide_desc);


        slideImageView.setImageResource(slide_image[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_descs[position]);

        container.addView(view);

        return view;


    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
       container.removeView((ConstraintLayout)object);
    }
}
