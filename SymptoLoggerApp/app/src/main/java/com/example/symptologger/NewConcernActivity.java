package com.example.symptologger;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/*
 *  Copyright 2018 Remi Arshad, Noni Hua, Jason Lee, Patrick Tamm, Kaiwen Zhang
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at

 *     http://www.apache.org/licenses/LICENSE-2.0

 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/**
 * The activity that starts when the patient is adding a new concern.
 */

public class NewConcernActivity extends AppCompatActivity {

    /*
    * The date features for this activity have been taken from Noni Hua's NewRecordActivity
    */

//    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static DateFormat dateFormat = new SimpleDateFormat("EEEE, MMM dd", Locale.CANADA);
    private static DateFormat timeFormat = new SimpleDateFormat("h:mm a", Locale.CANADA);

    Concern newConcern; //New concern variable declared ...
    //Date blankDate;

    Calendar c;

    int year;
    int month;
    int day;
    int hour;
    int minute;

    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_concern);

        getCalendarInfo();
        Date now = c.getTime();

        Intent fromListConcerns = getIntent();
        userName = fromListConcerns.getStringExtra("userName");

        final Button editDateButton = findViewById(R.id.newConcernDateField);
        editDateButton.setText(dateFormat.format(now));
        final Button editTimeButton = findViewById(R.id.newConcernTimeField);
        editTimeButton.setText(timeFormat.format(now));

        FloatingActionButton saveFAB = findViewById(R.id.saveNewFAB);
        saveFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    saveConcern();
                } catch (ParseException e){
                    e.printStackTrace();
                }
            }
        });

        FloatingActionButton cancelNewFAB = findViewById(R.id.cancelNewFAB);
        cancelNewFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(NewConcernActivity.this,"Cancel ...", Toast.LENGTH_SHORT).show();
                Intent cancelIntent = new Intent(NewConcernActivity.this,ListConcernActivity.class);
                cancelIntent.putExtra("userName",userName);
                startActivity(cancelIntent);
            }
        });

        editDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCalendarInfo();

                final DatePickerDialog datePickerDialog = new DatePickerDialog(NewConcernActivity.this, new DatePickerDialog.OnDateSetListener() {
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

                final TimePickerDialog timePickerDialog = new TimePickerDialog(NewConcernActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        updateCalendarTime(hourOfDay, minute);
                        editTimeButton.setText(timeFormat.format(c.getTime()));
                    }
                }, hour, minute, false);

                timePickerDialog.show();
            }
        });
    }

    public void saveConcern() throws ParseException {

        ConcernListController clc = new ConcernListController();



        Toast.makeText(this,"Saving ...", Toast.LENGTH_SHORT).show();

        EditText editTitle = (EditText) findViewById(R.id.concernTitle);
        EditText editDescription = (EditText) findViewById(R.id.concernDescription);
        //EditText editDate = (EditText) findViewById(R.id.newConcernDate);

        String concernTitle = editTitle.getText().toString();
        String concernDescription = editDescription.getText().toString();
        //String concernDate = editDate.getText().toString();

        /*
        That EditText.getText() does not return null, but empty string ...
        https://stackoverflow.com/questions/19590472/does-edittext-gettext-ever-returns-null
        User: https://stackoverflow.com/users/603742/stinepike
        Date: 2018-11-11
        */

//        if (concernDate.equals("")){
//           // blankDate = new Date();
//        } else {
//            //blankDate = sdf.parse(concernDate);
//        }

        try {
            newConcern = new Concern(concernTitle,c.getTime(),concernDescription,userName);
        } catch (TitleTooLongException e) {
            e.printStackTrace();
        } catch (DescriptionTooLongException f){
            f.printStackTrace();
        }

        clc.addConcern(newConcern, userName);
        ConcernList concerns = ConcernListController.getConcernList(userName);

        Intent doneIntent = new Intent(NewConcernActivity.this, ListConcernActivity.class);
        doneIntent.putExtra("userName",userName);
        startActivity(doneIntent);
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
