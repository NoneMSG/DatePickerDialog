package com.example.datepickerdialog;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_CALENDAR_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if( requestCode == REQUEST_CODE_CALENDAR_ACTIVITY ) {
            int year = intent.getIntExtra( "year", -1 );
            int month = intent.getIntExtra( "month", -1 );
            int date = intent.getIntExtra( "date", -1 );

            ((EditText)findViewById( R.id.editText )).
                    setText( year + "/" + (month+1) + "/" + date );
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        new AlertDialog.Builder( this ).
            setIcon( android.R.drawable.ic_dialog_alert ).
            setTitle( "종료" ).
            setMessage( "정말 끝내시겠습니까? " ).
            setPositiveButton("예", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            }).
            setNegativeButton( "아니오", null ).
            show();
    }

    public void dialogDatePicker(View view ) {
        Calendar calendar = Calendar.getInstance();

        new DatePickerDialog(
            this,
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    TextView tv = (TextView)findViewById( R.id.editText );
                    tv.setText( year + "/" + (monthOfYear + 1) + "/" + dayOfMonth );
                }
            },
            calendar.get( Calendar.YEAR ),
            calendar.get( Calendar.MONTH ),
            calendar.get( Calendar.DATE )).show();

    }

    public void dialogTimePicker( View view ) {
        Calendar calendar = Calendar.getInstance();

        new TimePickerDialog(
            this,
            new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    String time = hourOfDay + ":" + minute;
                    Toast.makeText(
                            getApplication(),
                            time,
                            Toast.LENGTH_LONG ).show();
                }
            },
            calendar.get( Calendar.HOUR ),
            calendar.get( Calendar.MINUTE ),
            false ).show();
    }

    public void calendarActivity( View view ) {
        Intent intent = new Intent( this, CalendarActivity.class );
        startActivityForResult( intent, REQUEST_CODE_CALENDAR_ACTIVITY );
    }
}
