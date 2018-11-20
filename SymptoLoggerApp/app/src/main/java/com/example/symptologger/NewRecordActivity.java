package com.example.symptologger;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

/**
 * <p>
 *     Activity to create a new record.
 *     App user can set title, choose date and time, add geo location and add pictures for body parts
 * </p>
 */
public class NewRecordActivity extends AppCompatActivity {
    private static DateFormat dateFormat = new SimpleDateFormat("EEEE, MMM dd", Locale.CANADA);
    private static DateFormat timeFormat = new SimpleDateFormat("h:mm a", Locale.CANADA);

    private int pos;
    Collection<Concern> concerns;
    ArrayList<Concern> concernList;

    Calendar c;

    int year;
    int month;
    int day;
    int hour;
    int minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_record_avtivity);

        Intent intent = getIntent();
        pos = intent.getIntExtra("pos",0);

        concerns = ConcernListController.getConcernList().getConcerns();
        concernList = new ArrayList<Concern>(concerns);

        getCalendarInfo();
        Date now = c.getTime();

        final Button editDateButton = findViewById(R.id.dateField);
        editDateButton.setText(dateFormat.format(now));
        final Button editTimeButton = findViewById(R.id.timeField);
        editTimeButton.setText(timeFormat.format(now));
        Button addLocationButton = findViewById(R.id.addLocationButton);
        Button addBodyPartsButton = findViewById(R.id.addBodyPartsButton);

        editDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCalendarInfo();

                final DatePickerDialog datePickerDialog = new DatePickerDialog(NewRecordActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        updateCalendarDate(year, month, dayOfMonth);
                        editDateButton.setText(dateFormat.format(c.getTime()));
                    }
                }, year, month, day);

                datePickerDialog.show();
            }
        });

        editTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCalendarInfo();

                final TimePickerDialog timePickerDialog = new TimePickerDialog(NewRecordActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        updateCalendarTime(hourOfDay, minute);
                        editTimeButton.setText(timeFormat.format(c.getTime()));
                    }
                }, hour, minute, false);

                timePickerDialog.show();
            }
        });

        addLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancelIntent = new Intent(NewRecordActivity.this, MapsActivity.class);
                startActivity(cancelIntent);
            }
        });

        addBodyPartsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: go to new body part view
            }
        });

        FloatingActionButton newRecordFAB = findViewById(R.id.newRecordFAB);
        newRecordFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(NewRecordActivity.this,"Saving Record", Toast.LENGTH_SHORT).show();

                EditText recordTitle = (EditText) findViewById(R.id.recordTitleText);
                String title = recordTitle.getText().toString();

                Record newRecord = new Record(c.getTime(),title);

                Concern thisConcern = concernList.get(pos);
                thisConcern.addRecord(newRecord);

                Intent doneIntent = new Intent(NewRecordActivity.this, ViewConcernActivity.class);
                doneIntent.putExtra("pos",pos);
                startActivity(doneIntent);
            }
        });

        FloatingActionButton cancelNewRecordFAB = findViewById(R.id.cancelNewRecordFAB);
        cancelNewRecordFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cancelIntent = new Intent(NewRecordActivity.this, ViewConcernActivity.class);
                cancelIntent.putExtra("pos",pos);
                startActivity(cancelIntent);
            }
        });
    }

    /**
     * Get calendar information and update global variables
     */
    private void getCalendarInfo() {
        c = Calendar.getInstance(Locale.CANADA);
        day = c.get(Calendar.DAY_OF_MONTH);
        month = c.get(Calendar.MONTH);
        year = c.get(Calendar.YEAR);
        hour = c.get(Calendar.HOUR);
        minute = c.get(Calendar.MINUTE);
    }

    /**
     * Update calendar information after user chooses a date
     * @param year
     * @param month
     * @param dayOfMonth
     */
    private void updateCalendarDate(int year, int month, int dayOfMonth) {
        c.set(year, month, dayOfMonth);
    }

    /**
     * Update time after user selects a time
     * @param hour
     * @param minute
     */
    private void updateCalendarTime(int hour, int minute) {
        c.set(year, month, day, hour, minute);
    }

}