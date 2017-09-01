package com.example.android.myapp1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {
     private TabLayout tablayout;
    private int[] tabIcons = {
            R.drawable.ic_action_art_track,
            R.drawable.ic_action_error_outline,
            R.drawable.ic_action_insert_invitation,
            R.drawable.ic_action_local_grocery_store
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);


        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        CategoryAdapter adapter = new CategoryAdapter(this, getSupportFragmentManager());


        viewPager.setAdapter(adapter);
        tablayout = (TabLayout) findViewById(R.id.tabs);
        tablayout.setupWithViewPager(viewPager);
        setupTabIcons();


    }
    private void setupTabIcons() {
        tablayout.getTabAt(0).setIcon(tabIcons[0]);
        tablayout.getTabAt(1).setIcon(tabIcons[1]);
        tablayout.getTabAt(2).setIcon(tabIcons[2]);
        tablayout.getTabAt(3).setIcon(tabIcons[3]);
    }
    private Menu mMenu;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_app, menu);
        MenuItem m=menu.findItem(R.id.profile_photo);
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),
                R.drawable.flower);
        RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(getResources(),bitmap);
        drawable.setCircular(true);
        m.setIcon(drawable);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.profile_photo:

                Intent i=new Intent(MainActivity.this,MyFlatActivity.class);
                startActivity(i);
                return true;

            case R.id.action_helpdesk:

                return true;

            case R.id.action_sign_out:

                return true;
        }
                return super.onOptionsItemSelected(item);

    }




}