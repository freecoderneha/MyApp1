package com.example.android.myapp1;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.Calendar;



public class ConfirmActivity extends AppCompatActivity {
    private TextView dateFromView;
    private TextView dateToView;
    private TextView timeToView;
    private TextView timeFromView;

    private int hour,minute,day,year,month;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        dateFromView = (TextView) findViewById(R.id.from_date);
        timeFromView = (TextView) findViewById(R.id.from_time);
        dateToView = (TextView) findViewById(R.id.to_date);
        timeToView = (TextView) findViewById(R.id.to_time);

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        showFromDate(year, month + 1, day);
        showToDate(year, month + 1, day);
        showFromTime(hour,minute);
        showToTime(hour,minute);

        EditText fromDate = (EditText) findViewById(R.id.from_date);
        fromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            @SuppressWarnings("deprecation")
            public void onClick(View view) {
                showDialog(999);
            }
        });

        EditText toDate = (EditText) findViewById(R.id.to_date);
        toDate.setOnClickListener(new View.OnClickListener() {
            @Override
            @SuppressWarnings("deprecation")
            public void onClick(View view) {
                showDialog(998);
            }
        });

        EditText fromTime=(EditText) findViewById(R.id.from_time) ;
        fromTime.setOnClickListener(new View.OnClickListener() {
            @Override
            @SuppressWarnings("deprecation")
            public void onClick(View view) {
                showDialog(997);
            }
        });

        EditText toTime=(EditText) findViewById(R.id.to_time);
        toTime.setOnClickListener(new View.OnClickListener() {
            @Override
            @SuppressWarnings("deprecation")
            public void onClick(View view) {
                showDialog(995);
            }
        });


    }

    @Override
    protected Dialog onCreateDialog(int id) {

        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateFromListener, year, month, day);

        } else if (id == 998) {
            return new DatePickerDialog(this,
                    myDateToListener, year, month, day);

        }
        else if (id == 997) {
            return new TimePickerDialog(this,
                    myTimeFromListener, hour, minute,true);

        }
        else if (id == 995) {
            return new TimePickerDialog(this,
                    myTimeToListener, hour, minute,true);

        }
        return null;
    }


    private DatePickerDialog.OnDateSetListener myDateToListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {

                    showToDate(arg1, arg2 + 1, arg3);


                }
            };
    private DatePickerDialog.OnDateSetListener myDateFromListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {

                    showFromDate(arg1, arg2 + 1, arg3);

                }
            };

    private TimePickerDialog.OnTimeSetListener myTimeFromListener = new
            TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                    showFromTime(hour,minute);
                }
            };

    private TimePickerDialog.OnTimeSetListener myTimeToListener = new
            TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                    showToTime(hour,minute);
                }
            };

    private void showFromDate(int year, int month, int day) {
        dateFromView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    private void showToDate(int year, int month, int day) {
        dateToView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    private void showFromTime(int hour, int minute) {
        timeFromView.setText(String.format("%02d:%02d", hour, minute));
    }

    private void showToTime(int hour, int minute) {
        timeToView.setText(String.format("%02d:%02d", hour, minute));
    }


}
