package com.example.android.myapp1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;



public class WordAdapter  extends ArrayAdapter<Word> {

    public WordAdapter(Context context, ArrayList<Word> words)
    {
        super(context, 0, words);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.grid_item, parent, false);
        }

        Word currentWord = getItem(position);

        TextView headingTextView = (TextView) listItemView.findViewById(R.id.heading);
        headingTextView.setText(currentWord.getHeadingId());

        ImageView image=(ImageView) listItemView.findViewById(R.id.image);
        image.setImageResource(currentWord.getImageResourceId());

        View gradientView=listItemView.findViewById(R.id.view);
        gradientView.setBackgroundResource(currentWord.getGradientResourceId());
     return listItemView;
    }
}