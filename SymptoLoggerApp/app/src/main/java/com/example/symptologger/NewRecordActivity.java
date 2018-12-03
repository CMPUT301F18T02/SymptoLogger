package com.example.symptologger;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
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
 * <p>
 *     Activity to create a new record.
 *     App user can set title, choose date and time, add geo location and add pictures for body parts
 * </p>
 */
public class NewRecordActivity extends AppCompatActivity {

    private static DateFormat dateFormat = new SimpleDateFormat("EEEE, MMM dd", Locale.CANADA);
    private static DateFormat timeFormat = new SimpleDateFormat("h:mm a", Locale.CANADA);
    private static DateFormat stringDateFormat = new SimpleDateFormat("E, MMM dd yyyy HH:mm:ss", Locale.CANADA);

    private int pos;
    private String userName;
    private int record_pos;
    private boolean modifying = false;
    Collection<Concern> concerns;
    ArrayList<Concern> concernList;
    Concern concernToModify;
    ArrayList<Record> recordList;
    Record recordToModify;

    Record photorec;

    ArrayList<Photograph> photos = new ArrayList<>();
    List<Bitmap> bits = new ArrayList<>();

    Calendar c;

    int year;
    int month;
    int day;
    int hour;
    int minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_record_activity);

        Intent intent = getIntent();
        Bundle fromModconcern = intent.getExtras();
        pos = fromModconcern.getInt("pos",0);
        userName = fromModconcern.getString("USERNAME");
        modifying = fromModconcern.getBoolean("modifying");

        Log.d("MODIFYING?",modifying+"");
        Log.d("POS?",pos+"");
        Log.d("USERNAME?",userName);

        concerns = ConcernListController.getConcernList(userName).getConcernsList();
        concernList = new ArrayList<Concern>(concerns);
        concernToModify = concernList.get(pos);

        if (!modifying){
            //Log.d("Record to Modify is not found so yeet","321234gfdwe234");
            photorec = new Record(new Date(),"",userName,"");
        } else {
            record_pos = fromModconcern.getInt("RECORD_POS");
            recordList = new ArrayList<>(concernToModify.getRecords());
            recordToModify = recordList.get(record_pos);
            //Log.d("Record to Modify is found","213124");
        }

        if (recordToModify == null){
            Log.d("Record is null","423");
        }else{
            Log.d("Record is found","23");
        }

        final Button editDateButton = findViewById(R.id.dateField);
        final Button editTimeButton = findViewById(R.id.timeField);
        Button addLocationButton = findViewById(R.id.addLocationButton);
        Button addBodyPartsButton = findViewById(R.id.addBodyPartsButton);

        if (!modifying) {
            getCalendarInfo();
            Date now = c.getTime();
            editDateButton.setText(dateFormat.format(now));
            editTimeButton.setText(timeFormat.format(now));

        } else {
            try {
                TextView titleView = findViewById(R.id.recordTitleText);
                titleView.setText(recordToModify.getTitle());
                Date recordTime = stringDateFormat.parse(recordToModify.getDate());
                editDateButton.setText(dateFormat.format(recordTime));
                editTimeButton.setText(timeFormat.format(recordTime));
            } catch (Exception e) {
                getCalendarInfo();
                Date now = c.getTime();
                editDateButton.setText(dateFormat.format(now));
                editTimeButton.setText(timeFormat.format(now));
            }
        }

        setBodyText(addBodyPartsButton);


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
                Intent intent;
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

                try {
                    intent = builder.build(NewRecordActivity.this);
                    startActivityForResult(intent, 1);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });

        addBodyPartsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Return arraylist photos
                if (photorec != null){

                }else{
                    if (recordToModify != null){
                        photorec = recordToModify;
                    }else{
                        photorec = new Record(new Date(), "", userName, "");
                    }
                }
                photos = photorec.getPhoto();
                //feature
                Intent intent = new Intent(NewRecordActivity.this, PhotoRecordActivity.class);
                intent.putExtra("USERNAMEOFRECORD",userName);

                Log.d("Size of the photos ",photos.size()+"");

                if (photos.size() != 0){
                    intent.putExtra("ISEDITMODE",true);
                    EncryptDecryptImageBitmap encryptor = new EncryptDecryptImageBitmap(userName);
                    Gson gson = new Gson();

                    ArrayList<Bitmap> bitt = new ArrayList<>();
                    for (int u = 0; u < photos.size(); u++){
                        Photograph p = new Photograph(); //photos.get(u);
                        bitt.add(encryptor.decrypt(p.getEncrypted()));
                        p.setURL(null);
                    }

                    String jsonPhotos = gson.toJson(bitt);
                    String jsonPhotos2 = gson.toJson(photos);
                    intent.putExtra("BITMAPS", jsonPhotos);
                    intent.putExtra("PHOTOCLASSES",jsonPhotos2);
                }
                startActivityForResult(intent, 666);
            }
        });

        FloatingActionButton newRecordFAB = findViewById(R.id.newRecordFAB);
        newRecordFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // TODO: change for offline mode
                // TODO: change for modifying mode
                Toast.makeText(NewRecordActivity.this,"Saving Record", Toast.LENGTH_SHORT).show();

                EditText recordTitle = (EditText) findViewById(R.id.recordTitleText);
                String title = recordTitle.getText().toString();

                Concern thisConcern = concernList.get(pos);

                if (recordToModify != null){

                    //Adding the photograph classes to the record class
                    ArrayList<Photograph> pkp = recordToModify.getPhoto();
                    for (int u = 0; u < pkp.size(); u++){
                        recordToModify.removePhoto(u);
                    }
                    pkp = photorec.getPhoto();
                    for (int u = 0; u < pkp.size(); u++){
                        recordToModify.addPhoto(pkp.get(u));
                    }
                    //

                    thisConcern.addRecord(recordToModify);
                }else{
                    Record newRecord = new Record(c.getTime(),title,userName,thisConcern.getTitle());

                    //Adding the photograph classes to the record class
                    ArrayList<Photograph> pkp = photorec.getPhoto();
                    for (int u = 0; u < pkp.size(); u++){
                        newRecord.addPhoto(pkp.get(u));
                    }
                    //

                    thisConcern.addRecord(newRecord);
                }

                Intent doneIntent = new Intent(NewRecordActivity.this, ViewConcernActivity.class);
                Bundle doneBundle = new Bundle();
                doneBundle.putInt("pos",pos);
                doneBundle.putString("userName",userName);
                doneIntent.putExtras(doneBundle);
                startActivity(doneIntent);
            }
        });

        FloatingActionButton cancelNewRecordFAB = findViewById(R.id.cancelNewRecordFAB);
        cancelNewRecordFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cancelIntent = new Intent(NewRecordActivity.this, ViewConcernActivity.class);
                Bundle cancelBundle = new Bundle();
                cancelBundle.putInt("pos",pos);
                cancelBundle.putString("userName",userName);
                cancelIntent.putExtras(cancelBundle);
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

    public void setBodyText(Button butt){
        //
        if (photos.size() != 0){
            String s = "";
            String s3 = "";
            String s2 = "";
            for (int i = 0; i < bits.size(); i++){
                Photograph p = photos.get(i);
                Bitmap b = bits.get(i);
                //
                EncryptDecryptImageBitmap ed = new EncryptDecryptImageBitmap(photorec.getUserName());
                ArrayList<String> bps = p.getBPs();
                for (String bbb: bps){
                    if (s3.indexOf(bbb) == -1){
                        s3 = s3 + " " + bbb;
                    }
                }
            }
            if (photos.size() == 0){
                s2 = "Photo: 0";
            }else{
                s2 = "Photo(s): "+photos.size();
            }
            if (s3.length() == 0){
                s = "Body Parts: None";
            }else{
                s = "Body Parts: " + s3;
            }

            butt.setText(s2 + "; \n" +s);
        }else{
            butt.setText("ADD BODY PARTS");
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 666 && resultCode == RESULT_OK && data != null) {
            //When delete prompt is returned
            String jsonPhotos1 = data.getStringExtra("BITMAPS");
            String jsonPhotos2 = data.getStringExtra("PHOTOCLASSES");
            String username = data.getStringExtra("THEUSERNAME");

            Gson gson = new Gson();
            Type type = new TypeToken<List<Photograph>>(){}.getType();
            photos = gson.fromJson(jsonPhotos2, type);

            EncryptDecryptImageBitmap ed = new EncryptDecryptImageBitmap(username);

            Type type1 = new TypeToken<List<Bitmap>>(){}.getType();
            bits = gson.fromJson(jsonPhotos1, type1);

            ArrayList<Photograph> rphoto = photorec.getPhoto();

            for (int i = 0; i < rphoto.size(); i++){
                photorec.removePhoto(i);
            }

            for (int i = 0; i < bits.size(); i++){
                Photograph p = photos.get(i);
                Bitmap b = bits.get(i);
                if (b != null){
                    p.setEncrypted(ed.encrypt(b));
                }
                photorec.addPhoto(p);
            }

            Button addBodyPartsButton = findViewById(R.id.addBodyPartsButton);
            setBodyText(addBodyPartsButton);
        }
        int PLACE_PICKER_REQUEST = 1;
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(this, data);
                Button addLocationButton = findViewById(R.id.addLocationButton);
                addLocationButton.setText(place.getAddress());
            }
        }

    }

}