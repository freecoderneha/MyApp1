package com.example.android.myapp1;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;


public class MyFlatActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flat);
        ImageView image=(ImageView)  findViewById(R.id.image_round);
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),
                R.drawable.flower);
        RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(getResources(),bitmap);
        drawable.setCircular(true);
        image.setImageDrawable(drawable);

    }

}
