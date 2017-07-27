package com.example.datepickerdialog;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_date_picker );

        Calendar calendar = Calendar.getInstance();

        DatePicker dp = (DatePicker)findViewById( R.id.datePicker );
        dp.init(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DATE),
            new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    Intent intent = new Intent();
                    intent.putExtra( "year", year );
                    intent.putExtra( "month", monthOfYear );
                    intent.putExtra( "date", dayOfMonth );

                    setResult( Activity.RESULT_OK, intent );
                    finish();
                }
            });

    }
}
