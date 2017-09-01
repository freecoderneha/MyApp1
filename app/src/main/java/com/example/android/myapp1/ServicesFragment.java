package com.example.android.myapp1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;


public class ServicesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_services, container, false);


        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word(R.drawable.scrim,R.string.car,R.drawable.carwash
               ));
        words.add(new Word(R.drawable.scrim1,R.string.milk,R.drawable.milkman
        ));
        words.add(new Word(R.drawable.scrim2,R.string.paper,R.drawable.newspaper1
        ));
        words.add(new Word(R.drawable.scrim3,R.string.maid,R.drawable.maid
        ));
        words.add(new Word(R.drawable.scrim4,R.string.clean,R.drawable.dryclean
        ));


        WordAdapter adapter = new WordAdapter(getActivity(), words);

        GridView listView = (GridView)v.findViewById(R.id.grid);


        listView.setAdapter(adapter);

        return v;
    }
}