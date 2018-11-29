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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
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
 * If the patient would like to modify the details of a particular concern, this is the activity that
 * enables them to do so.
 */

public class ModifyConcernActivity extends AppCompatActivity {

    private int pos;
    private String userName;
    Collection<Concern> concerns;
    ArrayList<Concern> concernList;

    //private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static DateFormat dateFormat = new SimpleDateFormat("EEEE, MMM dd", Locale.CANADA);
    private static DateFormat timeFormat = new SimpleDateFormat("h:mm a", Locale.CANADA);

    Calendar c;

    int year;
    int month;
    int day;
    int hour;
    int minute;

    String concernTitle;
    String concernDesc;
    String concernDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_concern);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        pos = extras.getInt("pos");
        userName = extras.getString("userName");

        concerns = ConcernListController.getConcernList("").getConcernsList();
        concernList = new ArrayList<Concern>(concerns);

        Concern newConcern; //New concern variable declared ...

        concernTitle = concernList.get(pos).getTitle();
        concernDate = concernList.get(pos).getDate();
        concernDesc = concernList.get(pos).getDescription();

        getCalendarInfo();
        Date now = c.getTime();

        final Button editDateButton = findViewById(R.id.modConcernDateField);
        editDateButton.setText(dateFormat.format(now));
        final Button editTimeButton = findViewById(R.id.modConcernTimeField);
        editTimeButton.setText(timeFormat.format(now));

        FloatingActionButton modifyFAB = findViewById(R.id.modifyConcernFAB);
        modifyFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    modifyConcern();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        FloatingActionButton cancelFAB = findViewById(R.id.cancelFAB);
        cancelFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ModifyConcernActivity.this,"Cancel ...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ModifyConcernActivity.this, ViewConcernActivity.class);
                startActivity(intent);
            }
        });

        editDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCalendarInfo();

                final DatePickerDialog datePickerDialog = new DatePickerDialog(ModifyConcernActivity.this, new DatePickerDialog.OnDateSetListener() {
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

                final TimePickerDialog timePickerDialog = new TimePickerDialog(ModifyConcernActivity.this, new TimePickerDialog.OnTimeSetListener() {
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

    @Override
    protected void onStart(){
        super.onStart();
    }

    public void modTitle(View view){
        EditText newTitle = (EditText) findViewById(R.id.modifyConcernTitle);
        String title = newTitle.getText().toString();
        if (!title.equals("")){
            concernTitle = title;
        }
        Toast.makeText(this,"Title changed; click save to apply changes",Toast.LENGTH_LONG).show();
    }

    public void modDate(View view){
        concernDate = c.getTime().toString();
        Toast.makeText(this, "Date changed; click save to apply changes.",Toast.LENGTH_LONG).show();
    }

    public void modDesc(View view){
        EditText newDescription = (EditText) findViewById(R.id.modifyConcernDesc);
        String description = newDescription.getText().toString();
        if(!description.equals("")){
            concernDesc = description;
        }
        Toast.makeText(this,"Description changed; click save to apply changes.",Toast.LENGTH_LONG).show();
    }

    public void modifyConcern() throws ParseException {
        Toast.makeText(this,"Modifying ...",Toast.LENGTH_SHORT).show();

        try {
            concernList.get(pos).setTitle(concernTitle);
        } catch (TitleTooLongException e) {
            e.printStackTrace();
            Toast.makeText(this, "Title too long: 30 characters maximum", Toast.LENGTH_LONG).show();
        }


        try {
            concernList.get(pos).setDescription(concernDesc);
        } catch (DescriptionTooLongException e) {
            e.printStackTrace();
            Toast.makeText(this, "Description too long: 300 characters maximum", Toast.LENGTH_SHORT).show();
        }

        concernList.get(pos).setDate(concernDate);

        Intent intent = new Intent(ModifyConcernActivity.this,ViewConcernActivity.class);
        intent.putExtra("pos",pos);
        startActivity(intent);
    }

    public void addRecord(View view){
        Toast.makeText(this,"Add record ...", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ModifyConcernActivity.this, NewRecordActivity.class);
        intent.putExtra("pos",pos);
        startActivity(intent);
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
