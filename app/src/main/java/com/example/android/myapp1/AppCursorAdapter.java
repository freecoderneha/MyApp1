package com.example.android.myapp1;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.myapp1.data.AppContract.AppEntry;


public class AppCursorAdapter extends CursorAdapter {


    public AppCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        return LayoutInflater.from(context).inflate(R.layout.list_item_layout, parent, false);
    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView titleTextView = (TextView) view.findViewById(R.id.heading);
        TextView descriptionTextView = (TextView) view.findViewById(R.id.sub_heading);



        int titleColumnIndex = cursor.getColumnIndex(AppEntry.COLUMN_COMPLAIN_TITLE);
        int descriptionColumnIndex = cursor.getColumnIndex(AppEntry.COLUMN_COMPLAIN_DESCRIPTION);

        String complainTitle = cursor.getString(titleColumnIndex);
        String complainDescription = cursor.getString(descriptionColumnIndex);




        if (TextUtils.isEmpty(complainDescription)) {
            complainDescription = context.getString(R.string.unknown_description);
        }

        titleTextView.setText(complainTitle);
        descriptionTextView.setText(complainDescription);


    }
}