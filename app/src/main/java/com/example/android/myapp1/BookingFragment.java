package com.example.android.myapp1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


public class BookingFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_booking, container, false);
     TextView mhall=(TextView) v.findViewById(R.id.hall);
        TextView mclub=(TextView) v.findViewById(R.id.club);
        TextView mguest=(TextView) v.findViewById(R.id.guest);
        mhall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),HallActivity.class);
                startActivity(i);
            }
        });
        mclub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),ClubActivity.class);
                startActivity(i);
            }
        });
        mguest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),GuestActivity.class);
                startActivity(i);
            }
        });
        return v;
    }


}