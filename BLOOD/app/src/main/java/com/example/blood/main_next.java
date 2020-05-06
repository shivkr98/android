package com.example.blood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class main_next extends AppCompatActivity {

    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;

    private TextView[] mDots;

    private  SlideAdapter slideAdapter;

    private  int mCurretPage;

    private Button mNextBtn;

    TextView skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_next);

        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout = (LinearLayout) findViewById(R.id.dotsLayout);
        mNextBtn= findViewById(R.id.n_button);
        skip = findViewById(R.id.skip);

        slideAdapter = new SlideAdapter(this);

        mSlideViewPager.setAdapter(slideAdapter);
        addDotsIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);


        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            if(mNextBtn.getText() == "Next" ) {
                mSlideViewPager.setCurrentItem(mCurretPage + 1);
            }
            else if(mNextBtn.getText() == "Finish" ) {
                Intent intent = new Intent(main_next.this,users.class);
                startActivity(intent);



            }
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(main_next.this,users.class);
                startActivity(intent);

            }
        });


    }

    public void addDotsIndicator(int position) {

        mDots = new TextView[5];

        for(int i = 0; i<mDots.length; i++ ) {

            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8225"));
            mDots[i].setTextColor(getResources().getColor(R.color.colorPrimary));

            mDotLayout.addView(mDots[i]);

        }

        if (mDots.length > 0) {
            mDots[position].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }


    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int i) {


            addDotsIndicator(i);


            mCurretPage =i;
            if (i==0){
                mNextBtn.setEnabled(true);

                mNextBtn.setText("Next");


            }else if (i == mDots.length -1){

                mNextBtn.setEnabled(true);
                mNextBtn.setText("Finish");

            }else {
                mNextBtn.setEnabled(true);
                mNextBtn.setText("Next");
            }


        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}
